package com.somnionocte.compose_extensions

import androidx.compose.ui.unit.Dp
import kotlin.math.pow

/**
 * Calculates the fractional position of a Int value within a specified range.
 * This function is useful for mapping a Int value to a normalized float between 0.0 and 1.0,
 * representing its progress or position relative to a start and end point.
 *
 * @param value The Int value whose fractional position is to be determined.
 * @param from The starting Int value of the range.
 * @param to The ending Int value of the range.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range if value falls outside from and to.
 * Defaults to false.
 * @return A Float representing the fractional position of value within the range defined by from and to.
 * Returns 0.0f if from is equal to to to prevent division by zero.
 */
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

/**
 * Calculates the fractional position of a Int value within a closed floating-point range.
 * This is an overloaded function that provides a convenient way to use ClosedFloatingPointRange
 * for defining the animation range, simplifying the function call.
 *
 * @param value The Int value whose fractional position is to be determined.
 * @param range The ClosedFloatingPointRange defining the start and end Int values.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range. Defaults to true.
 * @return A Float representing the fractional position of value within the specified range.
 */
fun fractionify(
    value: Int,
    range: IntRange,
    bounded: Boolean = true
) = fractionify(value, range.first, range.last, bounded)



/**
 * Calculates the fractional position of a Double value within a specified range.
 * This function is useful for mapping a Double value to a normalized float between 0.0 and 1.0,
 * representing its progress or position relative to a start and end point.
 *
 * @param value The Double value whose fractional position is to be determined.
 * @param from The starting Double value of the range.
 * @param to The ending Double value of the range.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range if value falls outside from and to.
 * Defaults to false.
 * @return A Float representing the fractional position of value within the range defined by from and to.
 * Returns 0.0f if from is equal to to to prevent division by zero.
 */
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

/**
 * Calculates the fractional position of a Double value within a closed floating-point range.
 * This is an overloaded function that provides a convenient way to use ClosedFloatingPointRange
 * for defining the animation range, simplifying the function call.
 *
 * @param value The Double value whose fractional position is to be determined.
 * @param range The ClosedFloatingPointRange defining the start and end Double values.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range. Defaults to true.
 * @return A Float representing the fractional position of value within the specified range.
 */
fun fractionify(
    value: Double,
    range: ClosedFloatingPointRange<Double>,
    bounded: Boolean = true
) = fractionify(value, range.start, range.endInclusive, bounded)



/**
 * Calculates the fractional position of a Float value within a specified range.
 * This function is useful for mapping a Float value to a normalized float between 0.0 and 1.0,
 * representing its progress or position relative to a start and end point.
 *
 * @param value The Float value whose fractional position is to be determined.
 * @param from The starting Float value of the range.
 * @param to The ending Float value of the range.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range if value falls outside from and to.
 * Defaults to false.
 * @return A Float representing the fractional position of value within the range defined by from and to.
 * Returns 0.0f if from is equal to to to prevent division by zero.
 */
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

/**
 * Calculates the fractional position of a Float value within a closed floating-point range.
 * This is an overloaded function that provides a convenient way to use ClosedFloatingPointRange
 * for defining the animation range, simplifying the function call.
 *
 * @param value The Float value whose fractional position is to be determined.
 * @param range The ClosedFloatingPointRange defining the start and end Float values.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range. Defaults to true.
 * @return A Float representing the fractional position of value within the specified range.
 */
fun fractionify(
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    bounded: Boolean = true
) = fractionify(value, range.start, range.endInclusive, bounded)


/**
 * Calculates the fractional position of a Dp value within a specified range.
 * This function is useful for mapping a Dp value to a normalized float between 0.0 and 1.0,
 * representing its progress or position relative to a start and end point.
 *
 * @param value The Dp value whose fractional position is to be determined.
 * @param from The starting Dp value of the range.
 * @param to The ending Dp value of the range.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range if value falls outside from and to.
 * Defaults to false.
 * @return A Float representing the fractional position of value within the range defined by from and to.
 * Returns 0.0f if from is equal to to to prevent division by zero.
 */
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

/**
 * Calculates the fractional position of a Dp value within a closed floating-point range.
 * This is an overloaded function that provides a convenient way to use ClosedFloatingPointRange
 * for defining the animation range, simplifying the function call.
 *
 * @param value The Dp value whose fractional position is to be determined.
 * @param range The ClosedFloatingPointRange defining the start and end Dp values.
 * @param bounded If true, the returned fraction will be coerced (clamped) between 0.0f and 1.0f.
 * If false, the fraction can extend beyond this range. Defaults to true.
 * @return A Float representing the fractional position of value within the specified range.
 */
fun fractionify(
    value: Dp,
    range: ClosedFloatingPointRange<Dp>,
    bounded: Boolean = true
) = fractionify(value, range.start, range.endInclusive, bounded)

/**
 * Transforms a fractional value (typically between 0.0f and 1.0f) into an "amplitude" fraction.
 * This function squares the result of mapping the input fractional value from the [0, 1] range to the [-1, 1] range.
 * The output value will always be between 0.0f and 1.0f, peaking at 1.0f when fractional is 0.0f or 1.0f,
 * and reaching 0.0f when fractional is 0.5f.
 *
 * This type of transformation is often useful for creating animation curves that
 * represent an "amplitude" effect, where an animation might start and end at a high point
 * and dip in the middle, or vice-versa, depending on how it's applied.
 *
 * @param fractional A Float value, typically expected to be within the range [0.0f, 1.0f].
 * @return A Float representing the amplitude fraction, always between 0.0f and 1.0f.
 */
fun amplitudeFractional(fractional: Float): Float = ((fractional * 2) - 1).pow(2f).coerceIn(0f..1f)