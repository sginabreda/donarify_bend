package com.proyectofinal.donarify.dto

import com.proyectofinal.donarify.security.SecurityRole

data class UserDto(
    val username: String,
    val password: String,
    val userRole: SecurityRole
)
