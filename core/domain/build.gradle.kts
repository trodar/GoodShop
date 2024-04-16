plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kapt)
}

android {
    namespace = "com.trodar.domain"
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
}

dependencies {
    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material3)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(projects.core.common)
    implementation(projects.core.data)
}