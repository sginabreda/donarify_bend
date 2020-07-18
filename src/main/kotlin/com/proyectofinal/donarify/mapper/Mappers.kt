package com.proyectofinal.donarify.mapper

import com.proyectofinal.donarify.domain.JobOffer
import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.domain.Volunteering
import com.proyectofinal.donarify.dto.JobOfferListDto
import com.proyectofinal.donarify.dto.OrganizationListDto
import com.proyectofinal.donarify.dto.VolunteeringListDto

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

fun toVolunteeringListDto(list: List<Volunteering>): VolunteeringListDto {
    return VolunteeringListDto(
        volunteerings = list.map { it.toDto() }
    )
}
