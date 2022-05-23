package com.yousufsohail.alligitor.presentation.ui.repository_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousufsohail.alligitor.domain.model.Repository
import com.yousufsohail.alligitor.repository.RepositoryRepository
import kotlinx.coroutines.launch

class RepositoryListViewModel @ViewModelInject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel() {

    val repositories: MutableState<List<Repository>> = mutableStateOf(ArrayList())

    fun search(query: String, page: Int) {

        viewModelScope.launch {
            val result = repositoryRepository.search(query, page)
            repositories.value = result
        }

    }

}
