package com.oddlyspaced.nothing.beacon

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.MutableLiveData
import com.oddlyspaced.nothing.beacon.databinding.ActivityRootBinding
import com.oddlyspaced.nothing.beacon.lib.LedAnimator
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl
import com.oddlyspaced.nothing.beacon.lib.constant.Led
import com.oddlyspaced.nothing.beacon.lib.constant.LedConstant
import com.oddlyspaced.nothing.beacon.lib.constant.Section
import kotlin.math.abs

class RootActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRootBinding.inflate(layoutInflater) }
    private val ledController = RootLedControllerImpl()

    private val globalCameraBrightness = MutableLiveData(0)
    private val globalVLineBrightness = MutableLiveData(0)
    private val globalCenterBrightness = MutableLiveData(0)
    private val globalBottomStripBrightness = MutableLiveData(0)
    private val globalBottomDotBrightness = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        globalCameraBrightness.observe(this) {
            binding.nothingDevice.cameraBrightness = it
            ledController.setSectionBrightness(Section.CAMERA_RING, it)
        }

        globalVLineBrightness.observe(this) {
            binding.nothingDevice.slantBrightness = it
            ledController.setSectionBrightness(Section.SLANT, it)
        }

        globalCenterBrightness.observe(this) {
            binding.nothingDevice.centerBrightness = it
            ledController.setSectionBrightness(Section.CENTER_RING, it)
        }

        globalBottomStripBrightness.observe(this) {
            binding.nothingDevice.bottomStripBrightness = it
            ledController.setSectionBrightness(Section.EXCLAMATION_BAR, it)
        }

        globalBottomDotBrightness.observe(this) {
            binding.nothingDevice.bottomDotBrightness = it
            ledController.setSectionBrightness(Section.EXCLAMATION_DOT, it)
        }

        binding.nothingDevice.apply {
            setOnCameraSectionClickListener {
                animateLED(globalCameraBrightness)
            }
            setOnVLineSectionClickListener {
                animateLED(globalVLineBrightness)
            }
            setOnCenterSectionClickListener {
                animateLED(globalCenterBrightness)
            }
            setOnBottomStripSectionClickListener {
                animateLED(globalBottomStripBrightness)
            }
            setOnBottomDotSectionClickListener {
                animateLED(globalBottomDotBrightness)
            }
        }
    }

    private fun animateLED(ledRef: MutableLiveData<Int>) {
        val animSet = AnimatorSet()
        animSet.playSequentially(listOf(
            ValueAnimator.ofInt(LedConstant.BRIGHTNESS_MIN, LedConstant.BRIGHTNESS_MAX / 2).apply {
                duration = 100L
                addUpdateListener {
                    ledRef.postValue(it.animatedValue as Int)
                }
            },
            ValueAnimator.ofInt(LedConstant.BRIGHTNESS_MIN, LedConstant.BRIGHTNESS_MAX / 2).apply {
                startDelay = 100L
                duration = 100L
                addUpdateListener {
                    ledRef.postValue(it.animatedValue as Int)
                }
            },
            ValueAnimator.ofInt(LedConstant.BRIGHTNESS_MIN / 4, LedConstant.BRIGHTNESS_MAX).apply {
                startDelay = 200L
                duration = 3000L
                interpolator = AccelerateInterpolator()
                addUpdateListener {
                    ledRef.postValue(it.animatedValue as Int)
                }
            }
        ))
        animSet.start()
    }
}