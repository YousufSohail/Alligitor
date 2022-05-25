package com.yousufsohail.alligitor.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository (
    val id: Long,
    val name: String,
    val description: String,
    val language: String,
    val stargazersCount: Long,
    val userName: String,
    val userAvatar: String
): Parcelable
