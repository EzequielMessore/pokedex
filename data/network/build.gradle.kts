import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}
