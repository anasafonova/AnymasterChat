package com.dzaigames.anymasterchat.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.cloudy.Cloudy

@Composable
@Preview(showBackground = true)
fun CloudyLayoutPreview() {
    Cloudy(radius = 25) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.Blue
                )
        ) {
            Box(
                modifier = Modifier
                    .width(295.dp)
                    .height(60.dp)
                    .align(Alignment.Center)
                    .background(
                        color = Color.Cyan,
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = "TESTtestTEST",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview
fun NoCloudyLayoutPreview() {
    CloudyLayout(isClouded = false) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.Blue
                )
        ) {
            Box(
                modifier = Modifier
                    .width(295.dp)
                    .height(60.dp)
                    .align(Alignment.Center)
                    .background(
                        color = Color.Cyan,
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = "TESTtestTEST",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CloudyLayout(
    isClouded: Boolean,
    content: @Composable () -> Unit
) {
    if (isClouded) {
        Cloudy(
            radius = 25
        ) {
            content()
        }
    } else {
        content()
    }
}