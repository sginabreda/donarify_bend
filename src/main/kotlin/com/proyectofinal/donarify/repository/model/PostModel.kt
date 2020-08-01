package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.proyectofinal.donarify.domain.Post
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
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
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
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
    open var temporal: Boolean = false
    @Column(name = "is_full_time")
    open var fulltime: Boolean = false
    @Column(name = "is_virtual")
    open var virtual: Boolean = false
    @Column(name = "type", updatable = false, insertable = false)
    open var type: Long = 0
    @Column(name = "image_url")
    open var imageUrl: String? = ""

    abstract fun toDomain(): Post
}
