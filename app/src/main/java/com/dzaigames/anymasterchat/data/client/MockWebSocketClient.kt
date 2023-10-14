package com.dzaigames.anymasterchat.data.client

import android.util.Log
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import javax.inject.Inject
import org.mockito.Mock

private const val TAG = "MockWebSocketClient"


class MockWebSocketClient @Inject constructor(
    private val messagesRepository: MessagesRepository
) : WebSocketClient {

    @Mock
    private lateinit var okHttpClient: OkHttpClient

    private val wsManager = WebSocketTransport(
        okHttpClient = okHttpClient,
        onMessage = this::receive
    )

    override fun connect() {
        wsManager.connectWebSocket("")
    }

    override fun disconnect() {
        Log.i(TAG, "WebSocket disconnected")
    }

    fun receive(text: String) {
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
        if (wsManager.sendMessage(message.message)) {
            messagesRepository.addMessage(message)
            sendAnswer(message)
        } else {
            Log.i(TAG, "Error sending message")
        }

        sendAnswer(message = message)
    }

    private fun sendAnswer(message: MessageDto) {
        receive("Hello! I've received your message with text: ${message.message}")
    }

}

// Create the WebSocketClient mock
//val mockWebSocketClient = mock(WebSocketClient::class.java)
//
//// Define a variable to hold the received message
//var receivedMessage: String? = null
//
//// Use Mockito's doAnswer to simulate the WebSocket client's behavior
//doAnswer(object : Answer<Unit> {
//    override fun answer(invocation: InvocationOnMock) {
//        // Get the argument passed to the send method
//        val message = invocation.arguments[0] as String
//
//        // Simulate receiving a message (you can change this logic as needed)
//        receivedMessage = "Response to: $message"
//
//        // You can perform further actions here if needed
//
//    }
//}).`when`(mockWebSocketClient).send(anyString())
//
//// Now, when you call send on the mock, it will store a response in receivedMessage
//val messageToSend = "Hello, WebSocket"
//mockWebSocketClient.send(messageToSend)
//
//// Check the received message
//println(receivedMessage) // Output should be "Response to: Hello, WebSocket"