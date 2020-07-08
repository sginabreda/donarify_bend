package com.proyectofinal.donarify.mapper

import com.proyectofinal.donarify.domain.Post
import com.proyectofinal.donarify.dto.PostListDto

fun toPostListDto(list: List<Post>): PostListDto {
    return PostListDto(
        posts = list.map { it.toPostDto() }
    )
}