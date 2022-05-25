package com.yousufsohail.alligitor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRepositoryListItem(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    cardHeight: Dp,
    gradientWidth: Float,
    padding: Dp
) {
    val brush = linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )
    Row(modifier = Modifier.padding(padding)) {
        Spacer(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(brush)
        )
        Column(modifier = Modifier.padding(start = padding)) {
            Surface(
                shape = MaterialTheme.shapes.small,
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(cardHeight / 4)
                        .background(brush = brush)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(cardHeight)
                        .background(brush = brush)
                )
            }
        }
    }

}


