package com.yousufsohail.alligitor.di

import com.yousufsohail.alligitor.cache.RepositoryDao
import com.yousufsohail.alligitor.cache.model.RepositoryEntityMapper
import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.model.RepositoryDtoMapper
import com.yousufsohail.alligitor.usercase.repository_list.SearchRepositories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UsecaseModule {

    @ViewModelScoped
    @Provides
    fun provideSearchRepositories(
        repositoryService: RepositoryService,
        dtoMapper: RepositoryDtoMapper,
        repositoryDao: RepositoryDao,
        entityMapper: RepositoryEntityMapper
    ): SearchRepositories {
        return SearchRepositories(repositoryService, dtoMapper, repositoryDao, entityMapper)
    }

}
