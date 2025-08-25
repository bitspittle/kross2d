plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}
