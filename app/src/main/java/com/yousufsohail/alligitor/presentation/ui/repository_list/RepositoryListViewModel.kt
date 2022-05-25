package com.yousufsohail.alligitor.presentation.ui.repository_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.presentation.REPOSITORY_LIST_PAGE_SIZE
import com.yousufsohail.alligitor.presentation.ui.repository_list.RepositoryListEvent.FetchEvent
import com.yousufsohail.alligitor.presentation.ui.repository_list.RepositoryListEvent.NextPageEvent
import com.yousufsohail.alligitor.presentation.ui.repository_list.RepositoryListEvent.RefreshFetchEvent
import com.yousufsohail.alligitor.repository.RepositoryRepository
import com.yousufsohail.alligitor.usercase.repository_list.SearchRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private val searchRepositories: SearchRepositories,
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    val TAG = RepositoryListViewModel::class.java.simpleName

    val repositories: MutableState<List<Repository>> = mutableStateOf(listOf())

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)

    private var repositoryListScrollPosition = 0

    init {
        onTriggerEvent(FetchEvent)
    }

    fun onTriggerEvent(event: RepositoryListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is FetchEvent -> {
                        fetch(false)
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    RefreshFetchEvent -> {
                        fetch(true)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
    }

    private fun fetch(forceRefresh: Boolean) {
        // New search. Reset the state
        resetSearchState()

        searchRepositories.execute(forceRefresh, page.value).onEach { dataState ->
            loading.value = dataState.loading
            dataState.data?.let { list ->
                repositories.value = list
            }
            dataState.error?.let { error ->
                TODO("Show error UI")
            }
        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        // prevent duplicate event due to recompose happening to quickly
        if ((repositoryListScrollPosition + 1) >= (page.value * REPOSITORY_LIST_PAGE_SIZE)) {
            incrementPage()
            Log.d(TAG, "nextPage: triggered: ${page.value}")

            if (page.value > 1) {
                searchRepositories.execute(false, page.value).onEach { dataState ->
                    loading.value = dataState.loading
                    dataState.data?.let { list ->
                        appendRepositories(list)
                    }
                    dataState.error?.let {}
                }.launchIn(viewModelScope)
            }
        }
    }

    /**
     * Called when a new search is executed.
     */
    private fun resetSearchState() {
        repositories.value = listOf()
        page.value = 1
        onChangeRepositoryScrollPosition(0)
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
