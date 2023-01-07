package com.oddlyspaced.nothing.beacon.lib.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.oddlyspaced.nothing.beacon.lib.R
import com.oddlyspaced.nothing.beacon.lib.databinding.DeviceBinding
import com.oddlyspaced.nothing.beacon.lib.enum.LedConstant

class NothingLEDView: ConstraintLayout {

    private lateinit var binding: DeviceBinding

    var darkenCameraSection = true

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attributes: AttributeSet): super(context, attributes) {
        init(context)
    }

    constructor(context: Context, attributes: AttributeSet, defStyleAttribute: Int): super(context, attributes, defStyleAttribute) {
        init(context)
    }

    private fun init(context: Context) {
        binding = DeviceBinding.inflate(LayoutInflater.from(context), this, true)
        allLedBrightness = 0
    }

    private fun translateBrightnessToAlpha(brightness: Int): Float {
        (brightness / LedConstant.BRIGHTNESS_MAX.toFloat()).let {
            return if (it < 0.1f) 0.1f else it
        }
    }

    var cameraBrightness = LedConstant.BRIGHTNESS_MAX
        set(value) {
            field = value
            binding.imgDeviceLedCamera.alpha = translateBrightnessToAlpha(value)
        }

    var slantBrightness = LedConstant.BRIGHTNESS_MAX
        set(value) {
            field = value
            binding.imgDeviceLedVline.alpha = translateBrightnessToAlpha(value)
        }

    var centerBrightness = LedConstant.BRIGHTNESS_MAX
        set(value) {
            field = value
            binding.imgDeviceLedCenter.alpha = translateBrightnessToAlpha(value)
            if (darkenCameraSection) {
                binding.imgDeviceBackgroundOverlay.alpha = (value / LedConstant.BRIGHTNESS_MAX.toFloat()) * 0.1f
            }
        }

    var bottomStripBrightness = LedConstant.BRIGHTNESS_MAX
        set(value) {
            field = value
            binding.imgDeviceLedBottom.alpha = translateBrightnessToAlpha(value)
        }

    var bottomDotBrightness = LedConstant.BRIGHTNESS_MAX
        set(value) {
            field = value
            binding.imgDeviceLedBottomDot.alpha = translateBrightnessToAlpha(value)
        }

    var allLedBrightness = 0
        set(value) {
            cameraBrightness = value
            slantBrightness = value
            centerBrightness = value
            bottomStripBrightness = value
            bottomDotBrightness = value
            field = value
        }

    private val ledBottomStripIntensityDrawables = arrayOf(null, R.drawable.led_strip_bottom_1, R.drawable.led_strip_bottom_2, R.drawable.led_strip_bottom_3, R.drawable.led_strip_bottom_4, R.drawable.led_strip_bottom_5, R.drawable.led_strip_bottom_6, R.drawable.led_strip_bottom_7, R.drawable.led_strip_bottom_8)
    var bottomStripIntensity = 8
        set(value) {
            if (value == 0) {
                binding.imgDeviceLedBottom.setImageDrawable(null)
            }
            else {
                binding.imgDeviceLedBottom.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        ledBottomStripIntensityDrawables[value]!!
                    )
                )
            }
            field = value
        }

    fun setOnCameraSectionClickListener(listener: OnClickListener) = binding.viewTouchLedCamera.setOnClickListener(listener)
    fun setOnVLineSectionClickListener(listener: OnClickListener) = binding.viewTouchLedVline.setOnClickListener(listener)
    fun setOnCenterSectionClickListener(listener: OnClickListener) = binding.viewTouchLedCenter.setOnClickListener(listener)
    fun setOnBottomStripSectionClickListener(listener: OnClickListener) = binding.viewTouchLedBottom.setOnClickListener(listener)
    fun setOnBottomDotSectionClickListener(listener: OnClickListener) = binding.viewTouchLedBottomDot.setOnClickListener(listener)
}