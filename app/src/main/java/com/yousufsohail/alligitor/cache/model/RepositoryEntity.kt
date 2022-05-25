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

    @ColumnInfo(name = "owner_name")
    val ownerName: String,

    @ColumnInfo(name = "owner_avatar_url")
    val ownerAvatarUrl: String,

    @ColumnInfo(name = "date_created")
    var dateCreated: Long,

    @ColumnInfo(name = "date_updated")
    var dateUpdated: Long,

    @ColumnInfo(name = "date_cached")
    var dateCached: Long

)
