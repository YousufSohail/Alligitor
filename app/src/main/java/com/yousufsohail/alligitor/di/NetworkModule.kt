package com.yousufsohail.alligitor.di

import com.google.gson.GsonBuilder
import com.yousufsohail.alligitor.network.RepositoryService
import com.yousufsohail.alligitor.network.model.RepositoryDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRepositoryMapper(): RepositoryDtoMapper {
        return RepositoryDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRepositoryService(): RepositoryService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RepositoryService::class.java)
    }

}
