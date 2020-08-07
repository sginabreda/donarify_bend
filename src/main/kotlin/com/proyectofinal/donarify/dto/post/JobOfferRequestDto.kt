package com.proyectofinal.donarify.dto.post

import com.proyectofinal.donarify.domain.JobOffer
import java.time.Instant
import java.util.Date

data class JobOfferRequestDto(
    override val id: Long,
    override val address: String,
    override val description: String,
    override val fulltime: Boolean,
    override val temporal: Boolean,
    override val virtual: Boolean,
    override val organizationId: Long,
    override val imageUrl: String?,
    override val title: String
) : PostRequestDto(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl, title) {

    override fun toDomain(): JobOffer {
        return JobOffer(
            id, description, address, organizationId, temporal, fulltime, virtual, imageUrl, title,
            Date.from(Instant.now())
        )
    }
}
