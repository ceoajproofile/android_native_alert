import 'android_native_alert_platform_interface.dart';

class AndroidNativeAlert {
  Future<String?> getPlatformVersion() {
    return AndroidNativeAlertPlatform.instance.getPlatformVersion();
  }

  Future<bool> canDrawOverlays() {
    return AndroidNativeAlertPlatform.instance.canDrawOverlays();
  }

  Future<void> openOverlayPermissionSettings() {
    return AndroidNativeAlertPlatform.instance.openOverlayPermissionSettings();
  }

  Future<void> showNativeAlert({
    required String title,
    required String body,
  }) {
    return AndroidNativeAlertPlatform.instance.showNativeAlert(
      title: title,
      body: body,
    );
  }

  Future<void> closeNativeAlert() {
    return AndroidNativeAlertPlatform.instance.closeNativeAlert();
  }
}