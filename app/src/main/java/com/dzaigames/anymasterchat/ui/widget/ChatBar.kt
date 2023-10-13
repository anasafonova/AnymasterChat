package com.dzaigames.anymasterchat.ui.widget

import android.graphics.Bitmap
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme

@Composable
@Preview
fun ChatBarPreview() {
    AnymasterChatTheme {
        ChatBar(
            userName = "Daniel",
            userSurname = "Moris",
            profileBitmap = null,
            isVerified = true,
            taskText = "Cleaning of a two-room apartment",
            taskPrice = 1498.0,
            changeOffer = { }
        )
    }
}

@Composable
fun ChatBar(
    userName: String,
    userSurname: String,
    profileBitmap: Bitmap?,
    isVerified: Boolean,
    taskText: String,
    taskPrice: Double,
    changeOffer: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(122.dp)
            .background(color = Color.White)
    ) {
        Column {
            NavigationBar(onBackClick = {  }, onMenuClick = {  }) {
                UserLayout(
                    userName = userName,
                    userSurname = userSurname,
                    profileBitmap = profileBitmap,
                    isVerified = isVerified
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = MaterialTheme.colorScheme.tertiary)
            )
            TaskLayout(
                taskText = taskText,
                taskPrice = taskPrice
            ) {
                changeOffer()
            }
            Spacer(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = MaterialTheme.colorScheme.tertiary)
            )
        }
    }
}