package com.oddlyspaced.nothing.beacon.receiver

import android.content.Context
import android.content.Intent
import com.oddlyspaced.nothing.beacon.util.Logger
import java.util.*

class PhoneCallReceiverImpl: PhoneCallReceiver() {
    override fun onCustomCallStateChanged(context: Context?, state: Int, number: String?) {
        Logger.d("onCustomCallStateChanges: Phone Number: $number $state")
        context?.sendBroadcast(Intent("com.oddly.led"))
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