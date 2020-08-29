package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.organization.OrganizationCreationDto
import com.proyectofinal.donarify.dto.organization.OrganizationDto
import com.proyectofinal.donarify.dto.user.UserRequestDto
import com.proyectofinal.donarify.repository.model.OrganizationModel

data class Organization(
    val id: Long?,
    val name: String,
    val description: String,
    val address: String,
    val activityType: String,
    val url: String?,
    val facebookUrl: String?,
    val twitterUrl: String?,
    val instagramUrl: String?,
    val email: String,
    val imageUrl: String?
) {
    fun toModel(): OrganizationModel {
        return OrganizationModel.of(
            id,
            name,
            description,
            address,
            activityType,
            url,
            facebookUrl,
            twitterUrl,
            instagramUrl,
            email,
            imageUrl
        )
    }

    fun toDto(): OrganizationDto {
        return OrganizationDto(
            id = id!!,
            name = name,
            description = description,
            address = address,
            activityType = activityType,
            url = url,
            facebookUrl = facebookUrl,
            twitterUrl = twitterUrl,
            instagramUrl = instagramUrl,
            email = email,
            imageUrl = imageUrl
        )
    }

    companion object {
        fun of(
            id: Long? = null,
            name: String,
            description: String,
            address: String,
            activityType: String,
            url: String? = null,
            facebookUrl: String? = null,
            twitterUrl: String? = null,
            instagramUrl: String? = null,
            email: String,
            imageUrl: String? = null
        ): Organization {
            return Organization(
                id = id,
                name = name,
                description = description,
                address = address,
                activityType = activityType,
                url = url,
                facebookUrl = facebookUrl,
                twitterUrl = twitterUrl,
                instagramUrl = instagramUrl,
                email = email,
                imageUrl = imageUrl
            )
        }

        fun buildOrganization(orgCreation: OrganizationCreationDto, user: UserRequestDto): Organization {
            return of(
                name = orgCreation.name,
                description = orgCreation.description,
                address = user.address,
                activityType = orgCreation.activityType,
                email = user.username,
                imageUrl = orgCreation.imageUrl
            )
        }
    }
}
