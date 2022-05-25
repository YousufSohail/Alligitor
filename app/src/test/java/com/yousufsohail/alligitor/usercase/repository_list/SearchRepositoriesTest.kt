package com.yousufsohail.alligitor.usercase.repository_list

import com.google.gson.GsonBuilder
import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.data.MockWebServerResponses.repositoriesListResponse
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

    // system in test
//    private val searchRepositories: SearchRepositories

    //dependencies
    private lateinit var repositoryService: RepositoryService

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
    }

    @Test
    fun mockWebServerSetup() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(repositoriesListResponse)
        )
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

}
