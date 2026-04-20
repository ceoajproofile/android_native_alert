package com.proofdev.android_native_alert

import android.content.Intent
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

            // Inadjust ang keys base sa iyong actual OneSignal logs:
            // "notification_mode" sa log mo ay "alert"
            val mode = data?.optString("notification_mode", "") ?: ""
            // "emergency" sa log mo ay "true"
            val isEmergency = data?.optBoolean("emergency", false) ?: false
            
            // Iba pang data na nakita sa log:
            val requestId = data?.optString("request_id", "") ?: ""
            val serviceName = data?.optString("service", "") ?: "" // "service" ang key sa log
            val budget = data?.optString("budget", "") ?: ""
            val distance = data?.optString("distance_km", "") ?: ""

            Log.d("OS_EXT", "Parsed Data -> mode: $mode, emergency: $isEmergency")
            Log.d("OS_EXT", "Details -> Service: $serviceName, Budget: $budget, ReqID: $requestId")

            // Check if it matches your emergency criteria
            if (mode == "alert" && isEmergency) {
                Log.d("OS_EXT", "=== MATCHED EMERGENCY ALERT! ===")

                // TRIGGER NATIVE OVERLAY
                val context = event.context
                val intent = Intent(context, AlertOverlayService::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra("service_name", serviceName)
                    putExtra("budget", budget)
                    putExtra("distance", distance)
                    putExtra("request_id", requestId)
                }
                
                // Simulan ang overlay service
                context.startService(intent)
                Log.d("OS_EXT", "=== OVERLAY SERVICE STARTED FROM EXTENSION ===")

            } else {
                Log.d("OS_EXT", "=== NOT MATCHED: check mode and emergency keys ===")
            }

        } catch (e: Exception) {
            Log.e("OS_EXT", "EXTENSION ERROR: ${e.message}", e)
        }
    }
}
