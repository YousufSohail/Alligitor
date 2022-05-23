package com.yousufsohail.alligitor.presentation.ui.repository_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.repository.RepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    val repositories: MutableState<List<Repository>> = mutableStateOf(listOf())

    init {
        search("language", 1)
    }

    fun search(query: String, page: Int) {
        viewModelScope.launch {
            val result = repositoryRepository.search(query, page)
            repositories.value = result
        }
    }
}
