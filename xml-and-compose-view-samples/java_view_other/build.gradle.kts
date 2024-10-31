plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.yiwen.java_view_other"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdkVersion"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    aaptOptions {
        noCompress("tflite", "lite")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }


    dataBinding{
        enable = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.7.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata:2.7.0")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation(project(":core"))
    implementation("com.github.smuyyh:JsonViewer:1.0.7")
    implementation("com.orhanobut:logger:2.2.0")
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("org.tensorflow:tensorflow-lite:2.5.0")
// lottie
    implementation(libs.lottie.android)
    // stateview nukc
    implementation("com.github.nukc:StateView:v3.0.2")

    implementation("org.greenrobot:eventbus:3.3.1")

    implementation(project(":react-native-container"))
}