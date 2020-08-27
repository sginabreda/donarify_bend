package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.proyectofinal.donarify.domain.Campaign
import java.math.BigDecimal
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "campaigns", schema = "public")
data class CampaignModel(
    @Column(name = "title")
    var title: String,
    @Column(name = "description")
    var description: String,
    @Column(name = "amount")
    var amount: BigDecimal,
    @Column(name = "collected_amount")
    var collectedAmount: BigDecimal,
    @Column(name = "end_date")
    var endDate: Date,
    @Column(name = "creation_date")
    var creationDate: Date,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "organization_id", nullable = false)
    var organization: OrganizationModel
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    var id: Long = 0

    fun toDomain(): Campaign {
        return Campaign(id, title, description, amount, collectedAmount, endDate, creationDate, organization.id)
    }

    companion object {
        fun of(
            id: Long?,
            title: String,
            description: String,
            amount: BigDecimal,
            collectedAmount: BigDecimal,
            endDate: Date,
            creationDate: Date,
            organization: OrganizationModel
        ): CampaignModel {
            val campaignModel =
                CampaignModel(title, description, amount, collectedAmount, endDate, creationDate, organization)
            id?.let { campaignModel.id = it }
            return campaignModel
        }
    }
}
