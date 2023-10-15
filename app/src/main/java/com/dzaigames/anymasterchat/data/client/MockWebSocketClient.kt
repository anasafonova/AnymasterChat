package com.dzaigames.anymasterchat.data.client

import android.util.Log
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import javax.inject.Inject

private const val TAG = "MockWebSocketClient"

class MockWebSocketClient @Inject constructor(
    private val messagesRepository: MessagesRepository
) : WebSocketClient {
    override fun connect() {
        Log.i(TAG, "WebSocket connected")
    }

    override fun disconnect() {
        Log.i(TAG, "WebSocket disconnected")
    }

    override suspend fun send(message: MessageDto) {
        Log.i(TAG, "Send message with text: ${message.message}")
        messagesRepository.addMessage(message)

        sendReplay(message = message)
    }

    private fun sendReplay(message: MessageDto) {
        val echoMessage = MessageDto(
            id = message.id + 1,
            author = 2,
            message = "Hello! I've received your message with text: ${message.message}",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        messagesRepository.addMessage(echoMessage)
    }

}