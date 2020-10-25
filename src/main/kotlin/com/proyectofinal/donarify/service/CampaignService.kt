package com.proyectofinal.donarify.service

import com.mercadopago.MercadoPago
import com.mercadopago.resources.Preference
import com.mercadopago.resources.datastructures.preference.BackUrls
import com.mercadopago.resources.datastructures.preference.Item
import com.mercadopago.resources.datastructures.preference.Payer
import com.proyectofinal.donarify.config.MercadoPagoConfig
import com.proyectofinal.donarify.context.ContextHelper
import com.proyectofinal.donarify.domain.Campaign
import com.proyectofinal.donarify.dto.campaign.DonationPreferenceDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.CampaignRepository
import com.proyectofinal.donarify.repository.UserRepository
import com.proyectofinal.donarify.repository.model.CampaignModel
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CampaignService(
    private val repository: CampaignRepository,
    private val userRepository: UserRepository,
    private val orgService: OrganizationService,
    private val mpConfig: MercadoPagoConfig
) {
    fun createCampaign(campaign: Campaign) {
        val user = userRepository.findByUsername(ContextHelper.getLoggedUser())!!
        val organization = orgService.getOrganization(user.organization!!.id)
        repository.save(campaign.toModel(organization))
    }

    fun listCampaigns(): List<Campaign> {
        return repository.findAll().map { it.toDomain() }.sortedBy { it.id }
    }

    fun getCampaign(id: Long): Campaign {
        val campaignModel = getOneOrThrowException(id)
        return campaignModel.toDomain()
    }

    fun deleteCampaign(id: Long) {
        val campaignModel = getOneOrThrowException(id)
        return repository.delete(campaignModel)
    }

    fun modifyCampaign(id: Long, campaign: Campaign): String {
        val campaignModel = getOneOrThrowException(id)
        modifyAttributes(campaign, campaignModel)
        repository.save(campaignModel)

        return "Campaign modified!"
    }

    fun generatePreferenceId(id: Long, body: DonationPreferenceDto): String {
        MercadoPago.SDK.setAccessToken(mpConfig.config.access_token)
        val preference = Preference()
        val item = Item()

        item.title = body.description
        item.quantity = 1
        item.unitPrice = body.amount.toFloat()
        item.id = id.toString()

        val payer = Payer()

        preference.additionalInfo = body.description
        preference.backUrls = BackUrls().setFailure(mpConfig.config.error_url)
            .setSuccess(mpConfig.config.success_url)
        preference.payer = payer
        preference.appendItem(item)
        return preference.save().id
    }

    private fun getOneOrThrowException(id: Long): CampaignModel {
        return repository.findByIdOrNull(id)
            ?: throw RequestException("Organization not found", "not.found", HttpStatus.NOT_FOUND.value())
    }

    private fun modifyAttributes(campaign: Campaign, campaignModel: CampaignModel) {
        campaignModel.title = campaign.title
        campaignModel.description = campaign.description
        campaignModel.amount = campaign.amount
        campaignModel.endDate = campaign.endDate
        campaign.imageUrl?.let { campaignModel.imageUrl = it }
    }
}
