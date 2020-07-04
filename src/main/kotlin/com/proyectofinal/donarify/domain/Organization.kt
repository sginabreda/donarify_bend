package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.OrganizationDto
import com.proyectofinal.donarify.repository.model.OrganizationModel

data class Organization(
    val name: String,
    val activity: String
) {
    fun toOrganizationModel(): OrganizationModel {
        return OrganizationModel(
            name = name,
            activity = activity
        )
    }

    fun toOrganizationDto(): OrganizationDto {
        return OrganizationDto(
            name = name,
            activity = activity
        )
    }
}