package com.yousufsohail.alligitor.di

import androidx.room.Room
import com.yousufsohail.alligitor.cache.RepositoryDao
import com.yousufsohail.alligitor.cache.database.AlligitorDatabase
import com.yousufsohail.alligitor.presentation.AlligitorApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(app: AlligitorApp): AlligitorDatabase {
        return Room
            .databaseBuilder(app, AlligitorDatabase::class.java, AlligitorDatabase.ALLIGITOR_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepositoryDao(database: AlligitorDatabase): RepositoryDao {
        return database.repositoryDao()
    }
}
