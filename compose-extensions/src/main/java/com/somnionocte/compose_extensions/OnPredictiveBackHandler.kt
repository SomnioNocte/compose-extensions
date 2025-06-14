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

/**
 * Represents the mutable state associated with a predictive back gesture.
 * This class holds information about the drag progress, the initial touch
 * coordinates, and the current offset during a predictive back animation.
 */
class PredictiveBackState {
    /**
     * A [derivedStateOf] indicating whether a predictive back gesture is currently being dragged.
     * This is true if [progress] is not 0f.
     */
    val isDragged by derivedStateOf { progress != 0f }

    /**
     * The initial touch offset when the predictive back gesture began.
     * This is an internal state and should not be set directly from outside the module.
     */
    var startOffset by mutableStateOf(Offset.Zero)
        internal set

    /**
     * The current progress of the predictive back gesture, ranging from 0f to 1f.
     * This is an internal state and should not be set directly from outside the module.
     */
    var progress by mutableFloatStateOf(0f)
        internal set

    /**
     * The current offset of the touch point relative to the [startOffset] during the gesture.
     * This is an internal state and should not be set directly from outside the module.
     */
    var offset by mutableStateOf(Offset.Zero)
        internal set

    /**
     * Updates the [offset] based on the current touch coordinates.
     * The offset is calculated as the difference between the [startOffset] and the current touch coordinates.
     *
     * @param touchX The current X-coordinate of the touch point.
     * @param touchY The current Y-coordinate of the touch point.
     */
    internal fun updateOffset(touchX: Float, touchY: Float) {
        offset = startOffset - Offset(touchX, touchY)
    }

    internal fun reset() {
        progress = 0f
        startOffset = Offset.Zero
        offset = Offset.Zero
    }
}

/**
 * A Composable function that enables and manages the predictive back gesture.
 * This function provides a [PredictiveBackState] which can be used to observe
 * the progress and offset of the predictive back animation, allowing you to
 * build custom back animations that respond to user gestures.
 *
 * When the predictive back gesture starts, the [PredictiveBackState.startOffset] is captured.
 * As the gesture progresses, the [PredictiveBackState.progress] and [PredictiveBackState.offset]
 * are continuously updated. If the gesture is successfully completed, the [onBack]
 * lambda is invoked. If the gesture is cancelled (e.g., the user lifts their finger
 * before completing the gesture), the state is reset.
 *
 * @param enabled Whether the predictive back gesture handling is enabled. Defaults to `true`.
 * @param onBack A lambda that will be invoked when the predictive back gesture is successfully
 * completed by the user.
 * @return A [PredictiveBackState] instance that reflects the current state of the
 * predictive back gesture, including progress, start offset, and current offset.
 * @see androidx.activity.compose.PredictiveBackHandler
 * @see PredictiveBackState
 */
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
            state.reset()
        } catch (_: CancellationException) {
            state.reset()
        }
    }

    return state
}