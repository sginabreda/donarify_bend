package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Organization

data class OrganizationDto(
    val id: Long?,
    val name: String,
    val activity: String,
    val posts: List<PostDto>? = listOf()
) {
    fun toOrganization(): Organization {
        return Organization(
            id = id,
            name = name,
            activity = activity
        )
    }
}
