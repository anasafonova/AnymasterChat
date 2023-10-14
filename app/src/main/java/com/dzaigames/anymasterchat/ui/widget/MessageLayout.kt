package com.dzaigames.anymasterchat.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme
import com.dzaigames.anymasterchat.utils.modifyIf

@Composable
@Preview(showBackground = true)
fun OutMessageLayoutPreview() {
    AnymasterChatTheme {
        MessageLayout(
            text = "Hi Daniel, my name is Eleni, I am a professional cleaner with 10 years of experience. I can come to you tomorrow morning. 2 bedroom apartment costs 30 euros and takes about 3 hours. Is it okay for you?",
            date = 1697195631000,
            isDelivered = true,
            isRead = true,
            isFromMe = true,
            isEdited = false,
            onLongPress = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InMessageLayoutPreview() {
    AnymasterChatTheme {
        MessageLayout(
            text = "Hi Eleni, sounds good for me, tomorrow morning is perfect.",
            date = 1697195797000,
            isDelivered = true,
            isRead = true,
            isFromMe = false,
            isEdited = false,
            onLongPress = { }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageLayout(
    text: String,
    date: Long,
    isDelivered: Boolean,
    isRead: Boolean,
    isFromMe: Boolean,
    isEdited: Boolean,
    onLongPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
    ) {
        Box(
            modifier = modifier
                .width(295.dp)
                .align(if (isFromMe) Alignment.CenterEnd else Alignment.CenterStart)
                .wrapContentHeight(Alignment.CenterVertically)
                .background(
                    color = if (isFromMe) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .modifyIf(isFromMe) {
                    combinedClickable(
                        onClick = { },
                        onLongClick = onLongPress
                    )
                }
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )

            TimeAndStatusLayout(
                date = date,
                isFromMe = isFromMe,
                isDelivered = isDelivered,
                isRead = isRead,
                isEdited = isEdited,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 4.dp)
            )
        }
    }
}