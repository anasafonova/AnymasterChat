package com.dzaigames.anymasterchat.data.model

import com.dzaigames.anymasterchat.data.database.model.MessageEntity

data class MessageDto(
    val id: Int,
    val author: Int,
    var message: String,
    var isEdited: Boolean,
    var createdAt: Long,
    var updatedAt: Long,
    var isRead: Boolean,
    var isDelivered: Boolean
)

fun MessageDto.toEntity(): MessageEntity {
    return MessageEntity(
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