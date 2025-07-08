package com.somnionocte.compose_extensions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.util.lerp

@Composable
fun ExpandableBox(
    state: () -> Boolean,
    collapsedContent: @Composable BoxScope.(progress: () -> Float) -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    animatable: Animatable<Float, AnimationVector1D> = remember { Animatable(0f) },
    collapsedGraphicsLayer: GraphicsLayerScope.(progress: Float) -> Unit = { alpha = it },
    expandedGraphicsLayer: GraphicsLayerScope.(progress: Float) -> Unit = { alpha = it },
    expandedContent: @Composable BoxScope.(progress: () -> Float) -> Unit
) {
    val transition = if(onBack != null) {
        val predictiveGesture = onPredictiveBack(state()) { onBack() }

        val rawTransition = animatableAs({ spring(1f, if(state()) 250f else 300f, .00000001f) }) { if(state()) 1f else 0f }
        val predictiveTransition = animatableAs(
            { spring(1f, if(predictiveGesture.isDragged) 15000f else 300f, .00000001f) }
        ) { predictiveGesture.progress * .6f }

        animatableAs(animatable, { spring(1f, 15000f, .00000001f) }) { (rawTransition.value - predictiveTransition.value).coerceIn(0f..1f) }
    } else {
        animatableAs(animatable, { spring(1f, if(state()) 300f else 400f, .00000001f) }) { if(state()) 1f else 0f }
    }

    val collapsedContent = @Composable {
        val progress by remember { derivedStateOf {
            (1f - (transition.value)).let { progress ->
                if(progress > .5f) amplitudeFractional(progress)
                else 0f
            }
        } }

        Box(
            Modifier.graphicsLayer { collapsedGraphicsLayer(progress) },
            contentAlignment = Alignment.Center, propagateMinConstraints = true
        ) {
            collapsedContent { progress }
        }
    }


    val expandedContent = @Composable {
        val progress by remember { derivedStateOf {
            (transition.value).let { progress ->
                if(progress > .5f) amplitudeFractional(progress)
                else 0f
            }
        } }

        Box(
            Modifier.graphicsLayer { expandedGraphicsLayer(progress) },
            contentAlignment = Alignment.Center, propagateMinConstraints = true
        ) {
            expandedContent { progress }
        }
    }

    Layout(
        listOf(collapsedContent, expandedContent),
        modifier.clipToBounds()
    ) { contents, constrains ->
        val barContentPlaceable = contents[0].first().measure(constrains)
        val contentPlaceable = contents[1].first().measure(constrains)

        val layoutWidth = lerp(barContentPlaceable.width, contentPlaceable.width, transition.value)
        val layoutHeight = lerp(barContentPlaceable.height, contentPlaceable.height, transition.value)

        layout(layoutWidth, layoutHeight) {
            if(transition.value >= .5f)
                contentPlaceable.place(0, 0)
            else
                barContentPlaceable.place(0, 0)
        }
    }
}