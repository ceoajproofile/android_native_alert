package com.proofdev.android_native_alert

import android.util.Log
import androidx.annotation.Keep
import com.onesignal.notifications.IDisplayableMutableNotification
import com.onesignal.notifications.INotificationReceivedEvent
import com.onesignal.notifications.INotificationServiceExtension

@Keep
class NotificationServiceExtension : INotificationServiceExtension {

    override fun onNotificationReceived(event: INotificationReceivedEvent) {
        Log.d("OS_EXT", "=== EXTENSION HIT ===")

        try {
            val notification: IDisplayableMutableNotification = event.notification
            val data = notification.additionalData

            Log.d("OS_EXT", "raw additionalData = $data")

            val type = data?.optString("type", "") ?: ""
            val urgent = data?.optBoolean("urgent", false) ?: false
            val requestId = data?.optString("request_id", "") ?: ""
            val serviceName = data?.optString("service_name", "") ?: ""
            val budget = data?.optString("budget", "") ?: ""
            val distance = data?.optString("distance", "") ?: ""

            Log.d("OS_EXT", "type=$type")
            Log.d("OS_EXT", "urgent=$urgent")
            Log.d("OS_EXT", "requestId=$requestId")
            Log.d("OS_EXT", "serviceName=$serviceName")
            Log.d("OS_EXT", "budget=$budget")
            Log.d("OS_EXT", "distance=$distance")

            if (type == "service_alert" && urgent) {
                Log.d("OS_EXT", "=== MATCHED SERVICE ALERT ===")

                // OPTIONAL: trigger overlay directly from native
                // You can connect this later to AlertOverlayService if needed

            } else {
                Log.d("OS_EXT", "=== NOT MATCHED ===")
            }

        } catch (e: Exception) {
            Log.e("OS_EXT", "EXTENSION ERROR: ${e.message}", e)
        }
    }
}
