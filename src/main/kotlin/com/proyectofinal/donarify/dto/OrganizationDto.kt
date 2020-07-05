package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Organization

data class OrganizationDto(
    val id: Long?,
    val name: String,
    val activity: String
) {
    fun toOrganization(): Organization {
        return Organization(
            id = id,
            name = name,
            activity = activity
        )
    }
}