package com.proyectofinal.donarify.dto.post

data class PostUpdateDto(
    val address: String,
    val description: String,
    val fulltime: Boolean,
    val temporal: Boolean,
    val virtual: Boolean,
    val imageUrl: ByteArray?,
    val title: String
)
