package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Volunteering

data class VolunteeringRequestDto(
    val id: Long?,
    val description: String,
    val address: String,
    val organizationId: Long,
    val isTemporal: Boolean,
    val isFullTime: Boolean,
    val isVirtual: Boolean
) {
    fun toDomain(): Volunteering {
        return Volunteering(id, description, address, organizationId, isTemporal, isFullTime, isVirtual)
    }
}