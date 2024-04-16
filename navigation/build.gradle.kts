plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kapt)
}

android {
    namespace = "com.trodar.navigation"
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

    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.material3)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation (projects.core.theme)
    implementation (projects.features.authentication)
    implementation (projects.features.settings)
    implementation (projects.features.home)
    implementation (projects.features.order)
    implementation (projects.features.catalog)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

}