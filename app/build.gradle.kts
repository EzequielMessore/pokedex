plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    alias(libs.plugins.hilt)
}

android {
    namespace = "br.com.messore.tech.pokedex"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.messore.tech.pokedex"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data:data"))
    implementation(project(":data:network"))
    implementation(project(":features:pokelist"))

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    debugImplementation(libs.bundles.debug.compose)

    testImplementation(libs.testing.kotlin.test)
    androidTestImplementation(libs.testing.junit.ext)
    androidTestImplementation(libs.testing.espresso)
    androidTestImplementation(libs.testing.compose)
}
