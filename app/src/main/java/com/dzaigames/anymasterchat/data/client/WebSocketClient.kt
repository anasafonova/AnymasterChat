package com.dzaigames.anymasterchat.data.client

import com.dzaigames.anymasterchat.data.model.MessageDto

interface WebSocketClient {
    fun connect()
    fun disconnect()
    suspend fun send(message: MessageDto)
}