plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kapt)
}

android {
    namespace = "com.trodar.data"
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

    implementation(platform(libs.squareup.okhttp3.bom))
    implementation(libs.squareup.okhttp3)
    implementation(libs.squareup.okhttp3.logging)
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.converter)
    implementation(libs.google.code.gson)

    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)
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
    implementation(projects.core.theme)
    implementation(projects.core.utils)
}
//implementation 'com.google.code.gson:gson:2.10.1'

//implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
//
//// define any required OkHttp artifacts without version
//implementation("com.squareup.okhttp3:okhttp")
//implementation("com.squareup.okhttp3:logging-interceptor")