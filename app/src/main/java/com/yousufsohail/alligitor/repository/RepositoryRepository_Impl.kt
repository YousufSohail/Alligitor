package com.yousufsohail.alligitor.repository

import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.model.RepositoryMapper

class RepositoryRepository_Impl (
    private val repositoryService: RepositoryService,
    private val mapper: RepositoryMapper,
): RepositoryRepository {

    override suspend fun search(query: String, page: Int): List<Repository> {
        return mapper.fromEntityList(repositoryService.search(query, page).repositories)
    }

    override suspend fun get(ownerLogin: String, repoName: String): Repository {
        return mapper.mapFromEntity(repositoryService.get(ownerLogin, repoName))
    }

}
