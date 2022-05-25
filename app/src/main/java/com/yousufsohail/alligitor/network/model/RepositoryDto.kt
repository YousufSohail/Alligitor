package com.yousufsohail.alligitor.network.model

import com.google.gson.annotations.SerializedName

class RepositoryDto(

    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("owner")
    var ownerDto: OwnerDto,

    @SerializedName("description")
    var description: String,

    @SerializedName("language")
    var language: String?,

    @SerializedName("stargazers_count")
    var stargazersCount: Long,

    @SerializedName("created_at")
    var createdAt: String,

    @SerializedName("updated_at")
    var updatedAt: String,
)
