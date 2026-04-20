group = "com.proofdev.android_native_alert"
version = "1.0-SNAPSHOT"

buildscript {
    val kotlinVersion = "2.2.20"
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.11.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.proofdev.android_native_alert"
    compileSdk = 35 // Recommended stable version

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
        }
    }

    defaultConfig {
        minSdk = 24
    }

    // PARA HINDI MABURA ANG CLASS SA BUILD
    buildTypes {
        release {
            consumerProguardFiles("proguard-rules.pro")
        }
        debug {
            consumerProguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    // IMPORTANTE: Para ma-compile ang OneSignal classes sa loob ng plugin
    compileOnly("com.onesignal:OneSignal:[5.0.0, 5.99.99]")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.mockito:mockito-core:5.0.0")
}
