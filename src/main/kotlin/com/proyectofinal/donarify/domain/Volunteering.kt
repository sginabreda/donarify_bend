package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.VolunteeringDto
import com.proyectofinal.donarify.repository.model.VolunteeringModel

data class Volunteering(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val isTemporal: Boolean,
    override val isFulltime: Boolean,
    override val isVirtual: Boolean
) : Post(id, description, address, organizationId, isTemporal, isFulltime, isVirtual) {
    override fun toModel(organization: Organization): VolunteeringModel {
        return VolunteeringModel(id, description, address, organization.toModel(), isTemporal, isFulltime, isVirtual)
    }

    override fun toDto(): VolunteeringDto {
        return VolunteeringDto(id, description, address, organizationId, isTemporal, isFulltime, isVirtual)
    }
}
