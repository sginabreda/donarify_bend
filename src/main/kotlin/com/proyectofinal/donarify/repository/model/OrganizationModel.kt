package com.proyectofinal.donarify.repository.model

import com.proyectofinal.donarify.domain.Organization
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "organizations", schema = "public")
data class OrganizationModel(
    val name: String,
    val activity: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun toOrganization(): Organization {
        return Organization(
            id = id,
            name = name,
            activity = activity
        )
    }
}