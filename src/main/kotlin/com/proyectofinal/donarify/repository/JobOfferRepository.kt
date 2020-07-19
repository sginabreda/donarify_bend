package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.JobOfferModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobOfferRepository : JpaRepository<JobOfferModel, Long>
