package com.yousufsohail.alligitor.presentation.ui.repository_list

sealed class RepositoryListEvent {
    object NewSearchEvent : RepositoryListEvent()
    object NextPageEvent : RepositoryListEvent()
}
