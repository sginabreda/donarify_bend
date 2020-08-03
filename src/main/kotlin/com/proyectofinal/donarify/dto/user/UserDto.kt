package com.proyectofinal.donarify.dto.user

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
data class UserDto(
    val username: String,
    val password: String,
    val name: String?,
    val lastName: String?,
    val address: String?,
    val telephone: String?
)
