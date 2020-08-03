package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.context.ContextHelper
import com.proyectofinal.donarify.dto.post_interest.PostInterestListDto
import com.proyectofinal.donarify.dto.user.JwtRequestDto
import com.proyectofinal.donarify.dto.user.JwtResponseDto
import com.proyectofinal.donarify.dto.user.UserDto
import com.proyectofinal.donarify.dto.user.UserRequestDto
import com.proyectofinal.donarify.dto.user.UserUpdateDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.mapper.toPostInterestListDto
import com.proyectofinal.donarify.security.JwtTokenUtil
import com.proyectofinal.donarify.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
@CrossOrigin
class UserController(
    private val authenticationManager: AuthenticationManager,
    private val service: UserService,
    private val jwtTokenUtil: JwtTokenUtil
) {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun createAuthenticationToken(@RequestBody request: JwtRequestDto): JwtResponseDto {
        authenticate(request.username, request.password)
        val userDetails = service.loadUserByUsername(request.username)
        val token = jwtTokenUtil.generateToken(userDetails)

        return JwtResponseDto(token)
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody user: UserRequestDto): UserDto {
        return service.saveUser(user).toDto()
    }

    @GetMapping("/interests")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    fun getInterests(): PostInterestListDto {
        return toPostInterestListDto(service.getInterests())
    }

    @PutMapping("/settings")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun modifyUser(@RequestBody userUpdateDto: UserUpdateDto): UserDto {
        validateUser(userUpdateDto)
        return service.modifyUser(userUpdateDto).toDto()
    }

    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw RequestException("User is disabled", "disabled.user", HttpStatus.UNAUTHORIZED.value())
        } catch (e: BadCredentialsException) {
            throw RequestException("Invalid credentials", "invalid.credentials", HttpStatus.UNAUTHORIZED.value())
        }
    }

    private fun validateUser(userUpdate: UserUpdateDto) {
        if (userUpdate.username != ContextHelper.getLoggedUser()) {
            throw RequestException("Invalid username", "invalid.username", HttpStatus.BAD_REQUEST.value())
        }
    }
}
