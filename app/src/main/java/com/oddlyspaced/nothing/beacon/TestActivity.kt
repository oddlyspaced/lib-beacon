package com.oddlyspaced.nothing.beacon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.oddlyspaced.nothing.beacon.databinding.ActivityTestBinding
import com.oddlyspaced.nothing.beacon.service.LedHandlerService

class TestActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean -> }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnPermCallLog.setOnClickListener {
            requestPermissionLauncher.launch(android.Manifest.permission.READ_CALL_LOG)
        }

        binding.btnPermNotification.setOnClickListener {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        binding.btnPermPhone.setOnClickListener {
            requestPermissionLauncher.launch(android.Manifest.permission.READ_PHONE_STATE)
        }

        binding.btnStartService.setOnClickListener {
            LedHandlerService.start(applicationContext)
        }
    }
}