// Plugins declared here instead of settings.gradle.kts because otherwise I get an error saying the kotlin plugin was
// applied multiple times.
plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
}

subprojects {
    repositories {
        mavenCentral()
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
            compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    afterEvaluate {
        tasks.findByName("jsBrowserProductionRun")?.let { jsRun ->
            tasks.register("runJs") {
                group = "application"
                description = "Alias for jsBrowserProductionRun / companion to runJvm"
                dependsOn(jsRun)
            }
        }
    }
}

