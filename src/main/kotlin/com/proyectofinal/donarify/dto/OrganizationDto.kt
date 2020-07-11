package com.proyectofinal.donarify.dto

data class OrganizationDto(
    val id: Long,
    val name: String,
    val description: String,
    val posts: List<PostDto>? = listOf(),
    val address: String,
    val activityType: String,
    val url: String?,
    val facebookUrl: String?,
    val twitterUrl: String?,
    val instagramUrl: String?,
    val email: String
)