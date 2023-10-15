package com.dzaigames.anymasterchat.util

import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import javax.inject.Inject

class PresetFakeMessageDataInteractor @Inject constructor(private val repository: MessagesRepository) {
    suspend fun preset() {
        repository.presetMessages(getMessagesPreset())
    }

    private fun getMessagesPreset(): List<MessageDto> {
        return listOf(
            MessageDto(
                id = 1,
                author = 1,
                message = "Hi Daniel, my name is Eleni, I am a professional cleaner with 10 years of experience. I can come to you tomorrow morning. 2 bedroom apartment costs 30 euros and takes about 3 hours. Is it okay for you?",
                isEdited = false,
                createdAt = 1697286859000  - 120000,
                updatedAt = 1697286859000 - 120000,
                isRead = true,
                isDelivered = true
            ),
            MessageDto(
                id = 2,
                author = 1,
                message = "I can also wash terrace, windows and balcony for extra 20 euros if needed.",
                isEdited = false,
                createdAt = 1697286859000 - 60000,
                updatedAt = 1697286859000 - 60000,
                isRead = true,
                isDelivered = true
            ),
            MessageDto(
                id = 3,
                author = 2,
                message = "Hi Eleni, sounds good for me, tomorrow morning is perfect.",
                isEdited = false,
                createdAt = 1697286859000,
                updatedAt = 1697286859000,
                isRead = true,
                isDelivered = true
            )
        )
    }
}