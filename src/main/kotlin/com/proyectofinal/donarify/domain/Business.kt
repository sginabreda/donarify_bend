package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.business.BusinessCreationDto
import com.proyectofinal.donarify.dto.business.BusinessDto
import com.proyectofinal.donarify.dto.user.UserRequestDto
import com.proyectofinal.donarify.repository.model.BusinessModel

data class Business(
    val id: Long?,
    val name: String,
    val description: String,
    val address: String,
    val activityType: String,
    val email: String,
    val imageUrl: String?
) {
    fun toDto(): BusinessDto {
        return BusinessDto(id, name, description, address, activityType, email, imageUrl)
    }

    fun toModel(): BusinessModel {
        return BusinessModel.of(id, name, description, activityType, imageUrl, email, address)
    }

    companion object {
        fun of(
            id: Long? = null,
            name: String,
            description: String,
            activityType: String,
            imageUrl: String? = null,
            email: String,
            address: String
        ): Business {
            return Business(
                id = id,
                name = name,
                description = description,
                activityType = activityType,
                imageUrl = imageUrl,
                email = email,
                address = address
            )
        }

        fun buildBusiness(businessCreation: BusinessCreationDto, user: UserRequestDto): Business {
            return of(
                name = businessCreation.name,
                description = businessCreation.description,
                address = user.address,
                activityType = businessCreation.activityType,
                email = user.username,
                imageUrl = businessCreation.imageUrl
            )
        }
    }
}
