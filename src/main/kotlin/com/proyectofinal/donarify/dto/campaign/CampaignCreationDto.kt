package com.proyectofinal.donarify.dto.campaign

import com.fasterxml.jackson.annotation.JsonFormat
import com.proyectofinal.donarify.domain.Campaign
import java.math.BigDecimal
import java.time.Instant
import java.util.Date

data class CampaignCreationDto(
    val title: String = "",
    val description: String = "",
    val amount: BigDecimal = BigDecimal.ZERO,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val endDate: Date = Date()
) {
    fun toDomain(): Campaign {
        return Campaign(null, title, description, amount, BigDecimal.ZERO, endDate, Date.from(Instant.now()), 0)
    }
}
