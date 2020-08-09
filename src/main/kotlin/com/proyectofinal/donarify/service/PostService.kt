package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.context.ContextHelper
import com.proyectofinal.donarify.domain.Post
import com.proyectofinal.donarify.domain.PostType
import com.proyectofinal.donarify.domain.VolunteeringType
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.PostInterestRepository
import com.proyectofinal.donarify.repository.PostRepository
import com.proyectofinal.donarify.repository.UserRepository
import com.proyectofinal.donarify.repository.model.PostInterestModel
import com.proyectofinal.donarify.repository.model.PostModel
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PostService(
    private val repository: PostRepository,
    private val orgService: OrganizationService,
    private val userRepository: UserRepository,
    private val interestRepository: PostInterestRepository
) {

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
        virtual: Boolean?,
        subType: VolunteeringType?
    ): List<Post> {
        var postList = repository.findAllBy(postType?.value, organizationId, temporal, fulltime, virtual)
        subType?.let {
            postList = postList.filter { postModel -> postModel.subType == it.value }
        }
        return postList.map { it.toDomain() }
    }

    fun listOwnPosts(
        postType: PostType?,
        temporal: Boolean?,
        fulltime: Boolean?,
        virtual: Boolean?,
        subType: VolunteeringType?
    ): List<Post> {
        val userModel = userRepository.findByUsername(ContextHelper.getLoggedUser())!!
        var postList = repository.findAllBy(postType?.value, userModel.organization!!.id, temporal, fulltime, virtual)
        subType?.let {
            postList = postList.filter { postModel -> postModel.subType == it.value }
        }
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

    fun deletePost(id: Long) {
        val postModel = getOneOrThrowException(id)
        return repository.delete(postModel)
    }

    fun createInterest(postId: Long): String {
        val postModel = getOneOrThrowException(postId)
        val userModel = userRepository.findByUsername(ContextHelper.getLoggedUser())!!
        val postInterest = PostInterestModel(postModel, userModel)

        try {
            interestRepository.save(postInterest)
        } catch (e: DataIntegrityViolationException) {
            throw RequestException("Interest already exists!", "duplicate.entry", HttpStatus.BAD_REQUEST.value())
        }

        return "Interest created!"
    }

    private fun getOneOrThrowException(id: Long): PostModel {
        return repository.findByIdOrNull(id)
            ?: throw RequestException("Post not found", "not.found", HttpStatus.NOT_FOUND.value())
    }

    private fun modifyAttributes(post: Post, postModel: PostModel) {
        postModel.description = post.description
        postModel.address = post.address
        postModel.fulltime = post.fulltime
        postModel.temporal = post.temporal
        postModel.virtual = post.virtual
        postModel.imageUrl = post.imageUrl
    }
}
