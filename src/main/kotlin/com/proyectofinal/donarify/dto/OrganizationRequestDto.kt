package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.domain.Organization

data class OrganizationRequestDto(
    val id: Long?,
    val name: String,
    val description: String,
    val address: String,
    val activityType: String,
    val url: String?,
    val facebookUrl: String?,
    val twitterUrl: String?,
    val instagramUrl: String?,
    val email: String
) {
    fun toOrganization(): Organization {
        return Organization(
            id,
            name,
            description,
            address,
            activityType,
            url,
            facebookUrl,
            twitterUrl,
            instagramUrl,
            email
        )
    }
}
