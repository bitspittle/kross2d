import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "dev.bitspittle.kross2d.examples.paint"
version = "0.0.1"

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    jvm { binaries { executable { mainClass.set("MainKt") } } }
    js { browser { binaries.executable() } }
    sourceSets {
        commonMain.dependencies {
            api("dev.bitspittle.kross2d:kross2d")
        }
    }
}
