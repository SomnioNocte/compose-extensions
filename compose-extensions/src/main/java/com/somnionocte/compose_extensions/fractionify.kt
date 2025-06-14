package com.somnionocte.compose_extensions

import androidx.compose.ui.unit.Dp
import kotlin.math.pow

//Int
fun fractionify(
    value: Int,
    from: Int,
    to: Int,
    bounded: Boolean = true
): Float {
    if(from == to) return 0f

    val fraction = ((value - from).toDouble() / (to - from)).toFloat()
    return if(bounded) fraction.coerceIn(0f, 1f) else fraction
}

fun fractionify(
    value: Int,
    range: IntRange,
    bounded: Boolean = true
) = fractionify(value, range.first, range.last, bounded)



//Double
fun fractionify(
    value: Double,
    from: Double,
    to: Double,
    bounded: Boolean = true
): Float {
    if(from == to) return 0f

    val fraction = ((value - from) / (to - from)).toFloat()
    return if(bounded) fraction.coerceIn(0f, 1f) else fraction
}

fun fractionify(
    value: Double,
    range: ClosedFloatingPointRange<Double>,
    bounded: Boolean = true
) = fractionify(value, range.start, range.endInclusive, bounded)



//Float
fun fractionify(
    value: Float,
    from: Float,
    to: Float,
    bounded: Boolean = true
): Float {
    if(from == to) return 0f

    val fraction = ((value - from).toDouble() / (to - from)).toFloat()
    return if(bounded) fraction.coerceIn(0f, 1f) else fraction
}

fun fractionify(
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    bounded: Boolean = true
) = fractionify(value, range.start, range.endInclusive, bounded)



//Dp
fun fractionify(
    value: Dp,
    from: Dp,
    to: Dp,
    bounded: Boolean = false
): Float {
    if(from == to) return 0f

    val fraction = ((value - from).value.toDouble() / (to - from).value).toFloat()
    return if(bounded) fraction.coerceIn(0f, 1f) else fraction
}

fun fractionify(
    value: Dp,
    range: ClosedFloatingPointRange<Dp>,
    bounded: Boolean = true
) = fractionify(value, range.start, range.endInclusive, bounded)

fun amplitudeFractional(fractional: Float): Float = ((fractional * 2) - 1).pow(2f)