package com.proyectofinal.donarify.dto.campaign

import java.math.BigDecimal

data class DonationPreferenceDto(
    val description: String,
    val amount: BigDecimal
)