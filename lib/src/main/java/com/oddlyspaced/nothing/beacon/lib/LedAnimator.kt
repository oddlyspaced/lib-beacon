package com.oddlyspaced.nothing.beacon.lib

import android.animation.ValueAnimator
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.oddlyspaced.nothing.beacon.lib.enum.Led
import com.oddlyspaced.nothing.beacon.lib.enum.LedConstant
import com.oddlyspaced.nothing.beacon.lib.enum.Section
import com.oddlyspaced.nothing.beacon.lib.interfaces.LedController
import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    // TODO: cache anim data in memory
    fun resourceAnimation(context: Context, animData: Int, mediaRes: Int) {
        // create media player object for our ringtone file
        val mediaPlayer = MediaPlayer.create(context, mediaRes)
        // load animation csv data for it
        val ledAnimationData = arrayListOf<Array<String>>()
        context.resources.openRawResource(animData).bufferedReader().readLines().forEach {
            ledAnimationData.add(it.split(",").toTypedArray())
        }
        // start media player
        mediaPlayer.start()
        CoroutineScope(Dispatchers.IO).launch {
            while (mediaPlayer.currentPosition != mediaPlayer.duration - 1) {
                // spread data across duration
                val durLoc = mediaPlayer.currentPosition.toDouble() / mediaPlayer.duration
                val pos = (durLoc * ledAnimationData.size).toInt()
                val data = ledAnimationData[pos]
                controller.setLedBrightness(Led.fromCode(7), data[0].toInt())
                controller.setLedBrightness(Led.fromCode(1), data[1].toInt())
                controller.setSectionBrightness(Section.CENTER_RING, data[2].toInt())
                controller.setSectionBrightness(Section.SLANT, data[3].toInt())
                controller.setLedBrightness(Led.fromCode(16), data[4].toInt())
            }
        }
    }

}