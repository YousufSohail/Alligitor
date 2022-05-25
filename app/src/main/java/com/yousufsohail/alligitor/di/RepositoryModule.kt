package com.yousufsohail.alligitor.di

import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.model.RepositoryDtoMapper
import com.yousufsohail.alligitor.repository.RepositoryRepository
import com.yousufsohail.alligitor.repository.RepositoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryRepository(
        repositoryService: RepositoryService,
        repositoryDtoMapper: RepositoryDtoMapper
    ): RepositoryRepository {
        return RepositoryRepositoryImpl(repositoryService, repositoryDtoMapper)
    }

}
