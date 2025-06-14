package com.somnionocte.compose_extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager

fun Modifier.clearFocusOnTap(enabled: Boolean = true) = composed {
    val focusManager = LocalFocusManager.current

    pointerInput(enabled) {
        if(enabled) detectTapGestures(onTap = { focusManager.clearFocus() })
    }
}