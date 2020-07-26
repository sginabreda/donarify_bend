package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.proyectofinal.donarify.domain.Post
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "posts", schema = "public")
abstract class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0
    @Column(name = "description")
    open lateinit var description: String
    @Column(name = "address")
    open lateinit var address: String
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "organization_id", nullable = false)
    open lateinit var organization: OrganizationModel
    @Column(name = "is_temporal")
    open var isTemporal: Boolean = false
    @Column(name = "is_full_time")
    open var isFulltime: Boolean = false
    @Column(name = "is_virtual")
    open var isVirtual: Boolean = false

    abstract fun toDomain(): Post
}
