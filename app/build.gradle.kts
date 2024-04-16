plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kapt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.trodar.fakeshop"

    defaultConfig {
        applicationId = "com.trodar.fakeshop"
        minSdk = libs.versions.minSdk.get().toInt()
        compileSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "0.10"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(platform(libs.firebase.bom))

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)



    implementation(libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation(projects.core.theme)
    implementation(projects.navigation)
    implementation (libs.firebase.auth)
}