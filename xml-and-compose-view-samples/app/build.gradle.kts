import org.jetbrains.kotlin.serialization.builtins.main
import org.jetbrains.kotlin.util.profile

plugins {
    alias(libs.plugins.androidApplication)
//    id("com.facebook.react")
}

//react {
//    entryFile = file("../rnDemo/index.js")
//    root = file("../rnDemo")
//    reactNativeDir = file("../rnDemo/node_modules/react-native")
//    cliFile = file("../rnDemo/node_modules/react-native/cli.js")
//}

android {
    namespace = "com.example.javaviewtest"
    compileSdk = 34

    signingConfigs {
        create("release") {
            storeFile = file(project.property("MYAPP_RELEASE_STORE_FILE") as String)
            storePassword = project.property("MYAPP_RELEASE_STORE_PASSWORD") as String
            keyAlias = project.property("MYAPP_RELEASE_KEY_ALIAS") as String
            keyPassword = project.property("MYAPP_RELEASE_KEY_PASSWORD") as String
        }
    }

    defaultConfig {
        applicationId = "com.example.javaviewtest"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        externalNativeBuild {
            cmake {
                cppFlags("-std=c++11")
            }
        }

        ndk {
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86_64")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    externalNativeBuild {
        cmake {
            path = file("./CMakeLists.txt")
        }
    }

    buildTypes {
//        debug {
//            isMinifyEnabled = false
//        }


        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            getByName("release") {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

//repositories{
//    flatDir(){
//        dir("libs")
//    }
//}

dependencies {
    implementation("com.example:chapter03:0.0.1")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // 引用com.yiwen.recyclerviewtest中的库
    implementation(project(":recyclerviewtest"))
    // 添加composeviwes
    implementation(project(":compose_views"))
    // 添加react-native-container
    implementation(project(":react-native-container"))
    // 添加java_view_other
    implementation(project(":java_view_other"))
    // 添加third_sdk
    implementation(project(":third_sdk"))

    implementation(project(":hybird"))
    implementation(project(":flutter"))
    implementation(project(":core"))

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // react Native
    implementation("com.facebook.react:react-android:0.74.1")
    implementation("com.facebook.react:hermes-android:0.74.1")


    debugImplementation("com.example.my_flutter:flutter_debug:1.0")
//    profileImplementation("com.example.my_flutter:flutter_profile:1.0")
    releaseImplementation("com.example.my_flutter:flutter_release:1.0")

    // lottie
    implementation(libs.lottie.android)
    implementation("org.greenrobot:eventbus:3.3.1")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    implementation("androidx.lifecycle:lifecycle-livedata:2.7.0")

    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")


//    implementation(name: 'react-native-webview-debug', ext: 'aar')

    // react native webview
//    implementation(project(":react-native-webview"))

//    implementation(project(":react-native-screens")) {
//        exclude(group = "com.facebook.react")
//    }

//    implementation(project(":react-native-safe-area-context")){
//        exclude(group = "com.facebook.react")
//    }

}