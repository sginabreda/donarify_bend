package com.proyectofinal.donarify.dto.campaign

import java.math.BigDecimal
import java.util.Date

data class CampaignDto(
    val id: Long,
    val title: String,
    val description: String,
    val amount: BigDecimal,
    val collectedAmount: BigDecimal,
    val endDate: Date,
    val creationDate: Date,
    val organizationId: Long
)
