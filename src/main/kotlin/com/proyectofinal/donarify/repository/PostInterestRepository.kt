package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.PostInterestModel
import com.proyectofinal.donarify.repository.model.PostModel
import com.proyectofinal.donarify.repository.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostInterestRepository : JpaRepository<PostInterestModel, Long> {

    fun findByPostAndUser(post: PostModel, user: UserModel): PostInterestModel?
}
