package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.post.PostDto
import com.proyectofinal.donarify.repository.model.PostModel

abstract class Post(
    open val id: Long?,
    open val description: String,
    open val address: String,
    open val organizationId: Long,
    open val temporal: Boolean,
    open val fulltime: Boolean,
    open val virtual: Boolean,
    open val imageUrl: String?,
    open val title: String
) {
    abstract fun toModel(organization: Organization): PostModel

    abstract fun toDto(): PostDto
}
