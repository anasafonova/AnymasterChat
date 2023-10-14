package com.dzaigames.anymasterchat.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dzaigames.anymasterchat.data.database.model.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {
    @Query("select * from messages")
    fun messages(): Flow<List<MessageEntity>>

    @Query("select * from messages where id = :id")
    fun messageById(id: Int): Flow<List<MessageEntity>>

    @Query("UPDATE messages SET isEdited = 1 WHERE id = :id")
    fun markMessageAsEdited(id: Int): Int

    @Query("UPDATE messages SET isRead = 1 WHERE id = :id")
    fun markMessageAsRead(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMessages(items: List<MessageEntity>)

}