package com.yousufsohail.alligitor.network.model

import com.google.gson.annotations.SerializedName

class RepositoryDto(

    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("owner")
    var owner: Owner,

    @SerializedName("description")
    var description: String,

    @SerializedName("language")
    var language: String?,

    @SerializedName("stargazers_count")
    var stargazersCount: Long
)
