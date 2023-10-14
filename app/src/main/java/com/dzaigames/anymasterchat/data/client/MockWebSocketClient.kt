package com.dzaigames.anymasterchat.data.client

import android.util.Log
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import javax.inject.Inject

private const val TAG = "MockWebSocketClient"

class MockWebSocketClient @Inject constructor(
    private val messagesRepository: MessagesRepository
) : WebSocketClient {

//    private var _isSocketConnected: MutableStateFlow<Boolean> = MutableStateFlow(
//        true
//    )
//    override val isSocketConnected: StateFlow<Boolean> = _isSocketConnected

    override fun connect() {
        Log.i(TAG, "WebSocket connected")
//        _isSocketConnected.value = true
    }

    override fun disconnect() {
        Log.i(TAG, "WebSocket disconnected")
//        _isSocketConnected.value = false
    }

    override suspend fun send(message: MessageDto) {
        Log.i(TAG, "Send message with text: ${message.message}") // ${_isSocketConnected.value}")
//        if (_isSocketConnected.value) {
        messagesRepository.addMessage(message)

        sendReplay(message = message)
//        }
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