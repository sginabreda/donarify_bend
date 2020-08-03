package com.proyectofinal.donarify.repository.model

import com.proyectofinal.donarify.dto.user.UserDto
import com.proyectofinal.donarify.security.SecurityRole
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

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
    var telephone: String? = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0

    fun toDto(): UserDto {
        return UserDto(username, password, name, lastName, address, telephone)
    }
}
