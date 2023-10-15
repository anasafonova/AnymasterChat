package com.dzaigames.anymasterchat.util

import androidx.compose.ui.Modifier

fun Modifier.modifyIf(condition: Boolean, modify: Modifier.() -> Modifier) =
    if (condition) modify() else this