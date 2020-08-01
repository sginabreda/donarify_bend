package com.proyectofinal.donarify.dto

data class VolunteeringDto(
    override val id: Long,
    override val address: String,
    override val description: String,
    override val fulltime: Boolean,
    override val temporal: Boolean,
    override val virtual: Boolean,
    override val organizationId: Long,
    override val imageUrl: String?
) : PostDto(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl)
