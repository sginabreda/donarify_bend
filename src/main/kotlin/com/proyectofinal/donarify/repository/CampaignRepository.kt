package com.proyectofinal.donarify.repository

import com.proyectofinal.donarify.repository.model.CampaignModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CampaignRepository : JpaRepository<CampaignModel, Long>
