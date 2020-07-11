package com.proyectofinal.donarify.controller

import com.proyectofinal.donarify.dto.PostDto
import com.proyectofinal.donarify.dto.PostListDto
import com.proyectofinal.donarify.dto.PostRequestDto
import com.proyectofinal.donarify.mapper.toPostListDto
import com.proyectofinal.donarify.service.PostService
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
@RequestMapping("posts")
class PostController(private val service: PostService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@RequestBody postDto: PostRequestDto): String {
        return service.createPost(postDto.toPost())
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun listPosts(): PostListDto {
        return toPostListDto(service.listPosts())
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPost(@PathVariable id: Long): PostDto {
        return service.getPost(id).toPostDto()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun modifyPost(@PathVariable id: Long, @RequestBody postDto: PostRequestDto): String {
        return service.modifyPost(id, postDto.toPost())
    }
}
