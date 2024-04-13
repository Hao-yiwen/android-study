plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.javaviewtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.javaviewtest"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


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

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}