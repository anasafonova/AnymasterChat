package com.dzaigames.anymasterchat.ui.chatScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.utils.PresetItemsInteractor
import javax.inject.Inject

class ChatScreenViewModelFactory @Inject constructor(private val repository: MessagesRepository, private val presetInteractor: PresetItemsInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatScreenViewModel(messagesRepository = repository, presetItemsInteractor = presetInteractor) as T
    }
}