package com.proyectofinal.donarify.dto

data class JobOfferDto(
    override val id: Long,
    override val address: String,
    override val description: String,
    override val isFulltime: Boolean,
    override val isTemporal: Boolean,
    override val isVirtual: Boolean,
    override val organizationId: Long
) : PostDto(id, description, address, organizationId, isTemporal, isFulltime, isVirtual)
