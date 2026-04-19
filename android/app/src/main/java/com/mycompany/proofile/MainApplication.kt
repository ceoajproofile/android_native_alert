package com.mycompany.proofile

import android.app.Application
import android.util.Log
import androidx.annotation.Keep
import com.onesignal.OneSignal
import com.onesignal.notifications.INotificationServiceExtension

@Keep
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("OS_EXT", "MainApplication started")

        // Force register extension manually
        try {
            val extension = NotificationServiceExtension()
            Log.d("OS_EXT", "Extension manually created: $extension")
        } catch (e: Exception) {
            Log.e("OS_EXT", "Failed to init extension: ${e.message}")
        }
    }
}
