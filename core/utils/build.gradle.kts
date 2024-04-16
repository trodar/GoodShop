plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.jetbrains.kapt)
}

android {
    namespace = "com.trodar.utils"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = libs.versions.androidjvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompiler.get()
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    implementation(projects.core.model)
    implementation(projects.core.theme)

}