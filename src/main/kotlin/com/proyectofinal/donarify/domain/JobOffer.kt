package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.JobOfferDto
import com.proyectofinal.donarify.repository.model.JobOfferModel

data class JobOffer(
    val id: Long?,
    val description: String,
    val address: String,
    val organizationId: Long,
    val isTemporal: Boolean,
    val isFullTime: Boolean,
    val isVirtual: Boolean
) {
    fun toModel(organization: Organization): JobOfferModel {
        return JobOfferModel.of(id, description, address, organization.toModel(), isTemporal, isFullTime, isVirtual)
    }

    fun toDto(): JobOfferDto {
        return JobOfferDto(id, description, address, organizationId, isTemporal, isFullTime, isVirtual)
    }
}
