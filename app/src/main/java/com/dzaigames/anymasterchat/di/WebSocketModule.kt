package com.dzaigames.anymasterchat.di

import com.dzaigames.anymasterchat.data.client.MockWebSocketClient
import com.dzaigames.anymasterchat.data.client.WebSocketClient
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.domain.usecase.SendMessageUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WebSocketClientModule {

    @Provides
    @Singleton
    fun provideWebSocketClient(messagesRepository: MessagesRepository): WebSocketClient {
        return MockWebSocketClient(
            messagesRepository = messagesRepository
        )
    }

    @Provides
    fun provideSendMessageUsecase(webSocketClient: MockWebSocketClient): SendMessageUseCase {
        return SendMessageUseCase(webSocketClient = webSocketClient)
    }
}