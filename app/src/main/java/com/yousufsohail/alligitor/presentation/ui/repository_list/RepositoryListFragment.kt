package com.yousufsohail.alligitor.presentation.ui.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yousufsohail.alligitor.presentation.components.LoadingRepositoryListShimmer
import com.yousufsohail.alligitor.presentation.components.RepositoryListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryListFragment : Fragment() {

    val viewModel: RepositoryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val searchResult = viewModel.repositories.value
                val loading = viewModel.loading.value

                Box(modifier = Modifier.fillMaxSize()) {
                    if (loading) {
                        LoadingRepositoryListShimmer(imageHeight = 250.dp)
                    } else {
                        LazyColumn {
                            itemsIndexed(items = searchResult) { index, repository ->
                                RepositoryListItem(repository = repository, onClick = {})
                            }
                        }
                    }
                }
            }
        }
    }
}
