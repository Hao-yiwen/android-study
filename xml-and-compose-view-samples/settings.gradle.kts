import groovy.lang.Closure

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
val storageUrl = System.getenv("FLUTTER_STORAGE_BASE_URL") ?: "https://storage.googleapis.com"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven {
            url = uri("https://storage.googleapis.com/download.flutter.io")
        }
        maven {
            url = uri("https://maven.pkg.github.com/Hao-yiwen/android-study")
            credentials {
                username = System.getenv("USERNAME") ?: ""
                password = System.getenv("TOKEN") ?: ""
            }
            content {
                includeGroupByRegex("com\\.example.*")
                includeGroupByRegex("yw.*")
            }
        }
        google()
        mavenCentral()
        maven {
//            url =
//                uri("/Users/haoyiwen/Documents/android/android-study/my_flutter/build/host/outputs/repo")
            url = uri("/Users/yw.hao/Documents/android/android-study/my_flutter/build/host/outputs/repo")
        }
        maven {
            url = uri("$storageUrl/download.flutter.io")
        }

    }
}

rootProject.name = "javaViewTest"
include(":app")
include(":recyclerviewtest")
include(":java_view_other")
include(":compose_views")
include(":react-native-container")
include(":third_sdk")
include(":core")
include(":flutter")
include(":hybird")

//includeBuild("./rnDemo/node_modules/@react-native/gradle-plugin")

// rn源码编译
//includeBuild("./rnDemo/node_modules/react-native") {
//    dependencySubstitution {
//        substitute(module("com.facebook.react:react-android")).using(project(":packages:react-native:ReactAndroid"))
//        substitute(module("com.facebook.react:react-native")).using(project(":packages:react-native:ReactAndroid"))
//        substitute(module("com.facebook.react:hermes-android")).using(project(":packages:react-native:ReactAndroid:hermes-engine"))
//        substitute(module("com.facebook.react:hermes-engine")).using(project(":packages:react-native:ReactAndroid:hermes-engine"))
//    }
//}

//include(":react-native-webview")
//project(":react-native-webview").projectDir = file("./rnDemo/node_modules/react-native-webview/android")
//include(":react-native-screens")
//project(":react-native-screens").projectDir = file("./rnDemo/node_modules/react-native-screens/android")
//include(":react-native-safe-area-context")
//project(":react-native-safe-area-context").projectDir = file("./rnDemo/node_modules/react-native-safe-area-context/android")