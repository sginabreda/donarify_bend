package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.repository.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class OrganizationService(private val repository: OrganizationRepository) {

    fun createOrganization(organization: Organization): String {
        repository.save(organization.toOrganizationModel())
        return "Organization created!"
    }

    fun listOrganizations(): List<Organization> {
        return repository.findAll().map { it.toOrganization() }
    }

    fun getOrganization(id: Long): Organization {
        return repository.getOne(id).toOrganization()
    }
}