// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.library") version "8.3.1" apply false
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
extra["buildToolsVersion"] = "30.0.3"
extra["compileSdkVersion"] = 34
extra["minSdkVersion"] = 28
extra["targetSdkVersion"] = 34
extra["versionCode"] = 1
extra["versionName"] = "1.0"

