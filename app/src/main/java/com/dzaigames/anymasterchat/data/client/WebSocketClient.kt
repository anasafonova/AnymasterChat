package com.dzaigames.anymasterchat.data.client

import com.dzaigames.anymasterchat.data.model.MessageDto

interface WebSocketClient {
//    val isSocketConnected: StateFlow<Boolean>
    fun connect()
    fun disconnect()
    suspend fun send(message: MessageDto)
}