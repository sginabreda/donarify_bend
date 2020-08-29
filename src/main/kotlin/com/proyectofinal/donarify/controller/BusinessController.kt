package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.business.BusinessDto
import com.proyectofinal.donarify.service.BusinessService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("businesses")
class BusinessController(private val service: BusinessService) {

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('BUSINESS')")
    fun modifyBusiness(@PathVariable id: Long, @RequestBody businessDto: BusinessDto) {
        return service.modifyBusiness(id, businessDto.toDomain())
    }
}
