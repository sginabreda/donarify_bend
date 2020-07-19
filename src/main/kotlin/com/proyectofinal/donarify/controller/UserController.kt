package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.JwtRequestDto
import com.proyectofinal.donarify.dto.JwtResponseDto
import com.proyectofinal.donarify.dto.UserDto
import com.proyectofinal.donarify.repository.model.UserModel
import com.proyectofinal.donarify.security.JwtTokenUtil
import com.proyectofinal.donarify.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class UserController(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService,
    private val jwtTokenUtil: JwtTokenUtil
) {
    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    fun createAuthenticationToken(@RequestBody request: JwtRequestDto): Any? {
        authenticate(request.username, request.password)

        val userDetails = userService.loadUserByUsername(request.username)

        val token = jwtTokenUtil.generateToken(userDetails)

        return ResponseEntity.ok(JwtResponseDto(token))
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun register(@RequestBody user: UserDto): ResponseEntity<UserModel> {
        return ResponseEntity.ok(userService.saveUser(user))
    }

    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}
