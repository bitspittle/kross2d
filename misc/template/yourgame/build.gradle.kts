import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "your.group.here"
version = "0.0.1-SNAPSHOT"

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    jvm {
        binaries {
            executable {
                mainClass.set("MainKt")
                applicationDefaultJvmArgs.addAll(
                    "-XX:+IgnoreUnrecognizedVMOptions",
                    "--enable-native-access=ALL-UNNAMED",
                )
            }
        }
    }
    js { browser { binaries.executable() } }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kross2d.core)
        }
    }
}
