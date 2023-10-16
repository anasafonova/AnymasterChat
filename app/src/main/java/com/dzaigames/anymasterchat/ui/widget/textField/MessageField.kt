@file:OptIn(ExperimentalComposeUiApi::class)

package com.dzaigames.anymasterchat.ui.widget.textField

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dzaigames.anymasterchat.R

@Composable
@Preview(showBackground = true)
fun MessageFieldPreview() {
    MessageTextField(
        message = "",
        isEditing = false,
        onSend = { _ -> },
        onValueChange = { _ -> },
        onDone = { }
    )
}

@Composable
@Preview(showBackground = true)
fun MessageFieldNotEmptyPreview() {
    MessageTextField(
        message = "Hi Eleni, sounds good",
        isEditing = false,
        onSend = { _ -> },
        onValueChange = { _ -> },
        onDone = { }
    )
}

@Composable
@Preview(showBackground = true)
fun MessageFieldEditingPreview() {
    MessageField(
        message = LoremIpsum(200).values.joinToString(),
        isEditing = true,
        onSend = { _ -> },
        onValueChange = { _ -> },
        onDone = { },
        onCancelEditing = { }
    )
}

@Composable
fun MessageField(
    message: String,
    isEditing: Boolean,
    onSend: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    onCancelEditing: () -> Unit,
    focusRequester: FocusRequester = FocusRequester()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (editMessageLabel, closeButton, messageField) = createRefs()
        if (isEditing) {
            Box(
                modifier = Modifier
                    .constrainAs(editMessageLabel) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 18.dp, start = 14.dp)
                    .height(16.dp)
                    .width(94.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = stringResource(id = R.string.edit_message),
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = stringResource(id = R.string.edit_message),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.8.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colorScheme.secondary,
                        ),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
            }

            IconButton(
                onClick = onCancelEditing,
                modifier = Modifier
                    .constrainAs(closeButton) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 4.dp, end = 14.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cross_circle), 
                    contentDescription = stringResource(id = R.string.close),
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        MessageTextField(
            message = message,
            isEditing = isEditing,
            onSend = onSend,
            onValueChange = onValueChange,
            onDone = onDone,
            modifier = Modifier
                .constrainAs(messageField) {
                    if (isEditing) {
                        top.linkTo(editMessageLabel.bottom)
                        start.linkTo(parent.start)
                    } else {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                },
            focusRequester = focusRequester
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageTextField(
    message: String,
    isEditing: Boolean,
    onSend: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester()
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
                .padding(
                    start = 14.dp,
                    top = 14.dp,
                    bottom = 14.dp,
                    end = if (message.isNotEmpty()) 64.dp else 14.dp
                )
                .focusRequester(focusRequester)
                .background(Color(0xFFF2F2F2), shape = RoundedCornerShape(16.dp)), //CircleShape),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone()
                    keyboardController?.hide()
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
                    painter = painterResource(id = if (!isEditing) R.drawable.ic_send else R.drawable.ic_check),
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