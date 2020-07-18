package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.Volunteering
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.VolunteeringRepository
import com.proyectofinal.donarify.repository.model.VolunteeringModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class VolunteeringService(private val repository: VolunteeringRepository, private val orgService: OrganizationService) {

    fun createVolunteering(volunteering: Volunteering): String {
        val organization = orgService.getOrganization(volunteering.organizationId)
        repository.save(volunteering.toModel(organization))
        return "Volunteering created!"
    }

    fun listVolunteering(): List<Volunteering> {
        return repository.findAll().map { it.toDomain() }
    }

    fun getVolunteering(id: Long): Volunteering {
        val volunteeringModel = getOneOrThrowException(id)
        return volunteeringModel.toDomain()
    }

    fun modifyVolunteering(id: Long, volunteering: Volunteering): String {
        val volunteeringModel = getOneOrThrowException(id)
        modifyAttributes(volunteering, volunteeringModel)
        repository.save(volunteeringModel)
        return "Volunteering updated!"
    }

    private fun getOneOrThrowException(id: Long): VolunteeringModel {
        val volunteeringModel: VolunteeringModel
        try {
            volunteeringModel = repository.getOne(id)
        } catch (e: Exception) {
            throw RequestException("Volunteering not found", "not.found", HttpStatus.NOT_FOUND.value())
        }
        return volunteeringModel
    }

    private fun modifyAttributes(volunteering: Volunteering, volunteeringModel: VolunteeringModel) {
        volunteeringModel.description = volunteering.description
        volunteeringModel.address = volunteering.address
        volunteeringModel.isFullTime = volunteering.isFullTime
        volunteeringModel.isTemporal = volunteering.isTemporal
        volunteeringModel.isVirtual = volunteering.isVirtual
    }
}