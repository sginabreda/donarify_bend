package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.dto.UserDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.UserRepository
import com.proyectofinal.donarify.repository.model.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) : UserDetailsService {

    @Autowired
    private lateinit var encoder: PasswordEncoder

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username.isNullOrBlank()) throw RequestException("no username provided", "no username", HttpStatus.BAD_REQUEST.value())

        val user = repository.findByUsername(username)
            ?: throw RequestException("no username found", "not.found", HttpStatus.NOT_FOUND.value())

        return User(user.username, user.password, emptyList())
    }

    fun saveUser(user: UserDto): UserModel {
        val user = UserModel(user.username, encoder.encode(user.password))
        return repository.save(user)
    }
}