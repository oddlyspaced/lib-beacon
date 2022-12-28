package com.oddlyspaced.nothing.beacon.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Message
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.oddlyspaced.nothing.beacon.util.Logger

// foreground service that will manage state for LED ringtones and calls
class LedHandlerService: Service() {

    companion object {
        private const val CHANNEL_ID = "12345"

        fun start(context: Context) {
            ContextCompat.startForegroundService(context, Intent(context, LedHandlerService::class.java))
        }

        fun stop(context: Context) {
            context.stopService(Intent(context, LedHandlerService::class.java))
        }
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
        TODO("Not yet implemented")
    }
}