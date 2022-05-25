package com.yousufsohail.alligitor.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yousufsohail.alligitor.cache.RepositoryDao
import com.yousufsohail.alligitor.cache.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class AlligitorDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

    companion object {

        val ALLIGITOR_DATABASE_NAME = "alligitor_db"
    }

}
