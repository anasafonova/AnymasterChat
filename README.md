# AnymasterChat

📱 AnymasterChat is an Android application that provides UI for chat messaging.
The app uses a mocked WebSocket connection to send and receive messages.
Whenever a message is sent, the user receives an auto-replay message.
In addition, the replayed message automatically updates whenever the original message is edited.

## Table of Contents

1. [Features](#features)
2. [Stack](#stack)
3. [Installation and Setup](#installation-and-setup)
4. [Usage](#usage)
5. [Contributing](#contributing)
6. [License](#license)

## Features

🚀 AnymasterChat offers the following features:

- Chat UI.
- Real-time message updates using a mocked WebSocket connection.
- Auto-replay.
- Dynamic updating of the replayed message when the original message is edited.

## Stack

🔧 AnymasterChat is built using the following technologies and frameworks:

- UI **Jetpack Compose**: A modern toolkit for building native Android user interfaces.
- DI **Dagger2**: A fast dependency injection framework for Android and Java.
- Single Activity: Utilizes a single activity architecture for efficient future app navigation.
- ORM **Room**: A persistence library provided by Android Jetpack that simplifies database integration with SQLite on Android.

## Installation and Setup

⚙️ Follow the steps below to install and set up AnymasterChat:

1. Clone the repository to your local machine using the following command:

```shell
git clone https://github.com/anasafonova/AnymasterChat.git
```

2. Open Android Studio and select "Open an existing project".
3. Navigate to the cloned repository directory and select it.
4. Once the project is open, wait for the Gradle build to finish.
5. Connect an Android device or start an emulator.
6. Run the app on the connected device/emulator by clicking on the Run button in Android Studio.

## Usage

🔨 To use AnymasterChat:

1. Launch the app on your Android device/emulator.
2. You will be presented with a chat interface.
3. Type your message in the input field at the bottom of the screen.
4. Press the send button to send the message.
5. The sent message will be displayed in the chat UI. You will immediately receive the auto-replay message.
6. If you wish to edit the message, long press on it, select "Edit" in context menu and simply update the text in the input field. Press "Send" button to update message, "Close" button to abort changes.
7. The replayed message will automatically update with the edited text.

## Contributing

🤝 Contributions to AnymasterChat are welcome! If you find any issues or would like to suggest new features, please open an issue on the [GitHub repository](https://github.com/anasafonova/AnymasterChat.git) or submit a pull request.

## License

📝 Simple Chat App is open source and released under the [MIT License](https://opensource.org/licenses/MIT). Feel free to modify and distribute the app as per the terms of the license.

🎉 Thank you for using Simple Chat App! If you have any further questions or need assistance, please don't hesitate to reach out. Happy chatting! 🎉
