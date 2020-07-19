package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.OrganizationModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository : JpaRepository<OrganizationModel, Long>
