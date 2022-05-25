package com.yousufsohail.alligitor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yousufsohail.alligitor.R

@Composable
fun RetryView(onRetryClicked: () -> Unit) {

    val retryAnimation by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.retry_animation))

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(retryAnimation)
        Text(
            text = stringResource(R.string.retry_title),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = stringResource(R.string.retry_subtitle),
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(top = 8.dp)
        )
        OutlinedButton(
            onClick = onRetryClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            Text(text = stringResource(R.string.cta_retry))
        }
    }
}
