package com.somnionocte.compose_extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager

/**
 * A Composable Modifier that clears focus from the currently focused component when a tap gesture is detected outside of it.
 * This is particularly useful for dismissing the software keyboard or deselecting input fields
 * by simply tapping anywhere else on the screen.
 *
 * The modifier utilizes pointerInput
 * to detect tap gestures and interacts with the LocalFocusManager
 * to clear the focus. The behavior can be enabled or disabled dynamically.
 *
 * @param enabled If true, the modifier will actively clear focus on tap.
 * If false, the tap gesture will not clear focus. Defaults to true.
 * @return A Modifier that can be applied to any Composable
 * to enable clear-focus-on-tap functionality.
 * @see androidx.compose.ui.platform.LocalFocusManager
 * @see androidx.compose.ui.input.pointer.pointerInput
 * @see androidx.compose.foundation.gestures.detectTapGestures
 */
fun Modifier.clearFocusOnTap(enabled: Boolean = true) = composed {
    val focusManager = LocalFocusManager.current

    pointerInput(enabled) {
        if(enabled) detectTapGestures(onTap = { focusManager.clearFocus() })
    }
}