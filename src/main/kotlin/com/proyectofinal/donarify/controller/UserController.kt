package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.JwtRequestDto
import com.proyectofinal.donarify.dto.JwtResponseDto
import com.proyectofinal.donarify.dto.PostInterestListDto
import com.proyectofinal.donarify.dto.UserDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.mapper.toPostInterestListDto
import com.proyectofinal.donarify.repository.model.UserModel
import com.proyectofinal.donarify.security.JwtTokenUtil
import com.proyectofinal.donarify.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
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
    fun createAuthenticationToken(@RequestBody request: JwtRequestDto): Any? {
        authenticate(request.username, request.password)
        val userDetails = service.loadUserByUsername(request.username)
        val token = jwtTokenUtil.generateToken(userDetails)

        return ResponseEntity.ok(JwtResponseDto(token))
    }

    @PostMapping("/register")
    fun register(@RequestBody user: UserDto): ResponseEntity<UserModel> {
        return ResponseEntity.ok(service.saveUser(user))
    }

    @GetMapping("/interests")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    fun getInterests(): PostInterestListDto {
        return toPostInterestListDto(service.getInterests())
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
}
