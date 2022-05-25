package com.yousufsohail.alligitor.usercase.repository_list

import com.yousufsohail.alligitor.cache.RepositoryDao
import com.yousufsohail.alligitor.cache.model.RepositoryEntityMapper
import com.yousufsohail.alligitor.domain.DataState
import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.model.RepositoryDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositories(
    private val repositoryService: RepositoryService,
    private val dtoMapper: RepositoryDtoMapper,
    private val repositoryDao: RepositoryDao,
    private val entityMapper: RepositoryEntityMapper,
) {
    fun execute(page: Int): Flow<DataState<List<Repository>>> = flow {
        try {
            emit(DataState.loading())

            val repositoriesFromNetwork = dtoMapper.toDomainList(repositoryService.search(page = page).repositories)

            repositoryDao.insertRepositories(entityMapper.fromDomainList(repositoriesFromNetwork))

            val repositories = repositoryDao.getAllRecipes(page)

            emit(DataState.success(entityMapper.toDomainList(repositories)))

        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown error"))
        }
    }
}
