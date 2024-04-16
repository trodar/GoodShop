plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}