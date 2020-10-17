package com.proyectofinal.donarify.repository.model

import com.proyectofinal.donarify.dto.user.UserDto
import com.proyectofinal.donarify.security.SecurityRole
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import org.hibernate.annotations.Type

@Entity
@Table(name = "users", schema = "public")
data class UserModel(
    @Column(name = "username", unique = true)
    val username: String,
    @Column(name = "password")
    var password: String,
    @Column(name = "role")
    val role: SecurityRole,
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    var interests: List<PostInterestModel> = listOf(),
    @Column(name = "name")
    var name: String? = "",
    @Column(name = "last_name")
    var lastName: String? = "",
    @Column(name = "address")
    var address: String? = "",
    @Column(name = "telephone")
    var telephone: String? = "",
    @Lob
    @Column(name = "image_url")
    @Type(type = "org.hibernate.type.BinaryType")
    var imageUrl: ByteArray?,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id")
    val organization: OrganizationModel? = null,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "business_id", referencedColumnName = "business_id")
    val business: BusinessModel? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0

    fun toDto(): UserDto {
        return UserDto(
            username,
            password,
            name,
            lastName,
            address,
            telephone,
            imageUrl,
            organization?.toDomain()?.toDto(),
            business?.toDomain()?.toDto()
        )
    }

    companion object {
        fun of(
            username: String,
            password: String,
            role: SecurityRole,
            interests: List<PostInterestModel>,
            name: String?,
            lastName: String?,
            address: String?,
            telephone: String?,
            organizationModel: OrganizationModel,
            imageUrl: ByteArray?
        ): UserModel {
            return UserModel(
                username = username,
                password = password,
                role = role,
                interests = interests,
                name = name,
                lastName = lastName,
                address = address,
                telephone = telephone,
                organization = organizationModel,
                imageUrl = imageUrl
            )
        }
    }
}
