package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.OrganizationDto
import com.proyectofinal.donarify.repository.model.OrganizationModel

data class Organization(
    val id: Long?,
    val name: String,
    val activity: String,
    val posts: List<Post> = listOf()
) {
    fun toOrganizationModel(): OrganizationModel {
        return OrganizationModel.of(id, name, activity)
    }

    fun toOrganizationDto(): OrganizationDto {
        return OrganizationDto(
            id = id,
            name = name,
            activity = activity,
            posts = posts.map { it.toPostDto() }
        )
    }
}
