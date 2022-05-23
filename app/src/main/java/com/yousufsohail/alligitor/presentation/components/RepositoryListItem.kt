package com.yousufsohail.alligitor.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yousufsohail.alligitor.domain.model.Repository

@Composable
fun RepositoryListItem(
    repository: Repository,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 6.dp, bottom = 6.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            Row {
                Image(
                    painter = painterResource(android.R.drawable.ic_menu_info_details),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column {
                    repository.userName?.let { Text(text = it) }
                    repository.name?.let { Text(text = it) }
                    repository.description?.let { Text(text = it) }
                    repository.description?.let { Text(text = it) }
                }

            }
            Divider(color = MaterialTheme.colors.secondary, thickness = 1.dp)
        }
    }

}
