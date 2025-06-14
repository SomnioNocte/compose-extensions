package com.somnionocte.compose_extensions

import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.util.fastRoundToInt
import kotlin.math.pow

var GraphicsLayerScope.scale: Float
    get() = this.scaleX
    set(value) {
        this.scaleX = value
        this.scaleY = value
    }

fun GraphicsLayerScope.blur(value: Float) {
    val _value = (if(value < 5f) value.pow(value * .2f) else value).fastRoundToInt()
    if(_value > 1) this.renderEffect = BlurEffect(_value.toFloat(), _value.toFloat(), TileMode.Decal)
}