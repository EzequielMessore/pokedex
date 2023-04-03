plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.messore.tech.pokedex.pokelist"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.coil)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    debugImplementation(libs.bundles.debug.compose)

    testImplementation(libs.testing.kotlin.test)
    androidTestImplementation(libs.testing.junit.ext)
    androidTestImplementation(libs.testing.espresso)
    androidTestImplementation(libs.testing.compose)
}
