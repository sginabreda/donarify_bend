package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.PostDto
import com.proyectofinal.donarify.repository.model.PostModel

data class Post(
    val id: Long?,
    val activity: String,
    val address: String,
    val type: String
) {
    fun toPostModel(): PostModel {
        return PostModel(activity, address, type)
    }

    fun toPostDto(): PostDto {
        return PostDto(id, activity, address, type)
    }
}