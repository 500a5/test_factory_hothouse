plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "soft.divan.test_factory_hothouse"
    compileSdk = 34

    defaultConfig {
        applicationId = "soft.divan.test_factory_hothouse"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
            buildConfigField("String", "HOST", "\"https://plannerok.ru/api/v1/users/\"")
        }

        debug {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
            isTestCoverageEnabled = true
            buildConfigField("String", "HOST", "\"https://plannerok.ru/api/v1/users/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val roomVer = "2.6.0"
val koinVer = "4.0.0"
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    // Koin for Tests
    testImplementation(libs.koin.test.junit4)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.kotlin)
    implementation (libs.converter.gson)

    // Room
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    testImplementation(libs.androidx.room.testing)

    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.material.icons.extended)

    implementation (libs.xmaterialccp)

    implementation (libs.androidx.ui.text.google.fonts)

    implementation (libs.androidx.datastore.preferences)

    implementation (libs.ohteepee)


    implementation (libs.androidx.runtime.livedata)
    implementation (libs.androidx.lifecycle.runtime.ktx.v286)
    implementation (libs.androidx.runtime)
    implementation (libs.androidx.navigation.compose.v281)
}