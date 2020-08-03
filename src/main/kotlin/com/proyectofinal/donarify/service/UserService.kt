package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.context.ContextHelper
import com.proyectofinal.donarify.domain.PostInterest
import com.proyectofinal.donarify.dto.UserDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.PostInterestRepository
import com.proyectofinal.donarify.repository.UserRepository
import com.proyectofinal.donarify.repository.model.PostInterestModel
import com.proyectofinal.donarify.repository.model.UserModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val interestRepository: PostInterestRepository
) : UserDetailsService {

    @Autowired
    private lateinit var encoder: PasswordEncoder

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username.isNullOrBlank()) {
            throw RequestException("Username was not provided", "invalid.username", HttpStatus.BAD_REQUEST.value())
        }
        val user = repository.findByUsername(username)
            ?: throw RequestException("Username not found", "not.found", HttpStatus.NOT_FOUND.value())

        return User(user.username, user.password, listOf(GrantedAuthority { user.role.toString() }))
    }

    fun saveUser(user: UserDto): UserModel {
        val userModel = UserModel(user.username, encoder.encode(user.password), user.userRole)
        return repository.save(userModel)
    }

    fun getInterests(): List<PostInterest> {
        val user = repository.findByUsername(ContextHelper.getLoggedUser())
        val list = interestRepository.findAllByUser(user!!)

        return list.map { it.toDomain() }
    }
}
