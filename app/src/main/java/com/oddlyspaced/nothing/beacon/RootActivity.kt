package com.oddlyspaced.nothing.beacon

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oddlyspaced.nothing.beacon.databinding.ActivityRootBinding
import com.oddlyspaced.nothing.beacon.lib.LedAnimator
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl
import kotlin.math.abs

class RootActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRootBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.nothingDevice.apply {
            setOnCameraSectionClickListener {
                cameraBrightness = abs(cameraBrightness - 4095)
            }
            setOnVLineSectionClickListener {
                slantBrightness = abs(slantBrightness - 4095)
            }
            setOnCenterSectionClickListener {
                centerBrightness = abs(centerBrightness - 4095)
            }
            setOnBottomStripSectionClickListener {
                bottomStripBrightness = abs(bottomStripBrightness - 4095)
            }
            setOnBottomDotSectionClickListener {
                bottomDotBrightness = abs(bottomDotBrightness - 4095)
            }
        }

    }
}