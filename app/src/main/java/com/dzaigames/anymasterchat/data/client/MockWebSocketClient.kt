package com.dzaigames.anymasterchat.data.client

import android.util.Log
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.mockito.Mockito.mock
import javax.inject.Inject

private const val TAG = "MockWebSocketClient"


class MockWebSocketClient @Inject constructor(
    private val messagesRepository: MessagesRepository
) : WebSocketClient {

    private val webSocketListener = WebSocketTransport(
        okHttpClient = mock(OkHttpClient::class.java), // error
        onMessage = this::receive
    )

    override fun connect() {
        webSocketListener.connectWebSocket("")
    }

    override fun disconnect() {
        Log.i(TAG, "WebSocket disconnected")
    }

    private fun receive(text: String) {
        runBlocking(context = Dispatchers.IO) {
            val now = System.currentTimeMillis()
            val receivedMessage = MessageDto(
                id = messagesRepository.getLastMessageId() + 1,
                author = 2,
                message = text,
                createdAt = now,
                updatedAt = now
            )
            messagesRepository.addMessage(receivedMessage)
        }
    }

    override fun send(message: MessageDto) {
        Log.i(TAG, "Send message with text: ${message.message}")
        if (webSocketListener.sendMessage(message.message)) {
            messagesRepository.addMessage(message)
            sendReplay(message)
        } else {
            Log.e(TAG, "Error sending message")
        }
    }

    private fun sendReplay(message: MessageDto) {
        receive("Hello! I've received your message with text: ${message.message}")
    }

}