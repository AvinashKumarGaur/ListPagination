package com.example.demopagination

// GithubRepository.kt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubRepository {
    private val githubService = GithubService.create()

    suspend fun searchRepositories(query: String, page: Int): List<Items> {
        return withContext(Dispatchers.IO) {
            githubService.searchRepositories(query, page).items
        }
    }
}


