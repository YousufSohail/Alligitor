package com.yousufsohail.alligitor.network.model

import com.google.gson.annotations.SerializedName

class Owner(

    @SerializedName("login")
    var login: String,

    @SerializedName("avatar_url")
    var avatar_url: String
)
