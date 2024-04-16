plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kapt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.trodar.settings"
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

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)
    implementation(platform(libs.firebase.bom))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material3)

    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation (projects.core.theme)
    implementation (projects.core.utils)
    implementation (projects.core.common)
    implementation (projects.core.domain)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

}