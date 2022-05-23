package com.yousufsohail.alligitor.network.model

import com.google.gson.annotations.SerializedName

class Owner(

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("login")
    var login: String? = null,

    @SerializedName("avatar_url")
    var avatar_url: String? = null
)
