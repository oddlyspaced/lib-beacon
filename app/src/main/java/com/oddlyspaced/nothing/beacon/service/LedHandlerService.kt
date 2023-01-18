package com.oddlyspaced.nothing.beacon.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.oddlyspaced.nothing.beacon.lib.animation.ResourceAnimator
import com.oddlyspaced.nothing.beacon.util.Logger
import com.oddlyspaced.nothing.beacon.util.SharedPreferenceManager

// foreground service that will manage state for LED ringtones and calls
class LedHandlerService: Service() {

    companion object {
        private const val CHANNEL_ID = "LED_CHANNEL"
        private const val ACTION_PREFIX = "beacon.call"
        const val ACTION_CALL_STARTED = "$ACTION_PREFIX.started"
        const val ACTION_CALL_MISSED = "$ACTION_PREFIX.missed"

        fun start(context: Context) {
            ContextCompat.startForegroundService(context, Intent(context, LedHandlerService::class.java))
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, LedHandlerService::class.java))
        }
    }

    private lateinit var anim: ResourceAnimator

    private val customReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.action?.let { action ->
                when (action) {
                    ACTION_CALL_STARTED -> anim.play()
                    ACTION_CALL_MISSED -> anim.stop()
                }
            }
        }
    }

    override fun onCreate() {
        val filter = IntentFilter().apply {
            addAction(ACTION_CALL_STARTED)
            addAction(ACTION_CALL_MISSED)
        }
        Logger.d("Registering LED Receiver")
        registerReceiver(customReceiver, filter)
        anim = ResourceAnimator(this, SharedPreferenceManager(applicationContext).getRingtone())
    }

    override fun onDestroy() {
        unregisterReceiver(customReceiver)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .apply {
                setContentTitle("LED Service")
                setContentText("Hello")
                setSmallIcon(android.R.drawable.btn_dialog)
            }.build()
        startForeground(1, notification)
        Logger.d("Service Started!")
        return START_NOT_STICKY
    }


    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(CHANNEL_ID, "LED Service Channel", NotificationManager.IMPORTANCE_MIN)
        val manager: NotificationManager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    override fun onBind(intent: Intent?): IBinder? {
        // todo: explore use case later
        return null
    }
}