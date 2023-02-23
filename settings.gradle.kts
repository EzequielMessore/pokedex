pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("deps") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "pokédex"
include(
    ":app"
)
