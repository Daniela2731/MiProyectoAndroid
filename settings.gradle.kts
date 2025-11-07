pluginManagement {
    repositories {
        google() // Repositorio de Google (Plugins Android y Maps)
        mavenCentral() // Repositorio Maven central
        gradlePluginPortal() // Plugins externos de Gradle
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Repositorio principal de Android
        mavenCentral() // Librerías de Kotlin, Compose, etc.
    }
}

rootProject.name = "Lab12_Maps"
include(":app")
