package com.dzaigames.anymasterchat.util

import com.dzaigames.anymasterchat.R
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.ui.chatScreen.model.ActionType
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import javax.inject.Inject

class PresetItemsInteractor @Inject constructor(private val repository: MessagesRepository) {
    fun preset() {
        repository.presetMessageActions(getMessageActionsPreset())
    }

    private fun getMessageActionsPreset(): List<MessageAction> {
        return listOf(
            MessageAction(
                actionType = ActionType.SaveAsTemplate,
                actionText = "Save as template",
                actionIcon = R.drawable.ic_add_template
            ),
            MessageAction(
                actionType = ActionType.Copy,
                actionText = "Copy",
                actionIcon = R.drawable.ic_copy
            ),
            MessageAction(
                actionType = ActionType.Edit,
                actionText = "Edit",
                actionIcon = R.drawable.ic_edit
            ),
            MessageAction(
                actionType = ActionType.Delete,
                actionText = "Delete",
                actionIcon = R.drawable.ic_delete,
                isCritical = true
            )
        )
    }
}