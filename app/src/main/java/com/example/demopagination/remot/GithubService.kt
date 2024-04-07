package com.example.demopagination.remot

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// GithubService.kt
interface GithubService {
    @GET("/search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("per_page") page: Int
    ): RepositoryResponse
   // https://api.github.com/search/repositories?q=Q&per_page=2
   // https://api.github.com/search/repositories?q=Q
    companion object {
        fun create(): GithubService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GithubService::class.java)
        }
    }
}

