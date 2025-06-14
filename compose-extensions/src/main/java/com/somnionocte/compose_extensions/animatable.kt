package com.somnionocte.compose_extensions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Animates a Compose state without triggering a recomposition of the entire Composable.
 * This function is designed to work seamlessly with existing Animatable instances
 * and Compose's state management, allowing for efficient, targeted animations.
 *
 * The animation is driven by changes in the provided value lambda, which
 * is observed via a snapshotFlow.
 * When the value changes, the animatable will animate to the new
 * value using the specified animation specification.
 *
 * @param animatable The Animatable instance to be controlled.
 * This should typically be remembered using remember
 * or a similar mechanism to persist across recompositions.
 * @param spec A lambda that provides the AnimationSpec
 * to use for the animation. Defaults to a spring animation.
 * This allows for flexible control over the animation's behavior (e.g., duration, easing).
 * @param onAnimationFinished A lambda that will be invoked once the animation completes.
 * This is useful for triggering side effects or further actions
 * after the animation has reached its target value.
 * @param value A lambda that provides the target float value for the animation.
 * Changes to the value returned by this lambda will trigger the animation.
 * This should ideally be a Compose state (e.g., a MutableState)
 * to ensure reactivity.
 * @return The provided Animatable instance,
 * which can be used to observe the animation's current value or state.
 * @see androidx.compose.animation.core.Animatable
 * @see androidx.compose.animation.core.AnimationSpec
 * @see androidx.compose.animation.core.spring
 * @see androidx.compose.runtime.snapshotFlow
 * @see androidx.compose.runtime.LaunchedEffect
 * @see androidx.compose.runtime.remember
 */
@Composable
inline fun animatableAs(
    animatable: Animatable<Float, AnimationVector1D>,
    crossinline spec: () -> AnimationSpec<Float> = { spring() },
    crossinline onAnimationFinished: () -> Unit = {  },
    crossinline value: () -> Float
): Animatable<Float, AnimationVector1D> {
    val state by remember { derivedStateOf { value() } }
    val spec by remember { derivedStateOf { spec() } }

    LaunchedEffect(Unit) {
        snapshotFlow { state }.collectLatest { value ->
            launch {
                animatable.animateTo(value, spec)
                onAnimationFinished()
            }
        }
    }

    return animatable
}

/**
 * Animates a Compose state without triggering a recomposition of the entire Composable.
 * This function is designed to work seamlessly with existing Animatable instances
 * and Compose's state management, allowing for efficient, targeted animations.
 *
 * The animation is driven by changes in the provided value lambda, which
 * is observed via a snapshotFlow.
 * When the value changes, the animatable will animate to the new
 * value using the specified animation specification.
 *
 * @param animatable The Animatable instance to be controlled.
 * This should typically be remembered using remember
 * or a similar mechanism to persist across recompositions.
 * @param spec A lambda that provides the AnimationSpec
 * to use for the animation. Defaults to a spring animation.
 * This allows for flexible control over the animation's behavior (e.g., duration, easing).
 * @param onAnimationFinished A lambda that will be invoked once the animation completes.
 * This is useful for triggering side effects or further actions
 * after the animation has reached its target value.
 * @param value A lambda that provides the target float value for the animation.
 * Changes to the value returned by this lambda will trigger the animation.
 * This should ideally be a Compose state (e.g., a MutableState)
 * to ensure reactivity.
 * @return The provided Animatable instance,
 * which can be used to observe the animation's current value or state.
 * @see androidx.compose.animation.core.Animatable
 * @see androidx.compose.animation.core.AnimationSpec
 * @see androidx.compose.animation.core.spring
 * @see androidx.compose.runtime.snapshotFlow
 * @see androidx.compose.runtime.LaunchedEffect
 * @see androidx.compose.runtime.remember
 */
@Composable
inline fun animatableAs(
    crossinline spec: () -> AnimationSpec<Float> = { spring() },
    initialValue: Float = 0f,
    crossinline onAnimationFinished: () -> Unit = {  },
    crossinline value: () -> Float
): Animatable<Float, AnimationVector1D> {
    val animatable = remember { Animatable(initialValue) }
    return animatableAs(animatable, spec, onAnimationFinished, value)
}

