package com.proyectofinal.donarify.repository.model

import com.fasterxml.jackson.annotation.JsonManagedReference
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
    val username: String,
    val password: String,
    val role: SecurityRole,
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    var interests: List<PostInterestModel> = listOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0
}
