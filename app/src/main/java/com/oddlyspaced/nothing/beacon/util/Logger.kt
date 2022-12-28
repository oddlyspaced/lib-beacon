package com.oddlyspaced.nothing.beacon.util

import android.util.Log

object Logger {
    private const val TAG = "BEACON_SPACEWAR"
    fun d(message: String, tag: String = TAG) {
        Log.d(tag, message)
    }
}