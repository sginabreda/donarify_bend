package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.JobOfferDto
import com.proyectofinal.donarify.dto.JobOfferListDto
import com.proyectofinal.donarify.dto.JobOfferRequestDto
import com.proyectofinal.donarify.mapper.toJobOfferListDto
import com.proyectofinal.donarify.service.JobOfferService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("jobs")
class JobOfferController(private val service: JobOfferService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createJobOffer(@RequestBody jobOfferRequestDto: JobOfferRequestDto): String {
        return service.createJobOffer(jobOfferRequestDto.toDomain())
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listJobOffers(): JobOfferListDto {
        return toJobOfferListDto(service.listJobOffers())
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getJobOffer(@PathVariable id: Long): JobOfferDto {
        return service.getJobOffer(id).toDto()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun modifyJobOffer(@PathVariable id: Long, @RequestBody jobOfferRequestDto: JobOfferRequestDto): String {
        return service.modifyJobOffer(id, jobOfferRequestDto.toDomain())
    }
}
