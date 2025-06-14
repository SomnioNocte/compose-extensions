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

/**
 * Retrieves the screen corner radius in Dp for the device.
 * This function attempts to get the physical rounded corner radius from the display
 * for Android S (API 31) and above.
 *
 * @return The screen corner radius in Dp.
 * Returns 0.dp if the corner radius cannot be determined or on older Android versions.
 * @see androidx.compose.ui.unit.Dp
 * @see android.os.Build.VERSION_CODES#S
 * @see android.view.Display#getRoundedCorner(int)
 */
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