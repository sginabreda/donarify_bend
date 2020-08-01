package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.VolunteeringDto
import com.proyectofinal.donarify.repository.model.VolunteeringModel

data class Volunteering(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val temporal: Boolean,
    override val fulltime: Boolean,
    override val virtual: Boolean
) : Post(id, description, address, organizationId, temporal, fulltime, virtual) {
    override fun toModel(organization: Organization): VolunteeringModel {
        return VolunteeringModel(id, description, address, organization.toModel(), temporal, fulltime, virtual, PostType.VOLUNTEERING.value)
    }

    override fun toDto(): VolunteeringDto {
        return VolunteeringDto(id,address, description, fulltime, temporal, virtual, organizationId)
    }
}
