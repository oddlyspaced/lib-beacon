package com.oddlyspaced.nothing.beacon.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.oddlyspaced.nothing.beacon.lib.LedAnimator
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl

class PhoneStateReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("SPACEWAR", "Receiver Receving ${context == null}")
//        if (context != null) {
//            Log.d("SPACEWAR", "Context Null!")
//        }
        LedAnimator(RootLedControllerImpl()).resourceAnimation(context!!, com.oddlyspaced.nothing.beacon.lib.R.raw.data_abra, com.oddlyspaced.nothing.beacon.lib.R.raw.ringtone_abra)
//        intent?.let { it ->
//            Log.d("SPACEWAR", it.action.toString() + " // " + it.extras!!.keySet().toTypedArray())
//            it.extras!!.keySet().forEachIndexed { index, s ->
//                Log.d("SPACEWAR_KEY", s)
//            }
//        }
    }
}