package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.PostModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostModel, Long>