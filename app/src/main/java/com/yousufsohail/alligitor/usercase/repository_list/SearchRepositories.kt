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
    fun execute(forceRefresh: Boolean, page: Int): Flow<DataState<List<Repository>>> = flow {
        try {
            emit(DataState.loading())

            if (forceRefresh) {
                val repositoriesFromNetwork = dtoMapper.toDomainList(repositoryService.search(page = 1).repositories)
                repositoryDao.deleteAllRepositories()
                repositoryDao.insertRepositories(entityMapper.fromDomainList(repositoriesFromNetwork))
                emit(DataState.success(entityMapper.toDomainList(repositoryDao.getAllRepositories(page))))
            } else {
                val repositories = repositoryDao.getAllRepositories(page)
                if (repositories.isEmpty()) {
                    val repositoriesFromNetwork =
                        dtoMapper.toDomainList(repositoryService.search(page = page).repositories)
                    repositoryDao.insertRepositories(entityMapper.fromDomainList(repositoriesFromNetwork))
                    emit(DataState.success(entityMapper.toDomainList(repositoryDao.getAllRepositories(page))))
                } else {
                    emit(DataState.success(entityMapper.toDomainList(repositories)))
                }
            }

        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown error"))
        }
    }
}
