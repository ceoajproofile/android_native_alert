import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'android_native_alert_method_channel.dart';

abstract class AndroidNativeAlertPlatform extends PlatformInterface {
  AndroidNativeAlertPlatform() : super(token: _token);

  static final Object _token = Object();

  static AndroidNativeAlertPlatform _instance = MethodChannelAndroidNativeAlert();

  static AndroidNativeAlertPlatform get instance => _instance;

  static set instance(AndroidNativeAlertPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('getPlatformVersion() has not been implemented.');
  }

  Future<bool> canDrawOverlays() {
    throw UnimplementedError('canDrawOverlays() has not been implemented.');
  }

  Future<void> openOverlayPermissionSettings() {
    throw UnimplementedError(
      'openOverlayPermissionSettings() has not been implemented.',
    );
  }

  Future<void> showNativeAlert({
    required String title,
    required String body,
  }) {
    throw UnimplementedError('showNativeAlert() has not been implemented.');
  }

  Future<void> closeNativeAlert() {
    throw UnimplementedError('closeNativeAlert() has not been implemented.');
  }
}