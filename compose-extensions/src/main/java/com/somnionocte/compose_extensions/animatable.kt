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
 * Animated state without calling recomposition. Only work with compose states.
 * */
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
 * Animated state without calling recomposition. Only work with compose states.
 * */
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
 * Animated state without calling recomposition. Only work with compose states.
 * */
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
 * Animated state without calling recomposition. Only work with compose states.
 * */
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