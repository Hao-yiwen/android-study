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


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/Hao-yiwen/android-study")
            credentials {
                username = System.getenv("USERNAME") ?: ""
                password = System.getenv("TOKEN") ?: ""
            }
            content {
                includeGroupByRegex("com\\.example.*")
            }
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "javaViewTest"
include(":app")
include(":recyclerviewtest")
include(":java_view_other")
include(":compose_views")
include(":react-native-container")

includeBuild("./rnDemo/node_modules/@react-native/gradle-plugin")
include(":react-native-webview")
project(":react-native-webview").projectDir = file("./rnDemo/node_modules/react-native-webview/android")
