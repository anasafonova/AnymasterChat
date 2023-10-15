package com.dzaigames.anymasterchat.util

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheEnd() : Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastItem == null || lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
}