package com.proofdev.android_native_alert

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class AndroidNativeAlertPlugin : FlutterPlugin, MethodChannel.MethodCallHandler {

    private lateinit var channel: MethodChannel
    private lateinit var binding: FlutterPlugin.FlutterPluginBinding

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        binding = flutterPluginBinding
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "android_native_alert")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${Build.VERSION.RELEASE}")
            }

            "canDrawOverlays" -> {
                result.success(Settings.canDrawOverlays(binding.applicationContext))
            }

            "openOverlayPermissionSettings" -> {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                    data = Uri.parse("package:${binding.applicationContext.packageName}")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                binding.applicationContext.startActivity(intent)
                result.success(null)
            }

            "showNativeAlert" -> {
                if (!Settings.canDrawOverlays(binding.applicationContext)) {
                    result.error("PERMISSION_DENIED", "Overlay permission not granted", null)
                    return
                }

                val title = call.argument<String>("title") ?: "Incoming Alert"
                val body = call.argument<String>("body") ?: "Hello from overlay service"

                val intent = Intent(binding.applicationContext, AlertOverlayService::class.java).apply {
                    putExtra("alert_title", title)
                    putExtra("alert_body", body)
                }

                binding.applicationContext.startService(intent)
                result.success(null)
            }

            "closeNativeAlert" -> {
                val intent = Intent(binding.applicationContext, AlertOverlayService::class.java)
                binding.applicationContext.stopService(intent)
                result.success(null)
            }

            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}