package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Volunteering

data class VolunteeringRequestDto(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val isTemporal: Boolean,
    override val isFulltime: Boolean,
    override val isVirtual: Boolean
) : PostRequestDto(id, description, address, organizationId, isTemporal, isFulltime, isVirtual) {

    override fun toDomain(): Volunteering {
        return Volunteering(id, description, address, organizationId, isTemporal, isFulltime, isVirtual)
    }
}
