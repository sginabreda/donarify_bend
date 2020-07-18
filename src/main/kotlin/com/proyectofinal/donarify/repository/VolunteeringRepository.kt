package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.VolunteeringModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VolunteeringRepository : JpaRepository<VolunteeringModel, Long>