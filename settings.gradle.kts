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
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "FakeShop"
include(":app")
include(":navigation")
include(":features:authentication")
include(":core:theme")
include(":core:utils")
include(":core:common")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":features:settings")
include(":features:home")
include(":features:order")
include(":features:catalog")
