package com.oddlyspaced.nothing.beacon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oddlyspaced.nothing.beacon.lib.LedAnimator
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        LedAnimator(RootLedControllerImpl()).testPattern()
    }
}