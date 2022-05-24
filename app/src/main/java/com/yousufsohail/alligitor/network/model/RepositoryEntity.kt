package com.yousufsohail.alligitor.network.model

import com.google.gson.annotations.SerializedName

class RepositoryEntity(

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("owner")
    var owner: Owner? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("language")
    var language: String? = null,

    @SerializedName("stargazers_count")
    var stargazersCount: Long? = null
)
