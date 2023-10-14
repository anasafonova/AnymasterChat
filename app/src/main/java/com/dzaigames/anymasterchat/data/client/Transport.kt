package com.dzaigames.anymasterchat.data.client
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.mockito.internal.matchers.Null

private const val TAG = "WebSocketClient"

class WebSocketTransport(private val okHttpClient: OkHttpClient, val onMessage: (String) -> Unit) {
    lateinit var webSocket: WebSocket

    fun connectWebSocket(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        webSocket = okHttpClient.newWebSocket(request, createWebSocketListener())
    }

    fun sendMessage(message: String): Boolean {
        return webSocket.send(message)
    }

    private fun createWebSocketListener() = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
            Log.i(TAG, "WebSocket connected")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            onMessage(text)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            // WebSocket connection is closed
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
            // Handle connection failure
        }
    }
}
