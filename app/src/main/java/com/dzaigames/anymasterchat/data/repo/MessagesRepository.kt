package com.dzaigames.anymasterchat.data.repo

import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface MessagesRepository {
    val messages: SharedFlow<List<MessageDto>>
    var messageActions: List<MessageAction>

    fun getMessages(): Flow<List<MessageDto>>

    suspend fun refreshMessages()

    suspend fun presetMessages(items: List<MessageDto>)

    fun presetMessageActions(items: List<MessageAction>)

}