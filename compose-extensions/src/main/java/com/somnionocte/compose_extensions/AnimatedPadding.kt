package com.somnionocte.compose_extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.layout
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.fastCoerceIn

fun Modifier.padding(padding: () -> PaddingValues) = composed {
    layout { content, constrains ->
        val currentPadding = padding()

        val currentPaddingWidthDiff = currentPadding.run {
            calculateLeftPadding(layoutDirection) + calculateRightPadding(layoutDirection)
        }.roundToPx()
        val currentPaddingHeightDiff = currentPadding.run {
            calculateTopPadding() + calculateBottomPadding()
        }.roundToPx()

        val minWidth = (constrains.minWidth - currentPaddingWidthDiff).fastCoerceAtLeast(0)
        val minHeight = (constrains.minHeight - currentPaddingHeightDiff).fastCoerceAtLeast(0)

        val contentPlaceable = content.measure(constrains.copy(
            minWidth = minWidth,
            maxWidth = (constrains.maxWidth - currentPaddingWidthDiff).fastCoerceAtLeast(minWidth),
            minHeight = minHeight,
            maxHeight = (constrains.maxHeight - currentPaddingHeightDiff).fastCoerceAtLeast(minHeight)
        ))

        layout(
            (contentPlaceable.width + currentPaddingWidthDiff).fastCoerceIn(constrains.minWidth, constrains.maxWidth),
            (contentPlaceable.height + currentPaddingHeightDiff).fastCoerceIn(constrains.minHeight, constrains.maxHeight)
        ) {
            contentPlaceable.place(
                currentPadding.calculateStartPadding(layoutDirection).roundToPx(),
                currentPadding.calculateTopPadding().roundToPx()
            )
        }
    }
}