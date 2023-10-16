package com.dzaigames.anymasterchat.ui.widget.contextMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzaigames.anymasterchat.R
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.ui.chatScreen.model.ActionType
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme

@Composable
@Preview(showBackground = true)
fun MessageContextMenuPreview() {
    AnymasterChatTheme {
        MessageContextMenu(
            selectedMessage = MessageDto(
                id = 0,
                author = 2,
                message = "Test message",
                isEdited = false,
                isRead = true,
                isDelivered = true,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            ),
            actionItems = listOf(
                MessageAction(
                    actionType = ActionType.SaveAsTemplate,
                    actionText = "Save as template",
                    actionIcon = R.drawable.ic_add_template
                ),
                MessageAction(
                    actionType = ActionType.SaveAsTemplate,
                    actionText = "Save as template",
                    actionIcon = R.drawable.ic_add_template
                ),
                MessageAction(
                    actionType = ActionType.SaveAsTemplate,
                    actionText = "Save as template",
                    actionIcon = R.drawable.ic_add_template
                )
            ),
            expanded = true
        )
    }
}

@Composable
fun MessageContextMenu(
    selectedMessage: MessageDto,
    actionItems: List<MessageAction>,
    expanded: Boolean,
    onDismiss: () -> Unit = {  },
    onSaveAsTemplate: (MessageDto) -> Unit = {  },
    onCopy: (MessageDto) -> Unit = {  },
    onEdit: (MessageDto) -> Unit = {  },
    onDelete: (MessageDto) -> Unit = {  }
) {
    var exp by remember {
        mutableStateOf(expanded)
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            exp = false
            onDismiss()
        },
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        actionItems.forEachIndexed { index, item ->
            DropdownMenuItem(
                text = {
                    ContextMenuItem(item = item)
                },
                onClick = {
                    when (item.actionType) {
                        ActionType.SaveAsTemplate -> onSaveAsTemplate(selectedMessage)
                        ActionType.Copy -> onCopy(selectedMessage)
                        ActionType.Edit -> onEdit(selectedMessage)
                        ActionType.Delete -> onDelete(selectedMessage)
                    }
                    onDismiss()
                },
                modifier = Modifier
                    .background(color = Color.White)
            )
            if (index < actionItems.size - 1)
                Divider()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ContextMenuItemPreview() {
    ContextMenuItem(
        item = MessageAction(
            actionType = ActionType.SaveAsTemplate,
            actionText = "Save as a template",
            actionIcon = R.drawable.ic_add_template,
            isCritical = false
        )
    )
}

@Composable
fun ContextMenuItem(
    item: MessageAction
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(40.dp)
    ) {
        Text(
            text = item.actionText,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontWeight = FontWeight(400),
                color = if (item.isCritical) Color(0xFFDE4040) else MaterialTheme.colorScheme.secondary,
            ),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        )

        Icon(
            painter = painterResource(id = item.actionIcon),
            tint = if (item.isCritical) Color(0xFFDE4040) else MaterialTheme.colorScheme.secondary,
            contentDescription = item.actionText,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(24.dp)
                .align(Alignment.CenterEnd)
        )
    }
}