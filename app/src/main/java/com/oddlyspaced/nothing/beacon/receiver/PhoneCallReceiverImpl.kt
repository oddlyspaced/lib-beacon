package com.oddlyspaced.nothing.beacon.receiver

import android.content.Context
import com.oddlyspaced.nothing.beacon.util.Logger
import java.util.*

//
//class PhoneStateReceiver: BroadcastReceiver() {
//
//    private var lastState = "123"
//
//    // RINGING -> OFFHOOK -> IDLE
//    override fun onReceive(_context: Context?, _intent: Intent?) {
//        _context?.let { context ->
//            _intent?.let { intent ->
//                if (intent.action != null && intent.action ==  TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
//                    Logger.d("PhoneStateReceiver: action: $lastState  " + intent.extras?.getString(TelephonyManager.EXTRA_STATE))
//                    lastState = intent.extras?.getString(TelephonyManager.EXTRA_STATE).toString()
//                }
//            }
//        }
////        LedAnimator(RootLedControllerImpl()).resourceAnimation(context!!, com.oddlyspaced.nothing.beacon.lib.R.raw.data_abra, com.oddlyspaced.nothing.beacon.lib.R.raw.ringtone_abra)
//    }
//}

class PhoneCallReceiverImpl: PhoneCallReceiver() {
    override fun onCustomCallStateChanged(context: Context?, state: Int, number: String?) {
        Logger.d("onCustomCallStateChanges: Phone Number: $number $state")
        super.onCustomCallStateChanged(context, state, number)
    }

    override fun onIncomingCallEnded(ctx: Context?, number: String?, start: Date?, end: Date?) {
        Logger.d("onIncomingCallEnded")
        super.onIncomingCallEnded(ctx, number, start, end)
    }

    override fun onIncomingCallStarted(ctx: Context?, number: String?, start: Date?) {
        Logger.d("onIncomingStarted")
        super.onIncomingCallStarted(ctx, number, start)
    }

    override fun onMissedCall(ctx: Context?, number: String?, start: Date?) {
        Logger.d("onMissedCall")
        super.onMissedCall(ctx, number, start)
    }

}