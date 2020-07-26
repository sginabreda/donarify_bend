package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.JobOffer

data class JobOfferRequestDto(
    override val id: Long,
    override val address: String,
    override val description: String,
    override val isFulltime: Boolean,
    override val isTemporal: Boolean,
    override val isVirtual: Boolean,
    override val organizationId: Long
) : PostRequestDto(id, description, address, organizationId, isTemporal, isFulltime, isVirtual) {

    override fun toDomain(): JobOffer {
        return JobOffer(id, description, address, organizationId, isTemporal, isFulltime, isVirtual)
    }
}
