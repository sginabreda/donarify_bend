package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.proyectofinal.donarify.domain.Organization
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import org.hibernate.annotations.Where

@Entity
@Table(name = "organizations", schema = "public")
data class OrganizationModel(
    @Column(name = "name")
    var name: String,
    @Column(name = "description", columnDefinition = "TEXT")
    var description: String,
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, targetEntity = JobOfferModel::class)
    @JsonManagedReference
    @Where(clause = "type=1")
    var jobs: List<JobOfferModel> = listOf(),
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, targetEntity = VolunteeringModel::class)
    @JsonManagedReference
    @Where(clause = "type=2")
    var volunteerings: List<VolunteeringModel> = listOf(),
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, targetEntity = CampaignModel::class)
    @JsonManagedReference
    var campaigns: List<CampaignModel> = listOf(),
    @Column(name = "address")
    var address: String,
    @Column(name = "activity_type")
    var activityType: String,
    @Column(name = "url")
    var url: String?,
    @Column(name = "facebook_url")
    var facebookUrl: String?,
    @Column(name = "twitter_url")
    var twitterUrl: String?,
    @Column(name = "instagram_url")
    var instagramUrl: String?,
    @Column(name = "email")
    var email: String,
    @OneToOne(mappedBy = "organization")
    var user: UserModel? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    var id: Long = 0

    fun toDomain(): Organization {
        return Organization(
            id = id,
            name = name,
            description = description,
            address = address,
            activityType = activityType,
            url = url,
            facebookUrl = facebookUrl,
            twitterUrl = twitterUrl,
            instagramUrl = instagramUrl,
            email = email,
            imageUrl = user?.imageUrl
        )
    }

    companion object {
        fun of(
            id: Long?,
            name: String,
            description: String,
            address: String,
            activityType: String,
            url: String?,
            facebookUrl: String?,
            twitterUrl: String?,
            instagramUrl: String?,
            email: String
        ): OrganizationModel {
            val org = OrganizationModel(
                name = name,
                description = description,
                address = address,
                activityType = activityType,
                url = url,
                facebookUrl = facebookUrl,
                twitterUrl = twitterUrl,
                instagramUrl = instagramUrl,
                email = email
            )
            id?.let { org.id = id }
            return org
        }
    }
}
