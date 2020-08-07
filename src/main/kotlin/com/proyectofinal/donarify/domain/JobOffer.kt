package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.post.JobOfferDto
import com.proyectofinal.donarify.repository.model.JobOfferModel
import com.proyectofinal.donarify.repository.model.PostModel
import com.proyectofinal.donarify.util.toStringDate
import java.time.Instant
import java.util.Date

data class JobOffer(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val temporal: Boolean,
    override val fulltime: Boolean,
    override val virtual: Boolean,
    override val imageUrl: String?,
    override val title: String,
    override val creationDate: Date
) : Post(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl, title, creationDate) {

    override fun toModel(organization: Organization): PostModel {
        return JobOfferModel(
            id,
            description,
            address,
            organization.toModel(),
            temporal,
            fulltime,
            virtual,
            PostType.JOB_OFFER.value,
            imageUrl,
            emptyList(),
            title,
            null,
            Date.from(Instant.now())
        )
    }

    override fun toDto(): JobOfferDto {
        return JobOfferDto(
            id,
            address,
            description,
            fulltime,
            temporal,
            virtual,
            organizationId,
            imageUrl,
            title,
            creationDate.toStringDate()
        )
    }
}
