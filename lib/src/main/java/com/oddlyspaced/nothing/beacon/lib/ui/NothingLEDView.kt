package com.oddlyspaced.nothing.beacon.lib.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.oddlyspaced.nothing.beacon.lib.databinding.DeviceBinding

class NothingLEDView: ConstraintLayout {

    private lateinit var binding: DeviceBinding

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
    }

    private fun translateBrightnessToAlpha(brightness: Int): Float {
        (brightness / 4095f).let {
            return if (it < 0.1f) 0.1f else it
        }
    }

    var cameraBrightness = 4095
        set(value) {
            field = value
            binding.imgDeviceLedCamera.alpha = translateBrightnessToAlpha(value)
        }

    var slantBrightness = 4095
        set(value) {
            field = value
            binding.imgDeviceLedVline.alpha = translateBrightnessToAlpha(value)
        }

    var centerBrightness = 4095
        set(value) {
            field = value
            binding.imgDeviceLedCenter.alpha = translateBrightnessToAlpha(value)
        }

    var bottomStripBrightness = 4095
        set(value) {
            field = value
            binding.imgDeviceLedBottom.alpha = translateBrightnessToAlpha(value)
        }

    var bottomDotBrightness = 4095
        set(value) {
            field = value
            binding.imgDeviceLedBottomDot.alpha = translateBrightnessToAlpha(value)
        }

    fun setOnCameraSectionClickListener(listener: OnClickListener) = binding.viewTouchLedCamera.setOnClickListener(listener)
    fun setOnVLineSectionClickListener(listener: OnClickListener) = binding.viewTouchLedVline.setOnClickListener(listener)
    fun setOnCenterSectionClickListener(listener: OnClickListener) = binding.viewTouchLedCenter.setOnClickListener(listener)
    fun setOnBottomStripSectionClickListener(listener: OnClickListener) = binding.viewTouchLedBottom.setOnClickListener(listener)
    fun setOnBottomDotSectionClickListener(listener: OnClickListener) = binding.viewTouchLedBottomDot.setOnClickListener(listener)
}