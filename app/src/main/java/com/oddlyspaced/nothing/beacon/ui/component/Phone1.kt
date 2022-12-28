package com.oddlyspaced.nothing.beacon.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.oddlyspaced.nothing.beacon.lib.R

private fun translateBrightnessToAlpha(brightness: Int): Float {
    (brightness / 4095f).let {
        return if (it < 0.1f) 0.1f else it
    }
}

@Composable
fun Phone1(
    modifier: Modifier,
    cameraBrightness: Int,
    vlineBrightness: Int,
    centerBrightness: Int,
    bottomStripBrightness: Int,
    bottomDotBrightness: Int
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "",
        )
        Image(
            painter = painterResource(id = R.drawable.led_strip_camera),
            contentDescription = "",
            alpha = translateBrightnessToAlpha(cameraBrightness)
        )
        Image(
            painter = painterResource(id = R.drawable.led_strip_vline),
            contentDescription = "",
            alpha = translateBrightnessToAlpha(vlineBrightness)
        )
        Image(
            painter = painterResource(id = R.drawable.led_strip_center),
            contentDescription = "",
            alpha = translateBrightnessToAlpha(centerBrightness)
        )
        Image(
            painter = painterResource(id = R.drawable.led_strip_bottom),
            contentDescription = "",
            alpha = translateBrightnessToAlpha(bottomStripBrightness)
        )
        Image(
            painter = painterResource(id = R.drawable.led_dot_bottom),
            contentDescription = "",
            alpha = translateBrightnessToAlpha(bottomDotBrightness)
        )
    }
}


@Composable
fun Phone1(
    cameraBrightness: Int,
    vlineBrightness: Int,
    centerBrightness: Int,
    bottomStripBrightness: Int,
    bottomDotBrightness: Int
) = Phone1(
    modifier = Modifier,
    cameraBrightness,
    vlineBrightness,
    centerBrightness,
    bottomStripBrightness,
    bottomDotBrightness
)
