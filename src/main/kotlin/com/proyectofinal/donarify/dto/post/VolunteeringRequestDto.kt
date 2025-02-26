package com.proyectofinal.donarify.dto.post

import com.proyectofinal.donarify.domain.Volunteering
import com.proyectofinal.donarify.domain.VolunteeringType
import java.time.Instant
import java.util.Date

data class VolunteeringRequestDto(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val temporal: Boolean,
    override val fulltime: Boolean,
    override val virtual: Boolean,
    override val imageUrl: ByteArray?,
    override val title: String,
    val subType: String
) : PostRequestDto(id, description, address, temporal, fulltime, virtual, imageUrl, title) {

    override fun toDomain(): Volunteering {
        return Volunteering(
            id,
            description,
            address,
            0,
            temporal,
            fulltime,
            virtual,
            imageUrl,
            title,
            VolunteeringType.valueOf(subType),
            Date.from(Instant.now()),
            ""
        )
    }
}
