package com.somnionocte.compose_extensions

import androidx.activity.BackEventCompat
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class PredictiveBackState {
    val isDragged by derivedStateOf { progress != 0f }

    var startOffset by mutableStateOf(Offset.Zero)
        internal set
    var progress by mutableFloatStateOf(0f)
        internal set
    var offset by mutableStateOf(Offset.Zero)
        internal set

    internal fun updateOffset(touchX: Float, touchY: Float) {
        offset = startOffset - Offset(touchX, touchY)
    }
}

@Composable
fun onPredictiveBack(
    enabled: Boolean = true,
    onBack: () -> Unit
): PredictiveBackState {
    val state = remember { PredictiveBackState() }

    PredictiveBackHandler(enabled) { progress: Flow<BackEventCompat> ->
        try {
            progress.firstOrNull()?.apply { state.startOffset = Offset(touchX, touchY) }

            progress.collect { backHandler ->
                state.progress = backHandler.progress
                state.updateOffset(backHandler.touchX, backHandler.touchY)
            }
            onBack()
        } catch (_: CancellationException) {
            state.progress = 0f
            state.startOffset = Offset.Zero
            state.offset = Offset.Zero
        }
    }

    return state
}