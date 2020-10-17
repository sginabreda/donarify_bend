package com.proyectofinal.donarify.domain

import com.proyectofinal.donarify.dto.campaign.CampaignDto
import com.proyectofinal.donarify.repository.model.CampaignModel
import java.math.BigDecimal
import java.util.Date

data class Campaign(
    val id: Long?,
    val title: String,
    val description: String,
    val amount: BigDecimal,
    val collectedAmount: BigDecimal,
    val endDate: Date,
    val creationDate: Date,
    val organizationId: Long,
    val imageUrl: ByteArray?,
    val organizationName: String = ""
) {
    fun toModel(organization: Organization): CampaignModel {
        return CampaignModel.of(
            id,
            title,
            description,
            amount,
            collectedAmount,
            endDate,
            creationDate,
            organization.toModel(),
            imageUrl
        )
    }

    fun toDto(): CampaignDto {
        return CampaignDto(
            id!!,
            title,
            description,
            amount,
            collectedAmount,
            endDate,
            creationDate,
            organizationId,
            imageUrl,
            organizationName
        )
    }
}
