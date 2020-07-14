package com.proyectofinal.donarify.mapper

import com.proyectofinal.donarify.domain.JobOffer
import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.dto.JobOfferListDto
import com.proyectofinal.donarify.dto.OrganizationListDto

fun toOrganizationListDto(list: List<Organization>): OrganizationListDto {
    return OrganizationListDto(
        organizations = list.map { it.toDto() }
    )
}

fun toJobOfferListDto(list: List<JobOffer>): JobOfferListDto {
    return JobOfferListDto(
        jobOffers = list.map { it.toDto() }
    )
}