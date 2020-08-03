package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.PostInterestModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostInterestRepository : JpaRepository<PostInterestModel, Long>
