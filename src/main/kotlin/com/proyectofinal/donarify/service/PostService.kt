package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.Post
import com.proyectofinal.donarify.domain.PostType
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

    fun listPosts(
        postType: PostType?,
        organizationId: Long?,
        temporal: Boolean?,
        fulltime: Boolean?,
        virtual: Boolean?
    ): List<Post> {
        val postList = repository.findAllBy(postType?.value, organizationId, temporal, fulltime, virtual)
        return postList.map { it.toDomain() }
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
        postModel.fulltime = post.fulltime
        postModel.temporal = post.temporal
        postModel.virtual = post.virtual
    }
}
