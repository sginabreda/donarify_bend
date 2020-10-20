package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.OrganizationModel
import com.proyectofinal.donarify.repository.model.PostInterestModel
import com.proyectofinal.donarify.repository.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostInterestRepository : JpaRepository<PostInterestModel, Long> {

    fun findAllByUser(user: UserModel): List<PostInterestModel>

    @Query(value = "FROM PostInterestModel AS pi INNER JOIN PostModel AS po ON pi.post.id = po.id WHERE po.organization = :organization")
    fun getInterestedUsers(organization: OrganizationModel): List<PostInterestModel>
}
