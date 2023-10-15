package com.dzaigames.anymasterchat.di

import com.dzaigames.anymasterchat.data.database.dao.MessagesDao
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.data.repo.OfflineFirstMessagesRepository
import com.dzaigames.anymasterchat.utils.PresetFakeMessageDataInteractor
import com.dzaigames.anymasterchat.utils.PresetItemsInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MessagesRepositoryModule {
    @Provides
    @Singleton
    fun provideMessagesRepository(messagesDao: MessagesDao): MessagesRepository =
        OfflineFirstMessagesRepository(messagesDao = messagesDao)

    @Provides
    @Singleton
    fun providePreset(messagesRepository: MessagesRepository): PresetItemsInteractor =
        PresetItemsInteractor(repository = messagesRepository)

    @Provides
    @Singleton
    fun provideFakeMessagePreset(messagesRepository: MessagesRepository): PresetFakeMessageDataInteractor =
        PresetFakeMessageDataInteractor(repository = messagesRepository)
}