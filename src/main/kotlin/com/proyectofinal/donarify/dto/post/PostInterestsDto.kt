package com.proyectofinal.donarify.dto.post

import com.proyectofinal.donarify.dto.user.UserDataDto

data class PostInterestsDto(
    val post: PostDto,
    val users: List<UserDataDto>
)
