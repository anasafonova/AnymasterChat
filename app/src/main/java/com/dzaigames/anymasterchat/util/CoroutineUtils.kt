package com.dzaigames.anymasterchat.util

import kotlinx.coroutines.flow.SharingStarted

private const val STOP_TIMEOUT_MILLIS: Long = 5000

val WhileUiSubscribed: SharingStarted = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS)