package com.proyectofinal.donarify.dto.post

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class JobOfferDto(
    override val id: Long,
    override val address: String,
    override val description: String,
    override val fulltime: Boolean,
    override val temporal: Boolean,
    override val virtual: Boolean,
    override val organizationId: Long,
    override val imageUrl: String?,
    override val title: String,
    override val creationDate: String
) : PostDto(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl, title, creationDate)