/**
 * Animates a Compose state without triggering a recomposition of the entire Composable.
 * This function is designed to work seamlessly with existing Animatable instances
 * and Compose's state management, allowing for efficient, targeted animations.
 *
 * The animation is driven by changes in the provided value lambda, which
 * is observed via a snapshotFlow.
 * When the value changes, the animatable will animate to the new
 * value using the specified animation specification.
 *
 * @param animatable The Animatable instance to be controlled.
 * This should typically be remembered using remember
 * or a similar mechanism to persist across recompositions.
 * @param spec A lambda that provides the AnimationSpec
 * to use for the animation. Defaults to a spring animation.
 * This allows for flexible control over the animation's behavior (e.g., duration, easing).
 * @param onAnimationFinished A lambda that will be invoked once the animation completes.
 * This is useful for triggering side effects or further actions
 * after the animation has reached its target value.
 * @param value A lambda that provides the target float value for the animation.
 * Changes to the value returned by this lambda will trigger the animation.
 * This should ideally be a Compose state (e.g., a MutableState)
 * to ensure reactivity.
 * @return The provided Animatable instance,
 * which can be used to observe the animation's current value or state.
 * @see androidx.compose.animation.core.Animatable
 * @see androidx.compose.animation.core.AnimationSpec
 * @see androidx.compose.animation.core.spring
 * @see androidx.compose.runtime.snapshotFlow
 * @see androidx.compose.runtime.LaunchedEffect
 * @see androidx.compose.runtime.remember
 */
@Composable
inline fun animatableColorAs(
    animatable: Animatable<Color, AnimationVector4D>,
    crossinline spec: () -> AnimationSpec<Color> = { spring() },
    crossinline onAnimationFinished: () -> Unit = {  },
    crossinline value: () -> Color
): Animatable<Color, AnimationVector4D> {
    val state by remember { derivedStateOf { value() } }
    val spec by remember { derivedStateOf { spec() } }

    LaunchedEffect(Unit) {
        snapshotFlow { state }.collectLatest { value ->
            launch {
                animatable.animateTo(value, spec)
                onAnimationFinished()
            }
        }
    }

    return animatable
}

/**
 * Animates a Compose state without triggering a recomposition of the entire Composable.
 * This function is designed to work seamlessly with existing Animatable instances
 * and Compose's state management, allowing for efficient, targeted animations.
 *
 * The animation is driven by changes in the provided value lambda, which
 * is observed via a snapshotFlow.
 * When the value changes, the animatable will animate to the new
 * value using the specified animation specification.
 *
 * @param animatable The Animatable instance to be controlled.
 * This should typically be remembered using remember
 * or a similar mechanism to persist across recompositions.
 * @param spec A lambda that provides the AnimationSpec
 * to use for the animation. Defaults to a spring animation.
 * This allows for flexible control over the animation's behavior (e.g., duration, easing).
 * @param onAnimationFinished A lambda that will be invoked once the animation completes.
 * This is useful for triggering side effects or further actions
 * after the animation has reached its target value.
 * @param value A lambda that provides the target float value for the animation.
 * Changes to the value returned by this lambda will trigger the animation.
 * This should ideally be a Compose state (e.g., a MutableState)
 * to ensure reactivity.
 * @return The provided Animatable instance,
 * which can be used to observe the animation's current value or state.
 * @see androidx.compose.animation.core.Animatable
 * @see androidx.compose.animation.core.AnimationSpec
 * @see androidx.compose.animation.core.spring
 * @see androidx.compose.runtime.snapshotFlow
 * @see androidx.compose.runtime.LaunchedEffect
 * @see androidx.compose.runtime.remember
 */
@Composable
inline fun animatableColorAs(
    crossinline spec: () -> AnimationSpec<Color> = { spring() },
    initialValue: Color = Color.Transparent,
    crossinline onAnimationFinished: () -> Unit = {  },
    crossinline value: () -> Color
): Animatable<Color, AnimationVector4D> {
    val animatable = remember { androidx.compose.animation.Animatable(initialValue) }
    return animatableColorAs(animatable, spec, onAnimationFinished, value)
}