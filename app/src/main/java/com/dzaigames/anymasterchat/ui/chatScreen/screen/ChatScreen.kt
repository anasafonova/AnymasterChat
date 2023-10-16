package com.dzaigames.anymasterchat.ui.chatScreen.screen

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dzaigames.anymasterchat.R
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.ui.chatScreen.model.ChatScreenState
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import com.dzaigames.anymasterchat.ui.chatScreen.viewModel.ChatScreenViewModel
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme
import com.dzaigames.anymasterchat.ui.widget.ChatBar
import com.dzaigames.anymasterchat.ui.widget.layout.CloudyLayout
import com.dzaigames.anymasterchat.ui.widget.contextMenu.MessageContextMenu
import com.dzaigames.anymasterchat.ui.widget.textField.MessageField
import com.dzaigames.anymasterchat.ui.widget.layout.MessageLayout
import com.dzaigames.anymasterchat.ui.widget.layout.OrderBanner
import com.dzaigames.anymasterchat.util.isScrolledToTheEnd

private const val USER_NAME = "Daniel"
private const val USER_SURNAME = "Moris"
private const val TASK_TEXT = "Cleaning of a two-room apartment"
private const val TASK_PRICE = 1498.0

@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel
) {
    val messages = viewModel.messages.collectAsState(initial = listOf())
    val uiState = viewModel.uiState.collectAsState()

    AnymasterChatTheme {
        ChatScreenContent(
            messages = messages.value,
            messageActions = viewModel.messageActions,
            uiState = uiState.value,
            viewModel = viewModel,
            userName = USER_NAME,
            userSurname = USER_SURNAME,
            profileBitmap = null,
            isVerified = true,
            taskText = TASK_TEXT,
            taskPrice = TASK_PRICE,
            changeOffer = { }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ChatScreenContent(
    messages: List<MessageDto>,
    messageActions: List<MessageAction>,
    uiState: ChatScreenState,
    viewModel: ChatScreenViewModel,
    userName: String,
    userSurname: String,
    profileBitmap: Bitmap?,
    isVerified: Boolean,
    taskText: String,
    taskPrice: Double,
    changeOffer: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val focusRequester = remember { FocusRequester() }

    val keyboard = LocalSoftwareKeyboardController.current

    val listState = rememberLazyListState()

    var selectedMessage: MessageDto? by rememberSaveable {
        mutableStateOf(null)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    var messageText by rememberSaveable { mutableStateOf("") }

    val sendNewMessage: (String) -> Unit = { text: String ->
        viewModel.onMessageSend(
            id = messages.size + 1,
            text = text
        )
    }

    val sendEditedMessage: (String) -> Unit = { text: String ->
        viewModel.onMessageEdited(
            message = selectedMessage!!.copy(
                message = text
            )
        )
    }

    val onDismiss: () -> Unit = {
        messageText = ""
        keyboard?.hide()
        focusManager.clearFocus()
        viewModel.onMessageCompleted()
        selectedMessage = null
    }

    val onEditAction: (MessageDto) -> Unit = { message ->
        messageText = message.message
        viewModel.onEdit()
        focusRequester.requestFocus()
        keyboard?.show()
    }

    LaunchedEffect(messages.size) {
        if (!listState.isScrolledToTheEnd()) {
            val itmIndex = listState.layoutInfo.totalItemsCount - 1
            if (itmIndex >= 0) {
                val lastItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                lastItem?.let {
                    listState.animateScrollToItem(itmIndex, it.size + it.offset)
                }
            }
        }
    }

    CloudyLayout(isClouded = expanded) {
        Scaffold(
            topBar = {
                ChatBar(
                    userName = userName,
                    userSurname = userSurname,
                    profileBitmap = profileBitmap,
                    isVerified = isVerified,
                    taskText = taskText,
                    taskPrice = taskPrice,
                    changeOffer = changeOffer
                )
            },
            bottomBar = {
                MessageField(
                    message = messageText,
                    isEditing = uiState.isEditing,
                    onSend = { text ->
                        if (uiState.isEditing) {
                            sendEditedMessage(text)
                        } else {
                            sendNewMessage(text)
                        }
                        onDismiss()
                    },
                    onValueChange = {
                        messageText = it
                    },
                    onDone = { focusManager.clearFocus() },
                    onCancelEditing = {
                        onDismiss()
                    },
                    focusRequester = focusRequester
                )
            }
        ) { contentPadding ->
            Column {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .wrapContentHeight(align = Alignment.Bottom)
                        .padding(
                            top = contentPadding.calculateTopPadding(),
                            bottom = contentPadding.calculateBottomPadding()
                        )
                        .background(
                            color = Color.Transparent
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp),
                    content = {
                        item {
                            TodayLayout()
                        }

                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                OrderBanner(
                                    userName = userName,
                                    price = taskPrice,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }

                        items(messages.size) {
                            MessageItem(
                                message = messages[it],
                                onLongPress = {
                                    selectedMessage = messages[it]
                                    expanded = true
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    if (selectedMessage != null && viewModel.checkMessageIsMine(selectedMessage!!)) {
        MessageContextMenu(
            selectedMessage = selectedMessage!!,
            actionItems = messageActions,
            expanded = expanded,
            onDismiss = {
                expanded = false
            },
            onEdit = { message ->
                onEditAction(message)
            }
        )
    }
}

@Composable
private fun MessageItem(
    message: MessageDto?,
    onLongPress: () -> Unit = {  }
) {
    if (message == null)
        return

    MessageLayout(
        text = message.message,
        date = message.createdAt,
        isDelivered = message.isDelivered,
        isRead = message.isRead,
        isFromMe = message.author == 1,
        isEdited = message.isEdited,
        onLongPress = onLongPress
    )
}

@Composable
private fun TodayLayout() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.today),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.8.sp,
                fontWeight = FontWeight(400),
                color = Color(0x66040C15),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Center)
        )
    }
}