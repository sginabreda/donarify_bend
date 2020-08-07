package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.organization.OrganizationDto
import com.proyectofinal.donarify.dto.organization.OrganizationListDto
import com.proyectofinal.donarify.dto.organization.OrganizationRequestDto
import com.proyectofinal.donarify.mapper.toOrganizationListDto
import com.proyectofinal.donarify.service.OrganizationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("organizations")
class OrganizationController(private val service: OrganizationService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listOrganizations(): OrganizationListDto {
        return toOrganizationListDto(service.listOrganizations())
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getOrganization(@PathVariable id: Long): OrganizationDto {
        return service.getOrganization(id).toDto()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun modifyOrganization(@PathVariable id: Long, @RequestBody organizationDto: OrganizationRequestDto): String {
        return service.modifyOrganization(id, organizationDto.toOrganization())
    }
}
