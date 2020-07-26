package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.proyectofinal.donarify.domain.JobOffer
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
@DiscriminatorValue("JOB")
data class JobOfferModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long,
    @Column(name = "description")
    override var description: String,
    @Column(name = "address")
    override var address: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "organization_id", nullable = false)
    override var organization: OrganizationModel,
    @Column(name = "is_temporal")
    override var isTemporal: Boolean,
    @Column(name = "is_full_time")
    override var isFulltime: Boolean,
    @Column(name = "is_virtual")
    override var isVirtual: Boolean
) : PostModel() {

    override fun toDomain(): JobOffer {
        return JobOffer(id, description, address, organization.id, isTemporal, isFulltime, isVirtual)
    }
}
