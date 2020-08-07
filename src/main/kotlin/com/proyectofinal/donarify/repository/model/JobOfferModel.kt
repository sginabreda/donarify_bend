package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.proyectofinal.donarify.domain.JobOffer
import java.util.Date
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("1")
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
    override var temporal: Boolean,
    @Column(name = "is_full_time")
    override var fulltime: Boolean,
    @Column(name = "is_virtual")
    override var virtual: Boolean,
    override var type: Long,
    override var imageUrl: String?,
    @OneToMany(mappedBy = "post_id", fetch = FetchType.LAZY)
    override var interests: List<PostInterestModel> = listOf(),
    override var title: String,
    override var subType: Long? = null,
    override var creationDate: Date
) : PostModel() {

    override fun toDomain(): JobOffer {
        return JobOffer.of(
            id = id,
            description = description,
            address = address,
            organizationId = organization.id,
            temporal = temporal,
            fulltime = fulltime,
            virtual = virtual,
            imageUrl = imageUrl,
            title = title,
            creationDate = creationDate,
            organizationName = organization.name
        )
    }
}
