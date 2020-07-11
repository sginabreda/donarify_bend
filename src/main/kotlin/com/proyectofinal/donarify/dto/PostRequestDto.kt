package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Post

data class PostRequestDto(
    val id: Long?,
    val description: String,
    val address: String,
    val type: String,
    val organizationId: Long,
    val isTemporal: Boolean,
    val isFullTime: Boolean,
    val isVirtual: Boolean
) {
    fun toPost(): Post {
        return Post(id, description, address, type, organizationId, isTemporal, isFullTime, isVirtual)
    }
}