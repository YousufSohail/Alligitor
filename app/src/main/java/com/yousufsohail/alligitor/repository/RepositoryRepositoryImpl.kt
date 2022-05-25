package com.yousufsohail.alligitor.repository

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.model.RepositoryDtoMapper

class RepositoryRepositoryImpl(
    private val repositoryService: RepositoryService,
    private val dtoMapper: RepositoryDtoMapper,
) : RepositoryRepository {

    override suspend fun search(query: String, page: Int): List<Repository> {
        return dtoMapper.toDomainList(repositoryService.search(query, page).repositories)
    }

    override suspend fun get(ownerLogin: String, repoName: String): Repository {
        return dtoMapper.mapToDomainModel(repositoryService.get(ownerLogin, repoName))
    }

}
