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
    implementation("io.insert-koin:koin-core:$koinVer")
    implementation("io.insert-koin:koin-android:$koinVer")
    implementation("io.insert-koin:koin-androidx-compose:$koinVer")
    // Koin for Tests
    testImplementation("io.insert-koin:koin-test-junit4:3.1.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.9.0")
    implementation("com.squareup.moshi:moshi-adapters:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    // Room
    kapt("androidx.room:room-compiler:$roomVer")
    implementation("androidx.room:room-ktx:$roomVer")
    implementation("androidx.room:room-runtime:$roomVer")
    testImplementation("androidx.room:room-testing:$roomVer")

    //Hawk
    implementation(libs.hawk)

    implementation ("androidx.navigation:navigation-compose:2.8.0")
    implementation ("androidx.compose.material:material-icons-extended")

    implementation ("com.github.TuleSimon:xMaterialccp:v2.13")

    implementation ("androidx.compose.ui:ui-text-google-fonts:1.7.2")

    implementation ("androidx.datastore:datastore-preferences:1.1.1")

    implementation ("com.github.composeuisuite:ohteepee:1.0.10")


    implementation ("androidx.compose.runtime:runtime-livedata:1.7.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation ("androidx.compose.runtime:runtime:1.7.2")
    implementation ("androidx.navigation:navigation-compose:2.8.1")
}