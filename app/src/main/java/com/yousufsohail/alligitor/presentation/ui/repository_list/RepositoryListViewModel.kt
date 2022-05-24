package com.yousufsohail.alligitor.presentation.ui.repository_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.presentation.REPOSITORY_LIST_PAGE_SIZE
import com.yousufsohail.alligitor.repository.RepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    val TAG = RepositoryListViewModel::class.java.simpleName

    val repositories: MutableState<List<Repository>> = mutableStateOf(listOf())

    val query = mutableStateOf("language")

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)

    var repositoryListScrollPosition = 0

    init {
        search(1)
    }

    private fun search(page: Int) {
        viewModelScope.launch {
            loading.value = true
            val result = repositoryRepository.search(query = query.value, page)
            repositories.value = result
            loading.value = false
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            // prevent duplicate event due to recompose happening to quickly
            if ((repositoryListScrollPosition + 1) >= (page.value * REPOSITORY_LIST_PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                Log.d(TAG, "nextPage: triggered: ${page.value}")

                if (page.value > 1) {
                    val result = repositoryRepository.search(page = page.value, query = query.value)
                    Log.d(TAG, "search: appending")
                    appendRepositories(result)
                }
                loading.value = false
            }
        }
    }

    /**
     * Append new repositories to the current list of repositories
     */
    private fun appendRepositories(repositories: List<Repository>) {
        val current = ArrayList(this.repositories.value)
        current.addAll(repositories)
        this.repositories.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeRepositoryScrollPosition(position: Int) {
        repositoryListScrollPosition = position
    }
}
