package com.proyectofinal.donarify.dto.campaign

import com.proyectofinal.donarify.domain.Campaign
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

data class CampaignCreationDto(
    val title: String = "",
    val description: String = "",
    val amount: BigDecimal = BigDecimal.ZERO,
    val endDate: String,
    val imageUrl: ByteArray?
) {
    fun toDomain(): Campaign {
        return Campaign(
            null,
            title,
            description,
            amount,
            BigDecimal.ZERO,
            SimpleDateFormat("yyyy-MM-dd").parse(endDate),
            Date.from(Instant.now()),
            0,
            imageUrl
        )
    }
}
