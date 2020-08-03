package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.organization.OrganizationDto
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
    val imageUrl: String?,
    val jobs: List<JobOffer> = listOf(),
    val volunteerings: List<Volunteering> = listOf()
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
            jobs = jobs.map { it.toDto() },
            address = address,
            activityType = activityType,
            url = url,
            facebookUrl = facebookUrl,
            twitterUrl = twitterUrl,
            instagramUrl = instagramUrl,
            email = email,
            imageUrl = imageUrl,
            volunteerings = volunteerings.map { it.toDto() }
        )
    }
}
