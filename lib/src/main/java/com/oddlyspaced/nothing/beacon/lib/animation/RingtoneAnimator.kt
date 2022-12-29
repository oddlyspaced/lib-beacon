package com.oddlyspaced.nothing.beacon.lib.animation

import android.content.Context
import android.media.MediaPlayer
import com.oddlyspaced.nothing.beacon.lib.R
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl
import com.oddlyspaced.nothing.beacon.lib.enum.Led
import com.oddlyspaced.nothing.beacon.lib.enum.NothingRingtone
import com.oddlyspaced.nothing.beacon.lib.enum.Section
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RingtoneAnimator(context: Context, anim: NothingRingtone) {

    private val controller = RootLedControllerImpl()
    private val mediaPlayer = MediaPlayer.create(context, anim.media)
    private val ledAnimationData = arrayListOf<Array<String>>()

    init {
        context.resources.openRawResource(anim.data).bufferedReader().readLines()
            .forEach {
                ledAnimationData.add(it.split(",").toTypedArray())
            }
    }

    var isRinging = false

    fun play() {
        mediaPlayer.start()
        mediaPlayer.isLooping = true
        isRinging = true
        animate()
    }

    private fun animate() {
        CoroutineScope(Dispatchers.IO).launch {
            while (isRinging) {
                if (mediaPlayer.currentPosition < mediaPlayer.duration) {
                    val durLoc = mediaPlayer.currentPosition.toDouble() / mediaPlayer.duration
                    val pos = (durLoc * ledAnimationData.size).toInt()
                    if (pos < ledAnimationData.size) {
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
    }

    fun stop() {
        isRinging = false
        mediaPlayer.seekTo(0)
        mediaPlayer.stop()
        mediaPlayer.prepareAsync()
    }

}