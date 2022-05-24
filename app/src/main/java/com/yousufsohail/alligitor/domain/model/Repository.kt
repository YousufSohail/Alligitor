package com.yousufsohail.alligitor.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository (
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val stargazersCount: Long? = null,
    val userName: String? = null,
    val userAvatar: String? = null
): Parcelable
