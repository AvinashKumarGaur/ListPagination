package com.example.demopagination.remot

// GithubRepository.kt

import com.example.demopagination.model.Items
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


