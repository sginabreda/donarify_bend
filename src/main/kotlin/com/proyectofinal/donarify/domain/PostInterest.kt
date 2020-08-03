package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.post_interest.PostInterestDto

data class PostInterest(
    val id: Long,
    val post: Post
) {
    fun toDto(): PostInterestDto {
        return PostInterestDto(id, post.toDto())
    }
}
