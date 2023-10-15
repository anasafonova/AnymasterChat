package com.dzaigames.anymasterchat.data.repo

import com.dzaigames.anymasterchat.data.database.dao.MessagesDao
import com.dzaigames.anymasterchat.data.database.model.toDto
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.model.toEntity
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OfflineFirstMessagesRepository @Inject constructor(
    private val messagesDao: MessagesDao,
) : MessagesRepository {

    private val repoScope = CoroutineScope(Dispatchers.IO)

    override val messages by lazy {
        getMessages()
            .shareIn(
                scope = repoScope,
                replay = 1,
                started = SharingStarted.WhileSubscribed()
                )
    }

    override var messageActions: List<MessageAction> = listOf()

    override fun getMessages(): Flow<List<MessageDto>> {
        return messagesDao.messages()
            .flowOn(Dispatchers.IO)
            .map { messages ->
                messages.map { it.toDto() }
                    .sortedBy { it.createdAt }
            }.onEach {
                if (it.isEmpty()) {
                    refreshMessages()
                }
            }
    }

    override fun addMessage(item: MessageDto) {
        messagesDao.insertAllMessages(
            items = listOf(
                item.toEntity()
            )
        )
    }

    override suspend fun refreshMessages() {
        // Refresh message list from server via REST API
    }

    override suspend fun presetMessages(items: List<MessageDto>) {
        messagesDao.insertAllMessages(items = items.map { it.toEntity() })
    }

    override fun presetMessageActions(items: List<MessageAction>) {
        messageActions = items
    }

    override suspend fun getLastMessageId(): Int {
        return messages.first().sortedByDescending { it.id }[0].id
    }
}