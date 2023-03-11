plugins {
    id("com.android.application") version "7.4.2" apply false
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    alias(libs.plugins.detekt)
}

// region detekt
dependencies {
    detektPlugins(libs.detekt.formatting)
}

detekt {
    toolVersion = libs.versions.detekt.get()
    val defaultSources = getSrcDirectors()
    val files = properties.getOrDefault("source", defaultSources)?.let {
        if (it is String) it.split(",") else it
    }

    source = project.files(files)
    config = files("$projectDir/detekt/detekt.yml")
    reportsDir = file("$projectDir/build/reports/detekt/")
}

/**
 * function responsible for retrieving all `src` folders of the project
 */
fun getSrcDirectors() = projectDir.walk().filter { file ->
    file.isDirectory && file.absolutePath.endsWith("src")
}.toList()

tasks {
    val installGitHooks by registering(Copy::class) {
        from(File(rootProject.rootDir, "scripts/pre-commit"))
        into(File(rootProject.rootDir, ".git/hooks"))
        fileMode = 0b000_111_101_101 // 0755 in binary, it doesn't seem to work if I put 755 or 0755
    }

    // Install hooks automatically before building a new compilation
    // Idea from: https://gist.github.com/KenVanHoeylandt/c7a928426bce83ffab400ab1fd99054a
    getByPath(":app:preBuild").dependsOn(installGitHooks)
}
// endregion
