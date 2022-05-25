package com.yousufsohail.alligitor.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yousufsohail.alligitor.R
import com.yousufsohail.alligitor.domain.model.Repository

@Preview
@Composable
fun PreviewRepositoryListItem() {
    RepositoryListItem(
        repository = Repository(
            20929025,
            "TypeScript",
            "Type script description Type script description Type script description",
            "TypeScript",
            80870,
            "microsoft",
            "https://avatars.githubusercontent.com/u/6154722?v=4"
        )
    ) {

    }
}

@Composable
fun RepositoryListItem(
    repository: Repository,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(repository.userAvatar)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_baseline_person_18),
                    contentDescription = stringResource(R.string.owner_picture),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                ) {
                    repository.userName.let { username ->
                        Text(
                            text = username,
                            style = MaterialTheme.typography.body2
                        )
                    }
                    repository.name.let { repoName ->
                        Text(
                            text = repoName,
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.h6
                        )
                    }
                    repository.description.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(top = 8.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        repository.language.let { language ->
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(color = Color.Red)
                            )
                            Text(
                                text = language,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        repository.stargazersCount.let { starCount ->
                            Image(
                                painterResource(id = R.drawable.ic_star), contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .size(18.dp)
                            )
                            Text(
                                text = starCount.toString(),
                                modifier = Modifier.padding(start = 6.dp)
                            )
                        }
                    }
                }
            }
            Divider(color = MaterialTheme.colors.secondary, thickness = 1.dp)
        }
    }
}
