package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserModel, Long> {

    fun findByUsername(username: String): UserModel?
}
