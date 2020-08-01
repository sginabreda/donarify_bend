package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.JobOffer

data class JobOfferRequestDto(
    override val id: Long,
    override val address: String,
    override val description: String,
    override val fulltime: Boolean,
    override val temporal: Boolean,
    override val virtual: Boolean,
    override val organizationId: Long,
    override val imageUrl: String?
) : PostRequestDto(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl) {

    override fun toDomain(): JobOffer {
        return JobOffer(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl)
    }
}
