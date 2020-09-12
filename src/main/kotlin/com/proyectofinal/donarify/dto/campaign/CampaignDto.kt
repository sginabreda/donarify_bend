package com.proyectofinal.donarify.dto.campaign

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CampaignDto(
    val id: Long,
    val title: String,
    val description: String,
    val amount: BigDecimal,
    val collectedAmount: BigDecimal,
    val endDate: Date,
    val creationDate: Date,
    val organizationId: Long,
    val imageUrl: String?,
    val organizationName: String
)
