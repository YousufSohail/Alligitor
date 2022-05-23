package com.yousufsohail.alligitor.repository

import com.yousufsohail.alligitor.domain.model.Repository

interface RepositoryRepository {

    suspend fun search(query: String, page: Int): List<Repository>

    suspend fun get(ownerLogin: String, repoName: String): Repository

}
