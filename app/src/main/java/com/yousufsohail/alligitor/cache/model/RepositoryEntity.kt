package com.yousufsohail.alligitor.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "stargazers_count")
    val stargazersCount: Long,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @ColumnInfo(name = "user_avatar_url")
    val userAvatarUrl: String,

    @ColumnInfo(name = "created_at")
    val dateCreated: String,

    @ColumnInfo(name = "updated_at")
    val dateUpdated: String,

    @ColumnInfo(name = "cached_at")
    val dateCached: String,

    )
