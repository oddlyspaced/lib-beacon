package com.oddlyspaced.nothing.beacon.lib.animation

import android.content.Context
import android.media.MediaPlayer
import com.oddlyspaced.nothing.beacon.lib.interfaces.RootLedControllerImpl
import com.oddlyspaced.nothing.beacon.lib.enum.Led
import com.oddlyspaced.nothing.beacon.lib.enum.NothingRingtone
import com.oddlyspaced.nothing.beacon.lib.enum.Section
import com.oddlyspaced.nothing.beacon.lib.ui.NothingLEDView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResourceAnimator(context: Context, anim: NothingRingtone, private val ledView: NothingLEDView? = null) {

    private val controller = RootLedControllerImpl()
    private val mediaPlayer = MediaPlayer.create(context, anim.media)
    private val ledAnimationData = context.resources.openRawResource(anim.data).bufferedReader().readLines().map {
        it.split(",").toTypedArray()
    }

    fun play() {
        mediaPlayer.setOnPreparedListener {
            isPlaying = true
            it.start()
            animate()
        }
    }

    private fun animate() {
        CoroutineScope(Dispatchers.IO).launch {
            while (isPlaying && mediaPlayer.currentPosition < mediaPlayer.duration - 1) {
                val durLoc = mediaPlayer.currentPosition.toDouble() / mediaPlayer.duration
                    val pos = (durLoc * ledAnimationData.size).toInt()
                    if (pos < ledAnimationData.size) {
                        val data = ledAnimationData[pos]
                        controller.setLedBrightness(Led.fromCode(7), data[0].toInt())
                        controller.setLedBrightness(Led.fromCode(1), data[1].toInt())
                        controller.setSectionBrightness(Section.CENTER_RING, data[2].toInt())
                        controller.setSectionBrightness(Section.SLANT, data[3].toInt())
                        controller.setLedBrightness(Led.fromCode(16), data[4].toInt())

                        ledView?.let {
                            it.cameraBrightness = data[0].toInt()
                            it.slantBrightness = data[1].toInt()
                            it.centerBrightness = data[2].toInt()
                            it.bottomStripBrightness = data[3].toInt()
                            it.bottomDotBrightness = data[4].toInt()
                        }
                    }
            }
        }
    }

    private var isPlaying = false

    fun stop() {
        isPlaying = false
        mediaPlayer.stop()
    }

}