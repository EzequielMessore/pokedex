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
    implementation(project(":data:data"))
    implementation(project(":domain"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.bundles.network)

    testImplementation(libs.bundles.testing)
}
