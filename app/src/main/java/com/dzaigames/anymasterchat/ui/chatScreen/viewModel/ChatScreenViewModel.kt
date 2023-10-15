package com.dzaigames.anymasterchat.ui.chatScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzaigames.anymasterchat.data.manager.UserPreferencesManager
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.model.isMineMessage
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.domain.usecase.SendMessageUseCase
import com.dzaigames.anymasterchat.ui.chatScreen.model.ChatScreenState
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessagesUiState
import com.dzaigames.anymasterchat.utils.PresetItemsInteractor
import com.dzaigames.anymasterchat.utils.WhileUiSubscribed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatScreenViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository,
    private val presetItemsInteractor: PresetItemsInteractor,
    userPreferencesManager: UserPreferencesManager,
    private val sendMessageUseCase: SendMessageUseCase
): ViewModel() {
    private val isRefreshing = MutableStateFlow(false)

    private val isError = MutableStateFlow(false)

    private val isEdited = MutableStateFlow(false)

    private val userId = userPreferencesManager.userId

    val messages: SharedFlow<List<MessageDto>> = messagesRepository
        .messages

    lateinit var messageActions: List<MessageAction>

    val uiState: StateFlow<ChatScreenState> = combine(
        isRefreshing,
        isError,
        isEdited
    ) { refreshing, errorOccured, edited ->
        val messagesState: MessagesUiState = MessagesUiState.Success

        ChatScreenState(
            messages = messagesState,
            isRefreshing = refreshing,
            isError = errorOccured,
            isEdited = edited
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = ChatScreenState(
                messages = MessagesUiState.Loading,
                isRefreshing = false,
                isError = false,
                isEdited = false
            )
        )

    fun preset() {
        viewModelScope.launch(Dispatchers.IO) {
            presetItemsInteractor.preset()
            messageActions = messagesRepository.messageActions
        }
    }

    fun checkMessageIsMine(message: MessageDto): Boolean {
        return message.isMineMessage(userId = userId)
    }

    fun onEdit() {
        viewModelScope.launch {
            isEdited.emit(true)
        }
    }

    fun onMessageCompleted() {
        viewModelScope.launch {
            isEdited.emit(false)
        }
    }

    fun onMessageEdited(message: MessageDto) {
        viewModelScope.launch(Dispatchers.IO) {
            sendMessageUseCase.invoke(
                message.copy(
                    updatedAt = System.currentTimeMillis(),
                    isEdited = true
                )
            )
        }
    }

    fun onMessageSend(id: Int, text: String) {
        val message = MessageDto(
            id = id,
            author = 1,
            message = text,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        viewModelScope.launch(Dispatchers.IO) {
            sendMessageUseCase.invoke(message)
        }
    }
}