package com.proyectofinal.donarify.dto.user

import com.proyectofinal.donarify.dto.business.BusinessCreationDto
import com.proyectofinal.donarify.dto.organization.OrganizationCreationDto
import com.proyectofinal.donarify.security.SecurityRole

data class UserRequestDto(
    val username: String,
    val password: String,
    val userRole: SecurityRole,
    val name: String,
    val lastName: String,
    val address: String,
    val telephone: String,
    val imageUrl: ByteArray?,
    val organization: OrganizationCreationDto?,
    val business: BusinessCreationDto?
)
