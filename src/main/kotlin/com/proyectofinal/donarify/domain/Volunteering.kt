package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.VolunteeringDto
import com.proyectofinal.donarify.repository.model.VolunteeringModel

data class Volunteering(
    val id: Long?,
    val description: String,
    val address: String,
    val organizationId: Long,
    val isTemporal: Boolean,
    val isFullTime: Boolean,
    val isVirtual: Boolean
) {
    fun toModel(organization: Organization): VolunteeringModel {
        return VolunteeringModel.of(id, description, address, organization.toModel(), isTemporal, isFullTime, isVirtual)
    }

    fun toDto(): VolunteeringDto {
        return VolunteeringDto(id, description, address, organizationId, isTemporal, isFullTime, isVirtual)
    }
}
