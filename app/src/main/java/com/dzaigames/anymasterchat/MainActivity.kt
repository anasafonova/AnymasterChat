package com.dzaigames.anymasterchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.dzaigames.anymasterchat.data.client.MockWebSocketClient
import com.dzaigames.anymasterchat.data.manager.UserPreferencesManager
import com.dzaigames.anymasterchat.ui.chatScreen.screen.ChatScreen
import com.dzaigames.anymasterchat.ui.chatScreen.viewModel.ChatScreenViewModel
import com.dzaigames.anymasterchat.ui.chatScreen.viewModel.ChatScreenViewModelFactory
import com.dzaigames.anymasterchat.ui.theme.AnymasterChatTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    private lateinit var chatScreenViewModel: ChatScreenViewModel

    @Inject
    lateinit var chatScreenViewModelFactory: ChatScreenViewModelFactory

    @Inject
    lateinit var userPreferencesManager: UserPreferencesManager

    @Inject
    lateinit var webSocketClient: MockWebSocketClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as ChatApplication).applicationComponent.inject(this)

        chatScreenViewModel = ViewModelProvider(this, chatScreenViewModelFactory)[ChatScreenViewModel::class.java]

        chatScreenViewModel.preset()

        userPreferencesManager.setDefaultUserId()

        webSocketClient.connect()

        setContent {
            AnymasterChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen(
                        viewModel = chatScreenViewModel
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        webSocketClient.disconnect()
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnymasterChatTheme {
        Greeting("Android")
    }
}