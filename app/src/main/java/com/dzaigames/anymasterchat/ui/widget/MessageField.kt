package com.dzaigames.anymasterchat.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dzaigames.anymasterchat.R

@Composable
@Preview(showBackground = true)
fun MessageFieldPreview() {
    MessageField(
        message = "Egor",
        onSend = { _ -> },
        onValueChange = { _ -> },
        onDone = { }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageField(
    message: String,
    onSend: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = onValueChange,
            singleLine = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFF2F2F2),
                unfocusedBorderColor = Color(0xFFF2F2F2)
            ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_photo),
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = stringResource(id = R.string.take_photo),
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
            },
            visualTransformation = { messageText ->
                messageTransformation(messageText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, top = 14.dp, bottom = 14.dp, end = if (message.isNotEmpty()) 64.dp else 14.dp)
                .background(Color(0xFFF2F2F2), shape = CircleShape),
            shape = CircleShape,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone()
                }
            )
        )
        
        if (message.isNotEmpty()) {
            IconButton(
                onClick = { onSend(message) },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send), 
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = stringResource(id = R.string.send)
                )
            }
        }
    }
}

fun messageTransformation(
    text: AnnotatedString
): TransformedText {
    val out = (text.text).ifEmpty { "Message..." }
    val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (text.isEmpty()) return 0
            return offset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (text.text == "") return 0
            return offset
        }
    }
    if (text.text == "") return TransformedText(AnnotatedString("Message..."), offsetMapping)
    return TransformedText(AnnotatedString(out), offsetMapping)
}