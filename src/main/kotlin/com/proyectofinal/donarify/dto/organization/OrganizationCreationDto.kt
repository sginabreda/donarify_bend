package com.proyectofinal.donarify.dto.organization

data class OrganizationCreationDto(
    val name: String,
    val description: String,
    val activityType: String,
    val url: String,
    val imageUrl: String?
)
