package com.oddlyspaced.nothing.beacon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.oddlyspaced.nothing.beacon.databinding.ActivityTestBinding
import com.oddlyspaced.nothing.beacon.lib.animation.RingtoneAnimator
import com.oddlyspaced.nothing.beacon.lib.enum.NothingRingtone
import com.oddlyspaced.nothing.beacon.service.LedHandlerService
import com.oddlyspaced.nothing.beacon.util.SharedPreferenceManager

class TestActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }
    private val ringtoneAnimations = NothingRingtone.values()
    private val sharedPreferenceManager by lazy { SharedPreferenceManager(applicationContext) }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (!isGranted) {
            Toast.makeText(applicationContext, "Please grant that permission!", Toast.LENGTH_SHORT).show()
        }
    }

    private lateinit var ringtoneAnimator: RingtoneAnimator
    private var currentRingtone = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ringtoneAnimator = RingtoneAnimator(applicationContext, NothingRingtone.ABRA)
        binding.txCurrentAnim.text = NothingRingtone.ABRA.name

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
            try {
                LedHandlerService.stop(applicationContext)
            }
            catch (_: Exception) {}
            LedHandlerService.start(applicationContext)
        }

        binding.btnRingStart.setOnClickListener {
            ringtoneAnimator = RingtoneAnimator(applicationContext, ringtoneAnimations[currentRingtone])
            ringtoneAnimator.play()
        }

        binding.btnRingStop.setOnClickListener {
            ringtoneAnimator.stop()
        }

        binding.btnAnimNext.setOnClickListener {
            ringtoneAnimator.stop()
            currentRingtone++
            if (currentRingtone == ringtoneAnimations.size) {
                currentRingtone = 0
            }
            binding.txCurrentAnim.text = ringtoneAnimations[currentRingtone].name
            ringtoneAnimator = RingtoneAnimator(applicationContext, ringtoneAnimations[currentRingtone])
        }

        binding.btnAnimPrev.setOnClickListener {
            ringtoneAnimator.stop()
            currentRingtone--
            if (currentRingtone == -1) {
                currentRingtone = ringtoneAnimations.size - 1
            }
            binding.txCurrentAnim.text = ringtoneAnimations[currentRingtone].name
            ringtoneAnimator = RingtoneAnimator(applicationContext, ringtoneAnimations[currentRingtone])
        }

        binding.btnSave.setOnClickListener {
            sharedPreferenceManager.saveRingtone(ringtoneAnimations[currentRingtone])
        }
    }
}