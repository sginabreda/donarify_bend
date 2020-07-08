package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Post

data class PostDto(
    val id: Long?,
    val activity: String,
    val address: String,
    val type: String
) {
    fun toPost(): Post {
        return Post(id, activity, address, type)
    }
}