package com.oddlyspaced.nothing.beacon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.BuildConfig
import com.topjohnwu.superuser.Shell

class MainActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)

        Shell.getShell {
            startActivity(Intent(this, RootActivity::class.java))
        }
    }
}