package com.oddlyspaced.nothing.beacon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oddlyspaced.nothing.beacon.databinding.ActivityMainBinding
import com.topjohnwu.superuser.Shell

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    init {
        Shell.enableVerboseLogging = BuildConfig.DEBUG
        Shell.setDefaultBuilder(
            Shell.Builder.create().apply {
                setFlags(Shell.FLAG_REDIRECT_STDERR)
                setTimeout(10)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Shell.getShell {
            startActivity(Intent(this, TestActivity::class.java))
            finish()
        }
    }
}