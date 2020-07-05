package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.OrganizationRepository
import com.proyectofinal.donarify.repository.model.OrganizationModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

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
        val organizationModel = getOneOrThrowException(id)
        return organizationModel.toOrganization()
    }

    fun modifyOrganization(id: Long, organization: Organization): String {
        val organizationModel = getOneOrThrowException(id)
        organizationModel.activity = organization.activity
        organizationModel.name = organization.name
        repository.save(organizationModel)
        return "Organization updated!"
    }

    private fun getOneOrThrowException(id: Long): OrganizationModel {
        val org: OrganizationModel
        try {
            org = repository.getOne(id)
        } catch (e: Exception) {
            throw RequestException("Organization not found", "not.found", HttpStatus.NOT_FOUND.value())
        }
        return org
    }
}
