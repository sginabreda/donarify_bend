package com.proyectofinal.donarify.dto.organization

import com.proyectofinal.donarify.dto.post.JobOfferDto
import com.proyectofinal.donarify.dto.post.VolunteeringDto

data class OrganizationDto(
    val id: Long,
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
    val jobs: List<JobOfferDto>? = listOf(),
    val volunteerings: List<VolunteeringDto>? = listOf()
)
