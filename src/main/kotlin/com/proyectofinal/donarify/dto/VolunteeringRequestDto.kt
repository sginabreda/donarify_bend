package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Volunteering

data class VolunteeringRequestDto(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val temporal: Boolean,
    override val fulltime: Boolean,
    override val virtual: Boolean,
    override val imageUrl: String?
) : PostRequestDto(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl) {

    override fun toDomain(): Volunteering {
        return Volunteering(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl)
    }
}
