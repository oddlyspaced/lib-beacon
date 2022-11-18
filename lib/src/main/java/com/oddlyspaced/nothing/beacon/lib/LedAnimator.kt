package com.oddlyspaced.nothing.beacon.lib

import android.animation.ValueAnimator
import com.oddlyspaced.nothing.beacon.lib.constant.LedConstant
import com.oddlyspaced.nothing.beacon.lib.constant.Section
import com.oddlyspaced.nothing.beacon.lib.interfaces.LedController

class LedAnimator(private val controller: LedController) {
    fun testPattern() {
        ValueAnimator.ofInt(LedConstant.BRIGHTNESS_MIN, LedConstant.BRIGHTNESS_MAX).apply {
            addUpdateListener {
                val value = it.animatedValue as Int
                controller.setSectionBrightness(Section.ALL_WHITE_LEDS, value)
            }
            duration = 2000L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
        }.start()
    }
}