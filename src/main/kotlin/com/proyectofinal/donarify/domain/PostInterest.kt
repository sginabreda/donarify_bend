package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.post_interest.PostInterestDto
import com.proyectofinal.donarify.repository.model.UserModel

data class PostInterest(
    val id: Long,
    val post: Post,
    val user: UserModel
) {
    fun toDto(): PostInterestDto {
        return PostInterestDto(id, post.toDto())
    }
}
