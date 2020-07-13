package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.proyectofinal.donarify.domain.Post
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
@Table(name = "posts", schema = "public")
data class PostModel(
    @Column(name = "description")
    var description: String,
    @Column(name = "address")
    var address: String,
    @Column(name = "type")
    var type: String,
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

    fun toPost(): Post {
        return Post(id, description, address, type, organization.id, isTemporal, isFullTime, isVirtual)
    }

    companion object {
        fun of(
            id: Long?,
            description: String,
            address: String,
            type: String,
            organization: OrganizationModel,
            isTemporal: Boolean,
            isFullTime: Boolean,
            isVirtual: Boolean
        ): PostModel {
            val post = PostModel(
                description, address, type, organization, isTemporal, isFullTime, isVirtual
            )
            id?.run { post.id = id }
            return post
        }
    }
}