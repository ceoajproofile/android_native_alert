package com.mycompany.proofile

import android.content.Intent
import android.util.Log
import androidx.annotation.Keep
import com.onesignal.notifications.IDisplayableMutableNotification
import com.onesignal.notifications.INotificationReceivedEvent
import com.onesignal.notifications.INotificationServiceExtension

@Keep
class NotificationServiceExtension : INotificationServiceExtension {

    override fun onNotificationReceived(event: INotificationReceivedEvent) {
        val notification: IDisplayableMutableNotification = event.notification
        val data = notification.additionalData

        val type = data?.optString("type", "") ?: ""
        val urgent = data?.optBoolean("urgent", false) ?: false
        val requestId = data?.optString("request_id", "") ?: ""
        val serviceName = data?.optString("service_name", "") ?: ""
        val budget = data?.optString("budget", "") ?: ""
        val distance = data?.optString("distance", "") ?: ""

        Log.d("OS_EXT", "type=$type urgent=$urgent requestId=$requestId")

        if (type == "service_alert" && urgent) {
            // optional: customize notification or start your native flow
        }
    }
}
