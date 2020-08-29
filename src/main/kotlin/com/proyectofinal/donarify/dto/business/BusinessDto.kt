package com.proyectofinal.donarify.dto.business

import com.fasterxml.jackson.annotation.JsonInclude
import com.proyectofinal.donarify.domain.Business

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BusinessDto(
    val id: Long?,
    val name: String,
    val description: String,
    val address: String,
    val activityType: String,
    val email: String,
    val imageUrl: String?
) {
    fun toDomain(): Business {
        return Business.of(id, name, description, activityType, imageUrl, email, address)
    }
}
