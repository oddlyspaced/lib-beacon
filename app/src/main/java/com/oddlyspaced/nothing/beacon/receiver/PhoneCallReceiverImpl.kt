package com.oddlyspaced.nothing.beacon.receiver

import android.content.Context
import android.content.Intent
import com.oddlyspaced.nothing.beacon.service.LedHandlerService
import com.oddlyspaced.nothing.beacon.util.Logger
import java.util.*

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
        sendBroadcast(ctx!!, LedHandlerService.ACTION_CALL_STARTED)
        super.onIncomingCallStarted(ctx, number, start)
    }

    override fun onMissedCall(ctx: Context?, number: String?, start: Date?) {
        Logger.d("onMissedCall")
        sendBroadcast(ctx!!, LedHandlerService.ACTION_CALL_MISSED)
        super.onMissedCall(ctx, number, start)
    }

    private fun sendBroadcast(context: Context, event: String) {
        context.sendBroadcast(Intent(event))
    }

}