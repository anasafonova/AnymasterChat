package com.dzaigames.anymasterchat.di

import android.content.Context
import androidx.room.Room
import com.dzaigames.anymasterchat.data.database.ChatDatabase
import com.dzaigames.anymasterchat.data.database.dao.MessagesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    private lateinit var database: ChatDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): ChatDatabase {
        database = Room.databaseBuilder(context, ChatDatabase::class.java, "chat-database").build()
        return database
    }

    @Singleton
    @Provides
    fun providesMessagesDao(database: ChatDatabase): MessagesDao {
        return database.messagesDao()
    }
}