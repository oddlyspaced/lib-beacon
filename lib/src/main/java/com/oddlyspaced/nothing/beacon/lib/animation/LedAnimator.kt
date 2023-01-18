package com.oddlyspaced.nothing.beacon.lib.animation

import android.animation.ValueAnimator
import com.oddlyspaced.nothing.beacon.lib.enum.Led
import com.oddlyspaced.nothing.beacon.lib.enum.LedConstant
import com.oddlyspaced.nothing.beacon.lib.enum.Section
import com.oddlyspaced.nothing.beacon.lib.interfaces.LedController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/***
 * Misc animations for the LED
 * @param controller Controller implementation (typically RootLedControllerImpl)
 */
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

    fun wiredChargingAnimation(batteryValue: Int) {
        val leds = arrayOf(16, 13, 11, 9, 12, 10, 14, 15, 8)
        controller.setSectionBrightness(Section.ALL_WHITE_LEDS, 0)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            // fill all leds to max
            for (led in leds) {
                controller.setLedBrightness(Led.fromCode(led), 4095)
                delay(16)
            }
            // wait for some secs
            delay(16)
            // calculate how much leds to fill for battery percentage
            // uptoLed = index corresponding to leds array which tells as to what all leds should remain lighted
            val uptoLed = ((batteryValue / 100.0) * leds.size).toInt()
            // turn off all leds after uptoLed index
            for (i in (uptoLed until leds.size).reversed()) {
                if (i == 0) // let dot led remain lit
                    continue
                controller.setLedBrightness(Led.fromCode(leds[i]), 0)
                delay(16)
            }
        }
    }

}