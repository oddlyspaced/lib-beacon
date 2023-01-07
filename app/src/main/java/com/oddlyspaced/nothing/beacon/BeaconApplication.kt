package com.oddlyspaced.nothing.beacon

import android.app.Application
import com.google.android.material.color.DynamicColors

class BeaconApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}