package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.Business
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.BusinessRepository
import com.proyectofinal.donarify.repository.model.BusinessModel
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BusinessService(private val repository: BusinessRepository) {

    fun modifyBusiness(id: Long, business: Business) {
        val businessModel = getOneOrThrowException(id)
        modifyAttributes(business, businessModel)
        repository.save(businessModel)
    }

    private fun getOneOrThrowException(id: Long): BusinessModel {
        return repository.findByIdOrNull(id)
            ?: throw RequestException("Business not found", "not.found", HttpStatus.NOT_FOUND.value())
    }

    private fun modifyAttributes(business: Business, businessModel: BusinessModel) {
        businessModel.activityType = business.activityType
        businessModel.address = business.address
        businessModel.description = business.description
        businessModel.name = business.name
    }
}
