package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.proyectofinal.donarify.domain.Volunteering
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "volunteering", schema = "public")
data class VolunteeringModel(
    @Column(name = "description")
    var description: String,
    @Column(name = "address")
    var address: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "organization_id", nullable = false)
    var organization: OrganizationModel,
    @Column(name = "is_temporal")
    var isTemporal: Boolean,
    @Column(name = "is_full_time")
    var isFullTime: Boolean,
    @Column(name = "is_virtual")
    var isVirtual: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    fun toDomain(): Volunteering {
        return Volunteering(id, description, address, organization.id, isTemporal, isFullTime, isVirtual)
    }

    companion object {
        fun of(
            id: Long?,
            description: String,
            address: String,
            organization: OrganizationModel,
            isTemporal: Boolean,
            isFullTime: Boolean,
            isVirtual: Boolean
        ): VolunteeringModel {
            val volunteeringModel = VolunteeringModel(
                description, address, organization, isTemporal, isFullTime, isVirtual
            )
            id?.run { volunteeringModel.id = id }
            return volunteeringModel
        }
    }
}
