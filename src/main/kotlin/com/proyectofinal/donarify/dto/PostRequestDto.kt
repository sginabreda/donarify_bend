package com.proyectofinal.donarify.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.proyectofinal.donarify.domain.Post

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(value = JobOfferRequestDto::class, name = "JOB_OFFER"),
        JsonSubTypes.Type(value = VolunteeringRequestDto::class, name = "VOLUNTEERING")
)
abstract class PostRequestDto(
    open val id: Long,
    open val description: String,
    open val address: String,
    open val organizationId: Long,
    open val temporal: Boolean,
    open val fulltime: Boolean,
    open val virtual: Boolean,
    open val imageUrl: String?,
    open val title: String
) {
    abstract fun toDomain(): Post
}
