import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'android_native_alert_platform_interface.dart';

class MethodChannelAndroidNativeAlert extends AndroidNativeAlertPlatform {
  @visibleForTesting
  final methodChannel = const MethodChannel('android_native_alert');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>(
      'getPlatformVersion',
    );
    return version;
  }

  @override
  Future<bool> canDrawOverlays() async {
    final result = await methodChannel.invokeMethod<bool>('canDrawOverlays');
    return result ?? false;
  }

  @override
  Future<void> openOverlayPermissionSettings() async {
    await methodChannel.invokeMethod('openOverlayPermissionSettings');
  }

  @override
  Future<void> showNativeAlert({
    required String title,
    required String body,
  }) async {
    await methodChannel.invokeMethod('showNativeAlert', {
      'title': title,
      'body': body,
    });
  }

  @override
  Future<void> closeNativeAlert() async {
    await methodChannel.invokeMethod('closeNativeAlert');
  }
}