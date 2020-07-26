package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.Post
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.PostRepository
import com.proyectofinal.donarify.repository.model.PostModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PostService(private val repository: PostRepository, private val orgService: OrganizationService) {

    fun createPost(post: Post): String {
        val organization = orgService.getOrganization(post.organizationId)
        repository.save(post.toModel(organization))
        return "Post created!"
    }

    fun listPosts(): List<Post> {
        return repository.findAll().map { it.toDomain() }
    }

    fun getPost(id: Long): Post {
        val postModel = getOneOrThrowException(id)
        return postModel.toDomain()
    }

    fun modifyPost(id: Long, post: Post): String {
        val postModel = getOneOrThrowException(id)
        modifyAttributes(post, postModel)
        repository.save(postModel)
        return "Post updated!"
    }

    private fun getOneOrThrowException(id: Long): PostModel {
        val postModel: PostModel
        try {
            postModel = repository.getOne(id)
        } catch (e: Exception) {
            throw RequestException("Job Offer not found", "not.found", HttpStatus.NOT_FOUND.value())
        }
        return postModel
    }

    private fun modifyAttributes(post: Post, postModel: PostModel) {
        postModel.description = post.description
        postModel.address = post.address
        postModel.isFulltime = post.isFulltime
        postModel.isTemporal = post.isTemporal
        postModel.isVirtual = post.isVirtual
    }
}
