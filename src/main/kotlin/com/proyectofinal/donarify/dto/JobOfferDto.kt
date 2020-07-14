package com.proyectofinal.donarify.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class JobOfferDto(
    val id: Long?,
    val description: String,
    val address: String,
    val organizationId: Long,
    @JsonProperty(value = "isTemporal")
    val isTemporal: Boolean,
    @JsonProperty(value = "isFullTime")
    val isFullTime: Boolean,
    @JsonProperty(value = "isVirtual")
    val isVirtual: Boolean
)
