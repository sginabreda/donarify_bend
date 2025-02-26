package com.proyectofinal.donarify.dto.post

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes(
        JsonSubTypes.Type(value = JobOfferDto::class, name = "JOB_OFFER"),
        JsonSubTypes.Type(value = VolunteeringDto::class, name = "VOLUNTEERING")
)
abstract class PostDto(
    open val id: Long?,
    open val description: String,
    open val address: String,
    open val organizationId: Long,
    open val temporal: Boolean,
    open val fulltime: Boolean,
    open val virtual: Boolean,
    open val imageUrl: ByteArray?,
    open val title: String,
    open val creationDate: String,
    open val organizationName: String
)
