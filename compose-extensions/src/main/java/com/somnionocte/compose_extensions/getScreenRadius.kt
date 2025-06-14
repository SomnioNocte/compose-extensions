package com.somnionocte.compose_extensions

import android.os.Build
import android.view.RoundedCorner
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//It is assumed that the radius of the screen rounding will not change while the application is running :)
private var cachedCornerRadius: Dp? = null

@Composable
fun getScreenCornerRadius(): Dp {
    if(cachedCornerRadius == null) {
        cachedCornerRadius = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            with(LocalDensity.current) {
                LocalView.current.display.getRoundedCorner(RoundedCorner.POSITION_TOP_LEFT)
                    ?.radius
                    ?.coerceAtLeast(0)
                    ?.toDp() ?: 0.dp
            }
        } else {
            0.dp
        }
    }

    return cachedCornerRadius ?: 0.dp
}