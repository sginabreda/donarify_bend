package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.campaign.CampaignCreationDto
import com.proyectofinal.donarify.dto.campaign.CampaignDto
import com.proyectofinal.donarify.dto.campaign.CampaignListDto
import com.proyectofinal.donarify.dto.campaign.DonationPreferenceDto
import com.proyectofinal.donarify.mapper.toCampaignListDto
import com.proyectofinal.donarify.service.CampaignService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("campaigns")
class CampaignController(private val service: CampaignService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZATION')")
    fun createCampaign(@RequestBody campaignDto: CampaignCreationDto) {
        return service.createCampaign(campaignDto.toDomain())
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listCampaigns(): CampaignListDto {
        return toCampaignListDto(service.listCampaigns())
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getCampaign(@PathVariable id: Long): CampaignDto {
        return service.getCampaign(id).toDto()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZATION')")
    fun deleteCampaign(@PathVariable id: Long) {
        return service.deleteCampaign(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ORGANIZATION')")
    fun modifyCampaign(@PathVariable id: Long, @RequestBody campaign: CampaignCreationDto): String {
        return service.modifyCampaign(id, campaign.toDomain())
    }

    @PostMapping("/{id}/donations")
    @ResponseStatus(HttpStatus.CREATED)
    fun donate(@PathVariable id: Long, @RequestBody donation: String): String {
        return "Donated!"
    }

    @PostMapping("/{id}/preferences")
    @ResponseStatus(HttpStatus.OK)
    fun generatePreferenceId(@PathVariable id: Long, @RequestBody body: DonationPreferenceDto): String {
        return service.generatePreferenceId(id, body)
    }
}
