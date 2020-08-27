package com.proyectofinal.donarify.mapper

import com.proyectofinal.donarify.domain.Campaign
import com.proyectofinal.donarify.domain.Organization
import com.proyectofinal.donarify.domain.Post
import com.proyectofinal.donarify.domain.PostInterest
import com.proyectofinal.donarify.dto.campaign.CampaignListDto
import com.proyectofinal.donarify.dto.organization.OrganizationListDto
import com.proyectofinal.donarify.dto.post.PostListDto
import com.proyectofinal.donarify.dto.post_interest.PostInterestListDto

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

fun toPostInterestListDto(list: List<PostInterest>): PostInterestListDto {
    return PostInterestListDto(
        interests = list.map { it.toDto() }
    )
}

fun toCampaignListDto(list: List<Campaign>): CampaignListDto {
    return CampaignListDto(
        campaigns = list.map { it.toDto() }
    )
}
