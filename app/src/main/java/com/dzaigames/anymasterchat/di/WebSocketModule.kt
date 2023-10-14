package com.dzaigames.anymasterchat.di

import com.dzaigames.anymasterchat.data.client.MockWebSocketClient
import com.dzaigames.anymasterchat.data.client.WebSocketClient
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import dagger.Module
import dagger.Provides

@Module
class WebSocketClientModule {
    @Provides
    fun provideWebSocketClient(messagesRepository: MessagesRepository): WebSocketClient {
        return MockWebSocketClient(messagesRepository = messagesRepository)
    }
}