package com.dzaigames.anymasterchat.ui.chatScreen.viewModel

import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.data.repo.MessagesRepository
import com.dzaigames.anymasterchat.ui.chatScreen.model.MessageAction
import com.dzaigames.anymasterchat.utils.PresetItemsInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias FullscreenEffect = Pair<Boolean, RenderEffect?>

class ChatScreenViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository,
    private val presetItemsInteractor: PresetItemsInteractor
): ViewModel() {
//    val exceptionHandler = CoroutineExceptionHandler { context, exception ->
//        viewModelScope.launch {
//            isError.emit(true)
//        }
//    }
//
//    private val isRefreshing = MutableStateFlow(false)
//
//    private val isError = MutableStateFlow(false)
//
//    private val _fullscreen = MutableStateFlow(false)
//    val fullscreen: StateFlow<Boolean> = _fullscreen
//    fun setFullscreen(isFullscreen: Boolean) {
//        _fullscreen.value = isFullscreen
//    }
//
//    private val _blur = MutableStateFlow(false)
//    val blur: StateFlow<Boolean> = _blur
//    fun setBlurred(isBlurred: Boolean) {
//        _blur.value = isBlurred
//    }
//
//    private val _radiusX = MutableStateFlow(DEFAULT_BLUR)
//    val radiusX: StateFlow<Float> = _radiusX
//    fun setRadiusX(newValue: Float) {
//        _radiusX.value = newValue
//    }
//
//    private val _radiusY = MutableStateFlow(DEFAULT_BLUR)
//    val radiusY: StateFlow<Float> = _radiusY
//    fun setRadiusY(newValue: Float) {
//        _radiusY.value = newValue
//    }

//    private val blurEffectFlow = combine(_radiusX, _radiusY, _blur) { x, y, applyBlur ->
//        if (applyBlur) {
//            RenderEffect.createBlurEffect(x, y, Shader.TileMode.MIRROR)
//        } else null
//    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

//    val fullscreenEffectFlow: Flow<FullscreenEffect> =
//        combine(_fullscreen, blurEffectFlow) { fullscreen, effect -> fullscreen to effect }
//
//    companion object {
//        const val DEFAULT_BLUR = 8f
//    }

    val messages: SharedFlow<List<MessageDto>> = messagesRepository
        .messages
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.Lazily,
//            initialValue = listOf()
//        )
//        .asResult()

    lateinit var messageActions: List<MessageAction>

    fun preset() {
        viewModelScope.launch(Dispatchers.IO) {
            presetItemsInteractor.preset()
            messageActions = messagesRepository.messageActions
        }
    }

//    val uiState: StateFlow<MainMenuUiState> = combine(
//        questions,
//        isRefreshing,
//        isError
//    ) { questionsResult, refreshing, errorOccurred ->
//
//        val questionsState: QuestionsUiState = when (questionsResult) {
//            is Result.Success -> QuestionsUiState.Success(questionsResult.data)
//            is Result.Loading -> QuestionsUiState.Loading
//            is Result.Error -> QuestionsUiState.Error
//        }
//
//        MainMenuUiState(
//            questionsState,
//            refreshing,
//            errorOccurred
//        )
//    }
//        .stateIn(
//            scope = viewModelScope,
//            started = WhileUiSubscribed,
//            initialValue = MainMenuUiState(
//                QuestionsUiState.Loading,
//                isRefreshing = false,
//                isError = false
//            )
//        )
}