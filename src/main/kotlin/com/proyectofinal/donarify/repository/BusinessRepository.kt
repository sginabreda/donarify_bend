package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.BusinessModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BusinessRepository : JpaRepository<BusinessModel, Long>
