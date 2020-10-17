package com.proyectofinal.donarify.dto.user

data class UserUpdateDto(
    val password: String?,
    val name: String?,
    val lastName: String?,
    val address: String?,
    val telephone: String?,
    val imageUrl: ByteArray?
)
