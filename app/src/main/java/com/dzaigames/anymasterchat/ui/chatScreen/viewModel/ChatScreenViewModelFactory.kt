package com.dzaigames.anymasterchat.ui.chatScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzaigames.anymasterchat.data.manager.UserPreferencesManager
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.domain.usecase.SendMessageUseCase
import com.dzaigames.anymasterchat.util.PresetItemsInteractor
import javax.inject.Inject

class ChatScreenViewModelFactory @Inject constructor(
    private val repository: MessagesRepository,
    private val presetInteractor: PresetItemsInteractor,
    private val userPreferencesManager: UserPreferencesManager,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatScreenViewModel(
            messagesRepository = repository,
            presetItemsInteractor = presetInteractor,
            userPreferencesManager = userPreferencesManager,
            sendMessageUseCase = sendMessageUseCase
        ) as T
    }
}