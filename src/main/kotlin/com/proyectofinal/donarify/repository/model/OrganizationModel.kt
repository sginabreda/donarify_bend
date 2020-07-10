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
import javax.persistence.Table

@Entity
@Table(name = "organizations", schema = "public")
data class OrganizationModel(
    var name: String,
    var activity: String,
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    @JsonManagedReference
    var posts: List<PostModel> = listOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    var id: Long = 0

    fun toOrganization(): Organization {
        return Organization(
            id = id,
            name = name,
            activity = activity,
            posts = posts.map { it.toPost() }
        )
    }

    companion object {
        fun of(id: Long?, name: String, activity: String): OrganizationModel {
            val org = OrganizationModel(name, activity)
            id?.let { org.id = id }
            return org
        }
    }
}