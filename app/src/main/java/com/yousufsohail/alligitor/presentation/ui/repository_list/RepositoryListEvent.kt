package com.yousufsohail.alligitor.presentation.ui.repository_list

sealed class RepositoryListEvent {
    object FetchEvent : RepositoryListEvent()
    object NextPageEvent : RepositoryListEvent()
    object RefreshFetchEvent : RepositoryListEvent()
}
