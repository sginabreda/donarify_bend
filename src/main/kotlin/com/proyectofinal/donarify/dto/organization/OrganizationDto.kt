package com.proyectofinal.donarify.dto.organization

import com.fasterxml.jackson.annotation.JsonInclude
import com.proyectofinal.donarify.dto.post.JobOfferDto
import com.proyectofinal.donarify.dto.post.VolunteeringDto

@JsonInclude(JsonInclude.Include.NON_NULL)
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
