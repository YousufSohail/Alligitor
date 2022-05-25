package com.yousufsohail.alligitor.domain.model

import java.util.*

data class Repository(
    val id: Long,
    val name: String,
    val description: String,
    val language: String,
    val stargazersCount: Long,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val dateCreated: Date,
    val dateUpdated: Date,
)
