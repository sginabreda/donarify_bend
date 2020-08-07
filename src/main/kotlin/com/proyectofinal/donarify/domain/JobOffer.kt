package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.post.JobOfferDto
import com.proyectofinal.donarify.repository.model.JobOfferModel
import com.proyectofinal.donarify.repository.model.PostModel
import com.proyectofinal.donarify.util.toStringDate
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
    override val creationDate: Date,
    override val organizationName: String
) : Post(
    id,
    description,
    address,
    organizationId,
    temporal,
    fulltime,
    virtual,
    imageUrl,
    title,
    creationDate,
    organizationName
) {

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
            creationDate
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
            creationDate.toStringDate(),
            organizationName
        )
    }

    companion object {
        fun of(
            id: Long,
            description: String,
            address: String,
            organizationId: Long,
            temporal: Boolean,
            fulltime: Boolean,
            virtual: Boolean,
            imageUrl: String?,
            title: String,
            creationDate: Date,
            organizationName: String
        ): JobOffer {
            return JobOffer(
                id = id,
                description = description,
                address = address,
                organizationId = organizationId,
                temporal = temporal,
                fulltime = fulltime,
                virtual = virtual,
                imageUrl = imageUrl,
                title = title,
                creationDate = creationDate,
                organizationName = organizationName
            )
        }
    }
}
