package com.proyectofinal.donarify.mapper

import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.domain.Post
import com.proyectofinal.donarify.dto.OrganizationListDto
import com.proyectofinal.donarify.dto.PostListDto

fun toOrganizationListDto(list: List<Organization>): OrganizationListDto {
    return OrganizationListDto(
        organizations = list.map { it.toDto() }
    )
}

fun toPostListDto(list: List<Post>): PostListDto {
    return PostListDto(
        posts = list.map { it.toDto() }
    )
}
