package com.dzaigames.anymasterchat.ui.chatScreen.screen

import android.graphics.Bitmap
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.util.Log
import android.view.ContextMenu
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dzaigames.anymasterchat.R
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import com.dzaigames.anymasterchat.ui.chatScreen.viewModel.ChatScreenViewModel
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme
import com.dzaigames.anymasterchat.ui.widget.ChatBar
import com.dzaigames.anymasterchat.ui.widget.MessageContextMenu
import com.dzaigames.anymasterchat.ui.widget.MessageField
import com.dzaigames.anymasterchat.ui.widget.MessageLayout
import com.dzaigames.anymasterchat.utils.modifyIf

private const val USER_NAME = "Daniel"
private const val USER_SURNAME = "Moris"
private const val TASK_TEXT = "Cleaning of a two-room apartment"
private const val TASK_PRICE = 1498.0


@Composable
@Preview(showSystemUi = true)
fun ChatScreenPreview() {
    val messages = listOf<MessageDto>(
        MessageDto(
            id = 1,
            author = 1,
            message = "Hi Daniel, my name is Eleni, I am a professional cleaner with 10 years of experience. I can come to you tomorrow morning. 2 bedroom apartment costs 30 euros and takes about 3 hours. Is it okay for you?",
            isEdited = false,
            createdAt = 1697195631000,
            updatedAt = 1697195631000,
            isRead = true,
            isDelivered = true
        ),
        MessageDto(
            id = 2,
            author = 1,
            message = "I can also wash terrace, windows and balcony for extra 20 euros if needed.",
            isEdited = false,
            createdAt = 1697195633000,
            updatedAt = 1697195633000,
            isRead = true,
            isDelivered = true
        ),
        MessageDto(
            id = 3,
            author = 2,
            message = "Hi Eleni, sounds good for me, tomorrow morning is perfect.",
            isEdited = false,
            createdAt = 1697195797000,
            updatedAt = 1697195797000,
            isRead = true,
            isDelivered = true
        )
    )

    ChatScreenContent(
        messages = messages.sortedByDescending { it.createdAt },
        messageActions = listOf(
            MessageAction(
                actionText = "Save as template",
                actionIcon = R.drawable.ic_add_template,
                onClick = {  }
            )
        ),
        userName = "Daniel",
        userSurname = "Moris",
        profileBitmap = null,
        isVerified = true,
        taskText = "Cleaning of a two-room apartment",
        taskPrice = 1498.0,
        changeOffer = { }
    )
}

@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel
) {
//    binding = ActivityMainBinding.inflate(layoutInflater)
//    setContentView(binding.root)
//
//    binding.xRadius.bind(viewModel.radiusX, viewModel::setRadiusX)
//    binding.yRadius.bind(viewModel.radiusY, viewModel::setRadiusY)
//    binding.blurToggle.bind(viewModel.blur, viewModel::setBlurred)
//    binding.fullscreenToggle.bind(viewModel.fullscreen, viewModel::setFullscreen)
//
//    viewModel.fullscreenEffectFlow.onEach { (fullscreen, effect) ->
//        if (fullscreen) {
//            binding.root.setRenderEffect(effect)
//            binding.image.setRenderEffect(null)
//        } else {
//            binding.root.setRenderEffect(null)
//            binding.image.setRenderEffect(effect)
//        }
//    }.launchIn(viewModel.viewModelScope)

    val messages = viewModel.messages.collectAsState(initial = listOf())

    AnymasterChatTheme {
        ChatScreenContent(
            messages = messages.value,
            messageActions = viewModel.messageActions,
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

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenContent(
    messages: List<MessageDto>,
    messageActions: List<MessageAction>,
    userName: String,
    userSurname: String,
    profileBitmap: Bitmap?,
    isVerified: Boolean,
    taskText: String,
    taskPrice: Double,
    changeOffer: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val listState = rememberLazyListState()

    var selectedMessageId by rememberSaveable {
        mutableStateOf(-1)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    var messageText by rememberSaveable { mutableStateOf("") }

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
                onSend = { _ -> },
                onValueChange = {
                    messageText = it
                },
                onDone = { focusManager.clearFocus() }
            )
        },
        modifier = Modifier
            .modifyIf(selectedMessageId >= 0 && messages[selectedMessageId].author == 1) { // (Build.VERSION.SDK_INT < Build.VERSION_CODES.S)
                graphicsLayer(
                    renderEffect = RenderEffect.createBlurEffect(25f, 25f, Shader.TileMode.MIRROR)
                        .asComposeRenderEffect()
                )
            }
    ) { contentPadding ->
        Column {
            LazyColumn(
                state = listState,
                reverseLayout = true,
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
                    items(messages.size) {
                        MessageItem(
                            message = messages[it],
                            onLongPress = {
                                selectedMessageId = it
                                expanded = true
                            }
                        )
                    }
                }
            )
        }
    }

    if (selectedMessageId >= 0 && messages[selectedMessageId].author == 1) {
        MessageContextMenu(
            items = messageActions,
            expanded = expanded,
            onDismiss = {
                expanded = it
                selectedMessageId = -1
            }
        )
    }
}

@Composable
fun MessageItem(
    message: MessageDto?,
    onLongPress: () -> Unit,
    showUnblurred: Boolean = false
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