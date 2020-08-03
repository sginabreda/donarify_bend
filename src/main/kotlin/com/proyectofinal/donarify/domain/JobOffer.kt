package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.post.JobOfferDto
import com.proyectofinal.donarify.repository.model.JobOfferModel
import com.proyectofinal.donarify.repository.model.PostModel

data class JobOffer(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val temporal: Boolean,
    override val fulltime: Boolean,
    override val virtual: Boolean,
    override val imageUrl: String?,
    override val title: String
) : Post(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl, title) {

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
            title
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
            title
        )
    }
}
