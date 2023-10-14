package com.dzaigames.anymasterchat.ui.chatScreen.model

import androidx.compose.runtime.Immutable

data class ChatScreenState(
    val messages: MessagesUiState,
    val isRefreshing: Boolean,
    val isError: Boolean,
    val isEdited: Boolean
)

@Immutable
sealed interface MessagesUiState {
    object Success : MessagesUiState //(val messages: List<MessageDto>) : MessagesUiState
    object Loading : MessagesUiState
    object Error : MessagesUiState
}