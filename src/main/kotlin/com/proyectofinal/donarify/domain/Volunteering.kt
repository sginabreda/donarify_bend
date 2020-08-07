package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.post.VolunteeringDto
import com.proyectofinal.donarify.repository.model.VolunteeringModel
import com.proyectofinal.donarify.util.toStringDate
import java.time.Instant
import java.util.Date

data class Volunteering(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    override val temporal: Boolean,
    override val fulltime: Boolean,
    override val virtual: Boolean,
    override val imageUrl: String?,
    override val title: String,
    val subType: VolunteeringType,
    override val creationDate: Date
) : Post(id, description, address, organizationId, temporal, fulltime, virtual, imageUrl, title, creationDate) {

    override fun toModel(organization: Organization): VolunteeringModel {
        return VolunteeringModel(
            id,
            description,
            address,
            organization.toModel(),
            temporal,
            fulltime,
            virtual,
            PostType.VOLUNTEERING.value,
            imageUrl,
            emptyList(),
            title,
            subType.value,
            Date.from(Instant.now())
        )
    }

    override fun toDto(): VolunteeringDto {
        return VolunteeringDto(
            id,
            address,
            description,
            fulltime,
            temporal,
            virtual,
            organizationId,
            imageUrl,
            title,
            subType.name,
            creationDate.toStringDate()
        )
    }
}
