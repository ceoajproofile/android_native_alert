package com.proofdev.android_native_alert

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class IncomingAlertActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("alert_title") ?: "Incoming Alert"
        val body = intent.getStringExtra("alert_body") ?: "Hello from plugin"

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(60, 60, 60, 60)
        }

        val titleView = TextView(this).apply {
            text = title
            textSize = 22f
        }

        val bodyView = TextView(this).apply {
            text = body
            textSize = 16f
            setPadding(0, 20, 0, 40)
        }

        val closeBtn = Button(this).apply {
            text = "Close"
            setOnClickListener { finish() }
        }

        root.addView(titleView)
        root.addView(bodyView)
        root.addView(closeBtn)

        setContentView(root)

        window.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        window.setGravity(Gravity.CENTER)

        vibratePhone()
    }

    private fun vibratePhone() {
        val pattern = longArrayOf(0, 300, 150, 300)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator.vibrate(
                VibrationEffect.createWaveform(pattern, -1)
            )
        } else {
            @Suppress("DEPRECATION")
            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(pattern, -1)
            }
        }
    }
}