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
@Table(name = "posts", schema = "donarify")
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
    val id: Long = 0

    fun toPost(): Post {
        return Post(id, description, address, type, organization.id, isTemporal, isFullTime, isVirtual)
    }
}