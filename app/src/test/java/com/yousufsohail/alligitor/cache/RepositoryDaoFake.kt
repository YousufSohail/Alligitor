package com.yousufsohail.alligitor.cache

import com.yousufsohail.alligitor.cache.model.RepositoryEntity

class RepositoryDaoFake(private val databaseFake: AlligitorDatabaseFake) : RepositoryDao {

    override suspend fun insertRepository(repository: RepositoryEntity): Long {
        databaseFake.repositories.add(repository)
        return 1
    }

    override suspend fun insertRepositories(repositories: List<RepositoryEntity>): LongArray {
        databaseFake.repositories.addAll(repositories)
        return longArrayOf(1)
    }

    override suspend fun getRepositoryById(id: Long): RepositoryEntity? {
        return databaseFake.repositories.find { it.id == id }
    }

    override suspend fun deleteRepositories(ids: List<Long>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllRepositories() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRepository(id: Long): Int {
        TODO("Not yet implemented")
    }

    override suspend fun searchRepositories(query: String, page: Int, pageSize: Int): List<RepositoryEntity> {
        return databaseFake.repositories
    }

    override suspend fun getAllRepositories(page: Int, pageSize: Int): List<RepositoryEntity> {
        return databaseFake.repositories
    }
}
