package com.dzaigames.anymasterchat.utils

import com.dzaigames.anymasterchat.R
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import javax.inject.Inject

class PresetItemsInteractor @Inject constructor(private val repository: MessagesRepository) {
    suspend fun preset() {
        repository.presetMessages(getMessagesPreset())
        repository.presetMessageActions(getMessageActionsPreset())
    }

    private fun getMessagesPreset(): List<MessageDto> {
        return listOf(
            MessageDto(
                id = 1,
                author = 1,
                message = "Hi Daniel, my name is Eleni, I am a professional cleaner with 10 years of experience. I can come to you tomorrow morning. 2 bedroom apartment costs 30 euros and takes about 3 hours. Is it okay for you?",
                isEdited = false,
                createdAt = 1697195631000,
                updatedAt = 1697195631000,
                isRead = true,
                isDelivered = true
            ),
            MessageDto(
                id = 2,
                author = 1,
                message = "I can also wash terrace, windows and balcony for extra 20 euros if needed.",
                isEdited = false,
                createdAt = 1697195633000,
                updatedAt = 1697195633000,
                isRead = true,
                isDelivered = true
            ),
            MessageDto(
                id = 3,
                author = 2,
                message = "Hi Eleni, sounds good for me, tomorrow morning is perfect.",
                isEdited = false,
                createdAt = 1697195797000,
                updatedAt = 1697195797000,
                isRead = true,
                isDelivered = true
            )
        )
    }

    private fun getMessageActionsPreset(): List<MessageAction> {
        return listOf(
            MessageAction(
                actionText = "Save as template",
                actionIcon = R.drawable.ic_add_template,
                onClick = {  }
            ),
            MessageAction(
                actionText = "Copy",
                actionIcon = R.drawable.ic_copy,
                onClick = {  }
            ),
            MessageAction(
                actionText = "Edit",
                actionIcon = R.drawable.ic_edit,
                onClick = {  }
            ),
            MessageAction(
                actionText = "Delete",
                actionIcon = R.drawable.ic_delete,
                onClick = {  }
            )
        )
    }
}