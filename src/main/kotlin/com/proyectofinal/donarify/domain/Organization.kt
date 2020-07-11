package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.OrganizationDto
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
    val posts: List<Post> = listOf()
) {
    fun toOrganizationModel(): OrganizationModel {
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
            email
        )
    }

    fun toOrganizationDto(): OrganizationDto {
        return OrganizationDto(
            id = id!!,
            name = name,
            description = description,
            posts = posts.map { it.toPostDto() },
            address = address,
            activityType = activityType,
            url = url,
            facebookUrl = facebookUrl,
            twitterUrl = twitterUrl,
            instagramUrl = instagramUrl,
            email = email
        )
    }
}
