package com.proyectofinal.donarify.repository.model

import com.proyectofinal.donarify.domain.Business
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "business", schema = "public")
data class BusinessModel(
    @Column(name = "name")
    var name: String,
    @Column(name = "description", columnDefinition = "TEXT")
    var description: String,
    @Column(name = "address")
    var address: String,
    @Column(name = "activity_type")
    var activityType: String,
    @Column(name = "image_url")
    var imageUrl: String?,
    @Column(name = "email")
    var email: String,
    @OneToOne(mappedBy = "organization")
    var user: UserModel? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    var id: Long = 0

    fun toDomain(): Business {
        return Business(id, name, description, address, activityType, email, imageUrl)
    }

    companion object {
        fun of(
            id: Long?,
            name: String,
            description: String,
            activityType: String,
            imageUrl: String?,
            email: String,
            address: String
        ): BusinessModel {
            val business =
                BusinessModel(
                    name = name,
                    description = description,
                    activityType = activityType,
                    imageUrl = imageUrl,
                    email = email,
                    address = address
                )
            id?.let { business.id = it }
            return business
        }
    }
}
