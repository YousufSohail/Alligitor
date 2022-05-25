package com.yousufsohail.alligitor.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yousufsohail.alligitor.cache.model.RepositoryEntity
import com.yousufsohail.alligitor.presentation.REPOSITORY_LIST_PAGE_SIZE

@Dao
interface RepositoryDao {

    @Insert
    suspend fun insertRepository(repository: RepositoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRepositories(repositories: List<RepositoryEntity>): LongArray

    @Query("SELECT * FROM repositories WHERE id = :id")
    suspend fun getRepositoryById(id: Long): RepositoryEntity

    @Query("DELETE FROM repositories WHERE id IN (:ids)")
    suspend fun deleteRepositories(ids: List<Long>): Int

    @Query("DELETE FROM repositories")
    suspend fun deleteAllRepositories()

    @Query("DELETE FROM repositories WHERE id = :id")
    suspend fun deleteRepository(id: Long): Int

    /**
     * Retrieve repositories for a particular page.
     * Ex: page = 2 retrieves repositories from 30-60.
     * Ex: page = 3 retrieves repositories from 60-90
     */
    @Query(
        """
        SELECT * FROM repositories 
        WHERE name LIKE '%' || :query || '%'
        OR description LIKE '%' || :query || '%'  
        ORDER BY date_updated DESC LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
        """
    )
    suspend fun searchRecipes(
        query: String,
        page: Int,
        pageSize: Int = REPOSITORY_LIST_PAGE_SIZE
    ): List<RepositoryEntity>

    /**
     * Same as 'searchRecipes' function, but no query.
     */
    @Query(
        """
        SELECT * FROM repositories 
        ORDER BY date_updated DESC LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
    """
    )
    suspend fun getAllRecipes(
        page: Int,
        pageSize: Int = REPOSITORY_LIST_PAGE_SIZE
    ): List<RepositoryEntity>
}
