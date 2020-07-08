package com.proyectofinal.donarify.repository.model

import com.proyectofinal.donarify.domain.Post
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "posts", schema = "public")
data class PostModel(
    var activity: String,
    var address: String,
    var type: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun toPost(): Post {
        return Post(id, activity, address, type)
    }
}