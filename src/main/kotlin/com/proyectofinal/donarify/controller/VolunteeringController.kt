package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.VolunteeringDto
import com.proyectofinal.donarify.dto.VolunteeringListDto
import com.proyectofinal.donarify.dto.VolunteeringRequestDto
import com.proyectofinal.donarify.mapper.toVolunteeringListDto
import com.proyectofinal.donarify.service.VolunteeringService
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
@RequestMapping("volunteering")
class VolunteeringController(private val service: VolunteeringService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createVolunteering(@RequestBody volunteeringRequestDto: VolunteeringRequestDto): String {
        return service.createVolunteering(volunteeringRequestDto.toDomain())
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listVolunteering(): VolunteeringListDto {
        return toVolunteeringListDto(service.listVolunteering())
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getVolunteering(@PathVariable id: Long): VolunteeringDto {
        return service.getVolunteering(id).toDto()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun modifyVolunteering(@PathVariable id: Long, @RequestBody volunteeringRequestDto: VolunteeringRequestDto): String {
        return service.modifyVolunteering(id, volunteeringRequestDto.toDomain())
    }
}
