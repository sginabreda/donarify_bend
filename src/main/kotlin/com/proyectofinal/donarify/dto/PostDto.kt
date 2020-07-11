package com.proyectofinal.donarify.dto

data class PostDto(
    val id: Long?,
    val description: String,
    val address: String,
    val type: String,
    val organizationId: Long,
    val isTemporal: Boolean,
    val isFullTime: Boolean,
    val isVirtual: Boolean
)