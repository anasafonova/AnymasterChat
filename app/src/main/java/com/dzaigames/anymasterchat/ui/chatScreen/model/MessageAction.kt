package com.dzaigames.anymasterchat.ui.chatScreen.model

import androidx.compose.ui.graphics.Color

data class MessageAction(
    val actionText: String,
    val actionIcon: Int,
    val onClick: () -> Unit
)
