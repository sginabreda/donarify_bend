package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.PostDto
import com.proyectofinal.donarify.repository.model.PostModel

abstract class Post(
    open val id: Long?,
    open val description: String,
    open val address: String,
    open val organizationId: Long,
    open val temporal: Boolean,
    open val fulltime: Boolean,
    open val virtual: Boolean
) {

    abstract fun toModel(organization: Organization): PostModel

    abstract fun toDto(): PostDto
}
