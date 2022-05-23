package com.yousufsohail.alligitor.presentation.ui.repository_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.yousufsohail.alligitor.repository.RepositoryRepository

class RepositoryListViewModel @ViewModelInject constructor(
    private val repositoryRepository: RepositoryRepository
) : ViewModel()
