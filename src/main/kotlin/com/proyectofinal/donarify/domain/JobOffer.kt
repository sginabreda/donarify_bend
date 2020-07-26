package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.JobOfferDto
import com.proyectofinal.donarify.repository.model.JobOfferModel
import com.proyectofinal.donarify.repository.model.PostModel

data class JobOffer(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val isTemporal: Boolean,
    override val isFulltime: Boolean,
    override val isVirtual: Boolean
) : Post(id, description, address, organizationId, isTemporal, isFulltime, isVirtual) {

    override fun toModel(organization: Organization): PostModel {
        return JobOfferModel(id, description, address, organization.toModel(), isTemporal, isFulltime, isVirtual)
    }

    override fun toDto(): JobOfferDto {
        return JobOfferDto(id, address, description, isFulltime, isTemporal, isVirtual, organizationId)
    }
}
