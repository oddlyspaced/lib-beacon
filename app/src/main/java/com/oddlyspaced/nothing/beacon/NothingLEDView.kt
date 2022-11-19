package com.oddlyspaced.nothing.beacon

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.oddlyspaced.nothing.beacon.databinding.DeviceBinding

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
        (brightness / 4096f).let {
            return if (it < 0.1f) 0.1f else it
        }
    }

    var cameraBrightness = 4096
        set(value) {
            field = value
            binding.imgDeviceLedCamera.alpha = translateBrightnessToAlpha(value)
        }

    var slantBrightness = 4096
        set(value) {
            field = value
            binding.imgDeviceLedVline.alpha = translateBrightnessToAlpha(value)
        }

    var centerBrightness = 4096
        set(value) {
            field = value
            binding.imgDeviceLedCenter.alpha = translateBrightnessToAlpha(value)
        }

    var bottomStripBrightness = 4096
        set(value) {
            field = value
            binding.imgDeviceLedBottom.alpha = translateBrightnessToAlpha(value)
        }

    var bottomDotBrightness = 4096
        set(value) {
            field = value
            binding.imgDeviceLedBottomDot.alpha = translateBrightnessToAlpha(value)
        }
}