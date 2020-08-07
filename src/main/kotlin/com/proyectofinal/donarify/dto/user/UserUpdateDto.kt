package com.proyectofinal.donarify.dto.user

data class UserUpdateDto(
    val username: String,
    val password: String?,
    val name: String?,
    val lastName: String?,
    val address: String?,
    val telephone: String?
)
