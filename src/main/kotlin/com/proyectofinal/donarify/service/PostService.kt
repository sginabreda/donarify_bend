package com.proyectofinal.donarify.service

import com.proyectofinal.donarify.domain.Post
import com.proyectofinal.donarify.exception.RequestException
import com.proyectofinal.donarify.repository.PostRepository
import com.proyectofinal.donarify.repository.model.PostModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PostService(private val repository: PostRepository) {

    fun createPost(post: Post): String {
        repository.save(post.toPostModel())
        return "Post created!"
    }

    fun listPosts(): List<Post> {
        return repository.findAll().map { it.toPost() }
    }

    fun getPost(id: Long): Post {
        val organizationModel = getOneOrThrowException(id)
        return organizationModel.toPost()
    }

    fun modifyPost(id: Long, post: Post): String {
        val postModel = getOneOrThrowException(id)
        postModel.activity = post.activity
        postModel.address = post.address
        repository.save(postModel)
        return "Post updated!"
    }

    private fun getOneOrThrowException(id: Long): PostModel {
        val post: PostModel
        try {
            post = repository.getOne(id)
        } catch (e: Exception) {
            throw RequestException("Post not found", "not.found", HttpStatus.NOT_FOUND.value())
        }
        return post
    }
}