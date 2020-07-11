package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.PostDto
import com.proyectofinal.donarify.repository.model.PostModel

data class Post(
    val id: Long?,
    val description: String,
    val address: String,
    val type: String,
    val organizationId: Long,
    val isTemporal: Boolean,
    val isFullTime: Boolean,
    val isVirtual: Boolean
) {
    fun toPostModel(organization: Organization): PostModel {
        return PostModel(
            description,
            address,
            type,
            organization.toOrganizationModel(),
            isTemporal,
            isFullTime,
            isVirtual
        )
    }

    fun toPostDto(): PostDto {
        return PostDto(id, description, address, type, organizationId, isTemporal, isFullTime, isVirtual)
    }
}