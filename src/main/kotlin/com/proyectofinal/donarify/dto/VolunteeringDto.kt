package com.proyectofinal.donarify.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class VolunteeringDto(
    override val id: Long,
    override val description: String,
    override val address: String,
    override val organizationId: Long,
    @JsonProperty(value = "isTemporal")
    override val isTemporal: Boolean,
    @JsonProperty(value = "isFullTime")
    override val isFulltime: Boolean,
    @JsonProperty(value = "isVirtual")
    override val isVirtual: Boolean
) : PostDto(id, description, address, organizationId, isTemporal, isFulltime, isVirtual)
