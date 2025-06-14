package com.somnionocte.compose_extensions

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp

/**
 * Creates a [RoundedCornerShape] with vertically symmetrical rounded corners.
 * This function allows you to specify a single radius for the top corners and
 * a single radius for the bottom corners, expressed in [Dp] units.
 *
 * @param top The radius for both the top-start and top-end corners.
 * @param bottom The radius for both the bottom-start and bottom-end corners.
 * @return A [RoundedCornerShape] instance with the specified vertical corner radii.
*/
fun VRoundedCornerShape(
    top: Dp,
    bottom: Dp
) = RoundedCornerShape(
    topStart = CornerSize(top),
    topEnd = CornerSize(top),
    bottomEnd = CornerSize(bottom),
    bottomStart = CornerSize(bottom)
)

/**
 * Creates a [RoundedCornerShape] with vertically symmetrical rounded corners.
 * This function allows you to specify a single radius for the top corners and
 * a single radius for the bottom corners, expressed as [Int] pixel values.
 *
 * @param top The radius for both the top-start and top-end corners.
 * @param bottom The radius for both the bottom-start and bottom-end corners.
 * @return A [RoundedCornerShape] instance with the specified vertical corner radii.
 */
fun VRoundedCornerShape(
    top: Int,
    bottom: Int
) = RoundedCornerShape(
    topStart = CornerSize(top),
    topEnd = CornerSize(top),
    bottomEnd = CornerSize(bottom),
    bottomStart = CornerSize(bottom)
)

/**
 * Creates a [RoundedCornerShape] with horizontally symmetrical rounded corners.
 * This function allows you to specify a single radius for the start corners and
 * a single radius for the end corners, expressed in [Dp] units.
 *
 * @param start The radius for both the top-start and bottom-start corners.
 * @param end The radius for both the top-end and bottom-end corners.
 * @return A [RoundedCornerShape] instance with the specified horizontal corner radii.
 */
fun HRoundedCornerShape(
    start: Dp,
    end: Dp
) = RoundedCornerShape(
    topStart = CornerSize(start),
    topEnd = CornerSize(end),
    bottomEnd = CornerSize(end),
    bottomStart = CornerSize(start)
)

/**
 * Creates a [RoundedCornerShape] with horizontally symmetrical rounded corners.
 * This function allows you to specify a single radius for the start corners and
 * a single radius for the end corners, expressed as [Int] pixel values.
 *
 * @param start The radius for both the top-start and bottom-start corners.
 * @param end The radius for both the top-end and bottom-end corners.
 * @return A [RoundedCornerShape] instance with the specified horizontal corner radii.
 */
fun HRoundedCornerShape(
    start: Int,
    end: Int
) = RoundedCornerShape(
    topStart = CornerSize(start),
    topEnd = CornerSize(end),
    bottomEnd = CornerSize(end),
    bottomStart = CornerSize(start)
)