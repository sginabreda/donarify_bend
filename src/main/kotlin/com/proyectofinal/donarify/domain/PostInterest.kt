package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.PostInterestDto

data class PostInterest(
    val id: Long,
    val post: Post
) {
    fun toDto(): PostInterestDto {
        return PostInterestDto(id, post.toDto())
    }
}
