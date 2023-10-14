package com.dzaigames.anymasterchat.data.client

import com.dzaigames.anymasterchat.data.model.MessageDto

interface WebSocketClient {
    fun connect()
    fun disconnect()
    fun send(message: MessageDto)
}