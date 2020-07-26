package com.proyectofinal.donarify.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.proyectofinal.donarify.domain.Post

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(value = JobOfferRequestDto::class, name = "JobOffer"),
        JsonSubTypes.Type(value = VolunteeringRequestDto::class, name = "Volunteering")
)
abstract class PostRequestDto(
    open val id: Long,
    open val description: String,
    open val address: String,
    open val organizationId: Long,
    open val isTemporal: Boolean,
    open val isFulltime: Boolean,
    open val isVirtual: Boolean
) {
    abstract fun toDomain(): Post
}
