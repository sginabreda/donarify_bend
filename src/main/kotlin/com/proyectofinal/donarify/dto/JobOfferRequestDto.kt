package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.JobOffer

data class JobOfferRequestDto(
    val id: Long?,
    val description: String,
    val address: String,
    val organizationId: Long,
    val isTemporal: Boolean,
    val isFullTime: Boolean,
    val isVirtual: Boolean
) {
    fun toDomain(): JobOffer {
        return JobOffer(id, description, address, organizationId, isTemporal, isFullTime, isVirtual)
    }
}