package com.dzaigames.anymasterchat.ui.chatScreen.model

data class MessageAction(
    val actionType: ActionType,
    val actionText: String,
    val actionIcon: Int,
    val isCritical: Boolean = false
)

enum class ActionType(val id: Int) {
    SaveAsTemplate(0),
    Copy(1),
    Edit(2),
    Delete(3);
}