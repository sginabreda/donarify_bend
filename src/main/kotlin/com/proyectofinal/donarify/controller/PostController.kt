package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.PostDto
import com.proyectofinal.donarify.dto.PostListDto
import com.proyectofinal.donarify.dto.PostRequestDto
import com.proyectofinal.donarify.mapper.EnumMapper.Companion.booleanMapper
import com.proyectofinal.donarify.mapper.EnumMapper.Companion.postTypeMapper
import com.proyectofinal.donarify.mapper.EnumMapper.Companion.throwError
import com.proyectofinal.donarify.mapper.toPostListDto
import com.proyectofinal.donarify.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("posts")
@CrossOrigin
class PostController(private val service: PostService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody postRequestDto: PostRequestDto): String {
        return service.createPost(postRequestDto.toDomain())
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listPosts(
        @RequestParam(value = "postType") type: String?,
        @RequestParam(value = "organizationId") organizationId: Long?,
        @RequestParam(value = "temporal") temporal: String?,
        @RequestParam(value = "fulltime") fulltime: String?,
        @RequestParam(value = "virtual") virtual: String?
    ): PostListDto {
        val postType = type?.let { postTypeMapper[it] ?: throwError("postType") }
        val isTemporal = temporal?.let { booleanMapper[it] ?: throwError("temporal") }
        val isFulltime = fulltime?.let { booleanMapper[it] ?: throwError("fulltime") }
        val isVirtual = virtual?.let { booleanMapper[it] ?: throwError("virtual") }
        return toPostListDto(service.listPosts(postType, organizationId, isTemporal, isFulltime, isVirtual))
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPost(@PathVariable id: Long): PostDto {
        return service.getPost(id).toDto()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun modifyPost(@PathVariable id: Long, @RequestBody postRequestDto: PostRequestDto): String {
        return service.modifyPost(id, postRequestDto.toDomain())
    }
}
