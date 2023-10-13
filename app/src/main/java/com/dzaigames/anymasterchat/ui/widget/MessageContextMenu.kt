package com.dzaigames.anymasterchat.ui.widget

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
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme

@Composable
@Preview(showBackground = true)
fun MessageContextMenuPreview() {
    AnymasterChatTheme {
        MessageContextMenu(
            items = listOf(
                MessageAction(
                    actionText = "Save as template",
                    actionIcon = R.drawable.ic_add_template,
                    onClick = {  }
                ),
                MessageAction(
                    actionText = "Save as template",
                    actionIcon = R.drawable.ic_add_template,
                    onClick = {  }
                ),
                MessageAction(
                    actionText = "Save as template",
                    actionIcon = R.drawable.ic_add_template,
                    onClick = {  }
                )
            ),
            expanded = true,
            onDismiss = {  }
        )
    }
}

@Composable
fun MessageContextMenu(
    items: List<MessageAction>,
    expanded: Boolean,
    onDismiss: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var exp by remember {
        mutableStateOf(expanded)
    }

    MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp))) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                exp = false
                onDismiss(exp)
            }
        ) {
            items.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(
                    text = {
                        ContextMenuItem(item = itemValue)
                    },
                    onClick = { }, //itemValue::onClick
                    modifier = Modifier
                        .background(color = Color.White)
                )
                if (itemIndex < items.size)
                    Divider()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ContextMenuItemPreview() {
    ContextMenuItem(
        item = MessageAction(
            actionText = "Save as a template",
            actionIcon = R.drawable.ic_add_template,
            onClick = {  }
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
                color = MaterialTheme.colorScheme.secondary,
            ),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
        )

        Icon(
            painter = painterResource(id = item.actionIcon),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = item.actionText,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(24.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

//@Composable
//fun DropdownMenuItem(
//    text: String,
//    onClick: () -> Unit
//) {
//    val contentColor = when {
////        !enabled -> MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0f)
////        selected -> MaterialTheme.colorScheme.surface.copy(alpha = 1f)
//        else -> MaterialTheme.colorScheme.surfaceTint.copy(alpha = 1f)
//    }
//
//    val backgroundColor = when {
////        !enabled -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.16f)
////        selected -> MaterialTheme.colorScheme.primary.copy(alpha = 1f)
//        else -> MaterialTheme.colorScheme.surface.copy(alpha = 1f)
//    }
//
//    CompositionLocalProvider(LocalContentColor provides contentColor) {
//        Box(modifier = Modifier
////            .clickable(enabled) { onClick() }
//            .background(backgroundColor)
//            .width(400.dp)
//            .padding(16.dp)
//        ) {
//            Text(
//                text = text,
//                style = MaterialTheme.typography.bodyLarge.copy(
//                    color = MaterialTheme.colorScheme.surfaceTint
//                )
//            )
//            if (isRecommended) {
//                Text(
//                    text = stringResource(id = R.string.recommended),
//                    color = MaterialTheme.colorScheme.onPrimary,
//                    style = MaterialTheme.typography.labelSmall.copy(
//                        fontSize = 8.sp,
//                        fontWeight = FontWeight.Normal
//                    ),
//                    modifier = Modifier
//                        .width(91.59333.dp)
//                        .height(16.89833.dp)
//                        .background(
//                            color = MaterialTheme.colorScheme.primary,
//                            shape = RoundedCornerShape(size = 7.89833.dp)
//                        )
//                        .padding(
//                            start = 15.79666.dp,
//                            top = 3.94917.dp,
//                            end = 15.79666.dp,
//                            bottom = 3.94917.dp
//                        )
//                        .align(Alignment.CenterEnd)
//                        .badgeLayout()
//                )
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun DropdownListPreview() {
//    UmiTheme {
//        DropdownList(
//            listOf(
//                DomainDocumentType(
//                    id = 0,
//                    name = "Document",
//                    codename = "DOCUMENT",
//                    listPosition = 0,
//                    isRecommended = true,
//                    needBackPhoto = false
//                ),
//                DomainDocumentType(
//                    id = 1,
//                    name = "Document2",
//                    codename = "DOCUMENT2",
//                    listPosition = 1,
//                    isRecommended = false,
//                    needBackPhoto = false
//                )
//            ),
//            defaultItem = null,
//            changeDocument = {},
//            modifier = Modifier
//        )
//    }
//}