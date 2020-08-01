package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.PostModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostModel, Long> {

    @Query(
        value = "FROM PostModel AS vo WHERE (:type IS NULL OR vo.type=:type) AND (:organizationId IS NULL OR vo.organization.id=:organizationId) " +
            "AND (:temporal IS NULL OR vo.temporal=:temporal) AND (:fulltime IS NULL OR vo.fulltime=:fulltime) AND (:virtual IS NULL OR vo.virtual=:virtual)"
    )
    fun findAllBy(
        type: Long?,
        organizationId: Long?,
        temporal: Boolean?,
        fulltime: Boolean?,
        virtual: Boolean?
    ): List<PostModel>
}
