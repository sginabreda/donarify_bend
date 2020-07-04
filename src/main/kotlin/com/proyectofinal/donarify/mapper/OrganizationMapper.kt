package com.proyectofinal.donarify.mapper

import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.dto.OrganizationListDto

fun toOrganizationListDto(list: List<Organization>): OrganizationListDto {
    return OrganizationListDto(
        organizations = list.map { it.toOrganizationDto() }
    )
}