package com.proyectofinal.donarify.dto.user

import com.proyectofinal.donarify.dto.organization.OrganizationRequestDto
import com.proyectofinal.donarify.security.SecurityRole

data class UserRequestDto(
    val username: String,
    val password: String,
    val userRole: SecurityRole,
    val name: String?,
    val lastName: String?,
    val address: String?,
    val telephone: String?,
    val organization: OrganizationRequestDto?
)
