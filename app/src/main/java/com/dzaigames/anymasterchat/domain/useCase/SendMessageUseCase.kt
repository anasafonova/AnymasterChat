package com.dzaigames.anymasterchat.domain.useCase

import com.dzaigames.anymasterchat.data.client.WebSocketClient
import com.dzaigames.anymasterchat.data.model.MessageDto
import com.dzaigames.anymasterchat.domain.base.BaseUseCase
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val webSocketClient: WebSocketClient
) : BaseUseCase<Unit, SendMessageUseCase.Params>() {

    override suspend fun doUseCase(params: Params) =
        webSocketClient.send(params.message)

    class Params(val message: MessageDto)

    suspend operator fun invoke(
        message: MessageDto
    ): Result<Unit> = invoke(Params(message))
}