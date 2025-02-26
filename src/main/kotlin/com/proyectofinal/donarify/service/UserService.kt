package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.context.ContextHelper
import com.proyectofinal.donarify.domain.Business
import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.domain.PostInterest
import com.proyectofinal.donarify.dto.post.PostInterestsDto
import com.proyectofinal.donarify.dto.user.UserRequestDto
import com.proyectofinal.donarify.dto.user.UserUpdateDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.PostInterestRepository
import com.proyectofinal.donarify.repository.UserRepository
import com.proyectofinal.donarify.repository.model.BusinessModel
import com.proyectofinal.donarify.repository.model.OrganizationModel
import com.proyectofinal.donarify.repository.model.UserModel
import com.proyectofinal.donarify.security.SecurityRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
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

    fun saveUser(user: UserRequestDto): UserModel {
        var organization: OrganizationModel? = null
        var business: BusinessModel? = null
        if (user.userRole == SecurityRole.ORGANIZATION) {
            organization = Organization.buildOrganization(user.organization!!, user).toModel()
        } else if (user.userRole == SecurityRole.BUSINESS) {
            business = Business.buildBusiness(user.business!!, user).toModel()
        }
        val userModel = UserModel(
            user.username,
            encoder.encode(user.password),
            user.userRole,
            emptyList(),
            user.name,
            user.lastName,
            user.address,
            user.telephone,
            user.imageUrl,
            organization,
            business
        )
        return repository.save(userModel)
    }

    fun getInterests(): List<PostInterest> {
        val user = repository.findByUsername(ContextHelper.getLoggedUser())
        val list = interestRepository.findAllByUser(user!!)

        return list.map { it.toDomain() }
    }

    fun deleteInterest(interestId: Long) {
        val interest = interestRepository.findByIdOrNull(interestId) ?: throw RequestException(
            "Interest not found",
            "not.found",
            HttpStatus.NOT_FOUND.value()
        )

        interestRepository.delete(interest)
    }

    fun getProfile(): UserModel {
        return repository.findByUsername(ContextHelper.getLoggedUser())!!
    }

    fun modifyUser(userUpdateDto: UserUpdateDto): UserModel {
        val user = repository.findByUsername(ContextHelper.getLoggedUser())
        modifyAttributes(user!!, userUpdateDto)

        return repository.save(user)
    }

    fun getInterestedUsers(): List<PostInterestsDto> {
        val user = repository.findByUsername(ContextHelper.getLoggedUser())
        val interests = interestRepository.getInterestedUsers(user!!.organization!!).map { it.toDomain() }

        return interests.groupBy { it.post.id }.map { entry ->
            val post = entry.value.first().post
            val users = entry.value.map { it.user.toDataDto() }
            PostInterestsDto(post.toDto(), users)
        }
    }

    private fun modifyAttributes(userModel: UserModel, userUpdateDto: UserUpdateDto) {
        userUpdateDto.address?.let { userModel.address = it }
        userUpdateDto.name?.let { userModel.name = it }
        userUpdateDto.lastName?.let { userModel.lastName = it }
        userUpdateDto.telephone?.let { userModel.telephone = it }
        userUpdateDto.password?.let { userModel.password = it }
        userUpdateDto.imageUrl?.let { userModel.imageUrl = it }
    }
}
