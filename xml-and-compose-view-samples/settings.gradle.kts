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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
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

includeBuild("$rootDir/rnDemo/node_modules/@react-native/gradle-plugin")