// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.library") version "8.7.0" apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.googleAndroidLibrariesMapsplatformSecretsGradlePlugin) apply false
}

buildscript {
    dependencies {
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}

// 使用 extra 定义扩展属性
extra["buildToolsVersion"] = "34.0.0"
extra["compileSdkVersion"] = 35
extra["minSdkVersion"] = 28
extra["targetSdkVersion"] = 35
extra["versionCode"] = 2
extra["versionName"] = "1.0"

