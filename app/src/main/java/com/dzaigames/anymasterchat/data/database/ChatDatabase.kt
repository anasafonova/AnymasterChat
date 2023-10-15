package com.dzaigames.anymasterchat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dzaigames.anymasterchat.data.database.dao.MessagesDao
import com.dzaigames.anymasterchat.data.database.model.MessageEntity

@Database(
    entities = [MessageEntity::class],

    version = ChatDatabase.DATABASE_VERSION,

    exportSchema = false
)

abstract class ChatDatabase : RoomDatabase() {

    abstract fun messagesDao() : MessagesDao

    companion object {
        const val DATABASE_VERSION: Int = 1
    }

}