package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Organization

data class OrganizationDto(
    val name: String,
    val activity: String
) {
    fun toOrganization(): Organization {
        return Organization(
            name = name,
            activity = activity
        )
    }
}