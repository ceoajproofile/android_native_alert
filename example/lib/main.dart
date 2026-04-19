import 'package:flutter/material.dart';
import 'package:android_native_alert/android_native_alert.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Native Overlay Test')),
        body: Center(
          child: ElevatedButton(
            onPressed: () async {
              final plugin = AndroidNativeAlert();

              final hasPermission = await plugin.canDrawOverlays();

              if (!hasPermission) {
                await plugin.openOverlayPermissionSettings();
              } else {
                await plugin.showNativeAlert(
                  title: 'Practice Alert',
                  body: 'Lumabas ako galing overlay service 🔥',
                );
              }
            },
            child: const Text('Show Overlay Alert'),
          ),
        ),
      ),
    );
  }
}