package com.somnionocte.compose_extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.LayoutDirection

operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    val ltr = LayoutDirection.Ltr
    return PaddingValues(
        calculateStartPadding(ltr) + other.calculateStartPadding(ltr),
        calculateTopPadding() + other.calculateTopPadding(),
        calculateEndPadding(ltr) + other.calculateEndPadding(ltr),
        calculateBottomPadding() + other.calculateBottomPadding()
    )
}

operator fun PaddingValues.minus(other: PaddingValues): PaddingValues {
    val ltr = LayoutDirection.Ltr
    return PaddingValues(
        calculateStartPadding(ltr) - other.calculateStartPadding(ltr),
        calculateTopPadding() - other.calculateTopPadding(),
        calculateEndPadding(ltr) - other.calculateEndPadding(ltr),
        calculateBottomPadding() - other.calculateBottomPadding()
    )
}

fun lerp(start: PaddingValues, stop: PaddingValues, fraction: Float): PaddingValues {
    val ltr = LayoutDirection.Ltr

    return PaddingValues(
        start = androidx.compose.ui.unit.lerp(start.calculateStartPadding(ltr), stop.calculateStartPadding(ltr), fraction),
        top = androidx.compose.ui.unit.lerp(start.calculateTopPadding(), stop.calculateTopPadding(), fraction),
        end = androidx.compose.ui.unit.lerp(start.calculateEndPadding(ltr), stop.calculateEndPadding(ltr), fraction),
        bottom = androidx.compose.ui.unit.lerp(start.calculateBottomPadding(), stop.calculateBottomPadding(), fraction),
    )
}