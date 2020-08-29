package com.proyectofinal.donarify.dto.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.proyectofinal.donarify.dto.business.BusinessDto
import com.proyectofinal.donarify.dto.organization.OrganizationDto

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
data class UserDto(
    val username: String,
    val password: String,
    val name: String?,
    val lastName: String?,
    val address: String?,
    val telephone: String?,
    val organization: OrganizationDto?,
    val business: BusinessDto?
)
