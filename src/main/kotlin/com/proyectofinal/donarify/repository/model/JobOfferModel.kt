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
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@DiscriminatorValue("1")
@Table(name = "posts", schema = "public")
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
    override var interests: List<PostInterestModel> = listOf()
) : PostModel() {

    override fun toDomain(): JobOffer {
        return JobOffer(id, description, address, organization.id, temporal, fulltime, virtual, imageUrl)
    }
}
