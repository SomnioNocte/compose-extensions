package com.somnionocte.compose_extensions

import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.util.fastRoundToInt
import kotlin.math.pow

/**
 * Extension property for GraphicsLayerScope
 * that provides a convenient way to set both scaleX and scaleY
 * to the same value, effectively scaling the content uniformly.
 *
 * When you get this property, it returns the current scaleX value.
 * When you set this property, both scaleX and scaleY are updated
 * with the provided value.
 */
var GraphicsLayerScope.scale: Float
    get() = this.scaleX
    set(value) {
        this.scaleX = value
        this.scaleY = value
    }

/**
 * Applies a blur effect to the content within the GraphicsLayerScope.
 * The blur radius is determined by the input value, with a special
 * non-linear scaling applied for smaller values to enhance visual impact.
 *
 * If the calculated blur radius is greater than 1, a BlurEffect
 * is applied using TileMode.Decal.
 * This means the blur will not extend beyond the original bounds of the content.
 * For blur values of 1 or less (after internal calculation), no blur effect is applied.
 *
 * @param value The desired blur radius. Smaller values (less than 5f) are
 * transformed non-linearly using value.pow(value * .2f) to create a
 * more pronounced effect, while larger values are used directly.
 * @see androidx.compose.ui.graphics.BlurEffect
 * @see androidx.compose.ui.graphics.TileMode#Decal
 */
fun GraphicsLayerScope.blur(value: Float) {
    val _value = (if(value < 5f) value.pow(value * .2f) else value).fastRoundToInt()
    if(_value > 1) this.renderEffect = BlurEffect(_value.toFloat(), _value.toFloat(), TileMode.Decal)
}