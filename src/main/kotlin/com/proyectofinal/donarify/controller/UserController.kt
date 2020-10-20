package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.post.PostInterestsListDto
import com.proyectofinal.donarify.dto.post_interest.PostInterestListDto
import com.proyectofinal.donarify.dto.user.JwtRequestDto
import com.proyectofinal.donarify.dto.user.JwtResponseDto
import com.proyectofinal.donarify.dto.user.UserDto
import com.proyectofinal.donarify.dto.user.UserRequestDto
import com.proyectofinal.donarify.dto.user.UserUpdateDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.mapper.toPostInterestListDto
import com.proyectofinal.donarify.security.JwtTokenUtil
import com.proyectofinal.donarify.security.SecurityRole
import com.proyectofinal.donarify.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
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
        validateBody(user)
        return service.saveUser(user).toDto()
    }

    @GetMapping("/interests")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER','BUSINESS')")
    fun getInterests(): PostInterestListDto {
        return toPostInterestListDto(service.getInterests())
    }

    @DeleteMapping("/interests/{interestId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('USER','BUSINESS')")
    fun deleteInterest(@PathVariable interestId: Long) {
        return service.deleteInterest(interestId)
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER','ORGANIZATION','BUSINESS')")
    fun getProfile(): UserDto {
        return service.getProfile().toDto()
    }

    @PutMapping("/profile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('USER','ORGANIZATION','BUSINESS')")
    fun modifyUser(@RequestBody userUpdateDto: UserUpdateDto): UserDto {
        return service.modifyUser(userUpdateDto).toDto()
    }

    @GetMapping("/post-interests")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ORGANIZATION')")
    fun getInterestedUsers(): PostInterestsListDto {
        return PostInterestsListDto(service.getInterestedUsers())
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

    private fun validateBody(user: UserRequestDto) {
        if (user.userRole == SecurityRole.ORGANIZATION && user.organization == null) {
            throw RequestException("Organization data is required", "bad.request", HttpStatus.BAD_REQUEST.value())
        } else if (user.userRole == SecurityRole.BUSINESS && user.business == null) {
            throw RequestException("Business data is required", "bad.request", HttpStatus.BAD_REQUEST.value())
        }
    }
}
