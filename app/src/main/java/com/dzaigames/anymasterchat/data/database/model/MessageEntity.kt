package com.dzaigames.anymasterchat.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dzaigames.anymasterchat.data.model.MessageDto

@Entity(tableName = "messages")
data class MessageEntity(@PrimaryKey(autoGenerate = true)
                         @ColumnInfo val id: Int,
                         @ColumnInfo val author: Int,
                         @ColumnInfo var message: String = "",
                         @ColumnInfo var isEdited: Boolean = false,
                         @ColumnInfo var createdAt: Long = 0,
                         @ColumnInfo var updatedAt: Long = 0,
                         @ColumnInfo var isRead: Boolean = false,
                         @ColumnInfo var isDelivered: Boolean = false
)

fun MessageEntity.toDto(): MessageDto {
    return MessageDto(
        id = id,
        author = author,
        message = message,
        isEdited = isEdited,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isRead = isRead,
        isDelivered = isDelivered
    )
}