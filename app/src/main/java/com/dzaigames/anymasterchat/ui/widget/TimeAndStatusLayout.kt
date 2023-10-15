package com.dzaigames.anymasterchat.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzaigames.anymasterchat.R
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme
import com.dzaigames.anymasterchat.util.toDateTime

@Composable
@Preview(showBackground = true)
fun EditedLayoutPreview() {
    AnymasterChatTheme {
        TimeAndStatusLayout(
            date = 1697195383000,
            isFromMe = true,
            isDelivered = true,
            isRead = true,
            isEdited = true
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ReadLayoutPreview() {
    AnymasterChatTheme {
        TimeAndStatusLayout(
            date = 1697195383000,
            isFromMe = true,
            isDelivered = true,
            isRead = true,
            isEdited = false
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DeliveredLayoutPreview() {
    AnymasterChatTheme {
        TimeAndStatusLayout(
            date = 1697195383000,
            isFromMe = true,
            isDelivered = true,
            isRead = false,
            isEdited = false
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SendLayoutPreview() {
    AnymasterChatTheme {
        TimeAndStatusLayout(
            date = 1697195383000,
            isFromMe = true,
            isDelivered = false,
            isRead = false,
            isEdited = false
        )
    }
}

@Composable
fun TimeAndStatusLayout(
    date: Long,
    isFromMe: Boolean,
    isDelivered: Boolean,
    isRead: Boolean,
    isEdited: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isEdited)
            Text(
                text = stringResource(id = R.string.edited),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.8.sp,
                    fontWeight = FontWeight(400),
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Right,
                ),
                modifier = Modifier
                    .padding(end = 4.dp)
            )

        Text(
            text = date.toDateTime(),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Right,
            )
        )

        if (isFromMe)
            Icon(
                painter = painterResource(id = R.drawable.ic_delivery_message),
                tint = when {
                    isRead -> MaterialTheme.colorScheme.secondary
                    isDelivered -> MaterialTheme.colorScheme.tertiary
                    else -> Color.Transparent
                },
                contentDescription = stringResource(id = R.string.delivery_status),
                modifier = Modifier
                    .size(20.dp)
            )
    }
}