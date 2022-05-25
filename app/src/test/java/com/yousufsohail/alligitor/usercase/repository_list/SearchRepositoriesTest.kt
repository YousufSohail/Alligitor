package com.yousufsohail.alligitor.usercase.repository_list

import com.google.gson.GsonBuilder
import com.yousufsohail.alligitor.cache.AlligitorDatabaseFake
import com.yousufsohail.alligitor.cache.RepositoryDaoFake
import com.yousufsohail.alligitor.cache.model.RepositoryEntityMapper
import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.data.MockWebServerResponses.repositoryListResponse
import com.yousufsohail.alligitor.network.model.RepositoryDtoMapper
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class SearchRepositoriesTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private val alligitorDatabaseFake = AlligitorDatabaseFake()

    // system in test
    private lateinit var searchRepositories: SearchRepositories

    //dependencies
    private lateinit var repositoryService: RepositoryService
    private lateinit var repositoryDaoFake: RepositoryDaoFake
    private val dtoMapper = RepositoryDtoMapper()
    private val entityMapper = RepositoryEntityMapper()

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/")
        repositoryService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RepositoryService::class.java)

        repositoryDaoFake = RepositoryDaoFake(alligitorDatabaseFake)

        searchRepositories = SearchRepositories(
            repositoryService,
            dtoMapper,
            repositoryDaoFake,
            entityMapper
        )
    }

    @Test
    fun getRepositoriesFromNetwork_emitRepositoriesFromCache(): Unit = runBlocking {

        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(repositoryListResponse)
        )

        // confirm the cache is empty to start
        assert(repositoryDaoFake.getAllRepositories(1, 30).isEmpty())

        val flowItems = searchRepositories.execute(false, 1).toList()

        // confirm the cache is no longer empty
        assert(repositoryDaoFake.getAllRepositories(1, 30).isNotEmpty())

        // first emission should be `loading`
        assert(flowItems[0].loading)

        // Second emission should be the list of repositories
        val repositories = flowItems[1].data
        assert(repositories?.size ?: 0 > 0)

        // confirm they are actually Repository objects
        assert(repositories?.get(index = 0) is Repository)

        assert(!flowItems[1].loading) // loading should be false now
    }

    /**
     * Simulate a bad request call and check if error state is emitted
     */
    @Test
    fun getRepositoriesFromNetwork_emitHttpError(): Unit = runBlocking {

        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody("{}")
        )

        val flowItems = searchRepositories.execute(false, 1).toList()

        // first emission should be `loading`
        assert(flowItems[0].loading)

        // Second emission should be the exception
        val error = flowItems[1].error
        assert(error != null)

        assert(!flowItems[1].loading) // loading should be false now
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

}
