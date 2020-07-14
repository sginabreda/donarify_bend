package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.JobOffer
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.JobOfferRepository
import com.proyectofinal.donarify.repository.model.JobOfferModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class JobOfferService(private val repository: JobOfferRepository, private val orgService: OrganizationService) {

    fun createJobOffer(jobOffer: JobOffer): String {
        val organization = orgService.getOrganization(jobOffer.organizationId)
        repository.save(jobOffer.toModel(organization))
        return "Job Offer created!"
    }

    fun listJobOffers(): List<JobOffer> {
        return repository.findAll().map { it.toDomain() }
    }

    fun getJobOffer(id: Long): JobOffer {
        val jobOfferModel = getOneOrThrowException(id)
        return jobOfferModel.toDomain()
    }

    fun modifyJobOffer(id: Long, jobOffer: JobOffer): String {
        val postModel = getOneOrThrowException(id)
        modifyAttributes(jobOffer, postModel)
        repository.save(postModel)
        return "Job Offer updated!"
    }

    private fun getOneOrThrowException(id: Long): JobOfferModel {
        val jobOffer: JobOfferModel
        try {
            jobOffer = repository.getOne(id)
        } catch (e: Exception) {
            throw RequestException("Job Offer not found", "not.found", HttpStatus.NOT_FOUND.value())
        }
        return jobOffer
    }

    private fun modifyAttributes(jobOffer: JobOffer, jobOfferModel: JobOfferModel) {
        jobOfferModel.description = jobOffer.description
        jobOfferModel.address = jobOffer.address
        jobOfferModel.isFullTime = jobOffer.isFullTime
        jobOfferModel.isTemporal = jobOffer.isTemporal
        jobOfferModel.isVirtual = jobOffer.isVirtual
    }
}