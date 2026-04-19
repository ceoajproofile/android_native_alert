import 'package:flutter_test/flutter_test.dart';
import 'package:android_native_alert/android_native_alert.dart';
import 'package:android_native_alert/android_native_alert_platform_interface.dart';
import 'package:android_native_alert/android_native_alert_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAndroidNativeAlertPlatform
    with MockPlatformInterfaceMixin
    implements AndroidNativeAlertPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final AndroidNativeAlertPlatform initialPlatform = AndroidNativeAlertPlatform.instance;

  test('$MethodChannelAndroidNativeAlert is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAndroidNativeAlert>());
  });

  test('getPlatformVersion', () async {
    AndroidNativeAlert androidNativeAlertPlugin = AndroidNativeAlert();
    MockAndroidNativeAlertPlatform fakePlatform = MockAndroidNativeAlertPlatform();
    AndroidNativeAlertPlatform.instance = fakePlatform;

    expect(await androidNativeAlertPlugin.getPlatformVersion(), '42');
  });
}
