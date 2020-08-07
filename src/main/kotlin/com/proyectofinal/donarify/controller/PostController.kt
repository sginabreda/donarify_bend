package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.domain.PostType
import com.proyectofinal.donarify.domain.VolunteeringType
import com.proyectofinal.donarify.dto.post.PostDto
import com.proyectofinal.donarify.dto.post.PostListDto
import com.proyectofinal.donarify.dto.post.PostRequestDto
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.mapper.EnumMapper.Companion.booleanMapper
import com.proyectofinal.donarify.mapper.EnumMapper.Companion.postTypeMapper
import com.proyectofinal.donarify.mapper.EnumMapper.Companion.subTypeMapper
import com.proyectofinal.donarify.mapper.EnumMapper.Companion.throwError
import com.proyectofinal.donarify.mapper.toPostListDto
import com.proyectofinal.donarify.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
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
class PostController(private val service: PostService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZATION')")
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
        @RequestParam(value = "virtual") virtual: String?,
        @RequestParam(value = "subType") subType: String?
    ): PostListDto {
        val postType = type?.let { postTypeMapper[it] ?: throwError("postType") }
        val isTemporal = temporal?.let { booleanMapper[it] ?: throwError("temporal") }
        val isFulltime = fulltime?.let { booleanMapper[it] ?: throwError("fulltime") }
        val isVirtual = virtual?.let { booleanMapper[it] ?: throwError("virtual") }
        val subtype = subType?.let { subTypeMapper[it] ?: throwError("subType") }
        validateParameters(postType, subtype)
        return toPostListDto(service.listPosts(postType, organizationId, isTemporal, isFulltime, isVirtual, subtype))
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable id: Long) {
        return service.deletePost(id)
    }

    @PostMapping("/{id}/interests")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    fun createInterest(@PathVariable id: Long): String {
        return service.createInterest(id)
    }

    private fun validateParameters(postType: PostType?, subType: VolunteeringType?) {
        postType?.let {
            if (it == PostType.JOB_OFFER) {
                if (subType != null)
                    throw RequestException(
                        "SubType can only be sent with type VOLUNTEERING",
                        "bad.request",
                        HttpStatus.BAD_REQUEST.value()
                    )
            }
        }
    }
}
