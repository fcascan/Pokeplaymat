package com.fcascan.pokeplaymat.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class VibrationHelper {
    fun triggerVibration(context: Context, milliseconds: Long = 25, amplitude: Int = VibrationEffect.DEFAULT_AMPLITUDE) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(milliseconds, amplitude)
            )
        }
    }

    fun triggerVibrationWaveform(context: Context, milliseconds: Long = 25) {
        val vibrationPattern = longArrayOf(0, milliseconds, milliseconds*2, milliseconds) // 0ms delay, 50ms vibrate, 100ms delay, 50ms vibrate
        val vibrationEffect = VibrationEffect.createWaveform(vibrationPattern, -1)
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(vibrationEffect)
        }
    }
}