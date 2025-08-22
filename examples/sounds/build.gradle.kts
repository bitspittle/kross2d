import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "dev.bitspittle.kross2d.examples.helloworld"
version = "0.0.1"

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    jvm {
        binaries {
            executable {
                mainClass.set("MainKt")
                applicationDefaultJvmArgs.addAll(
                    // JDK24 started reporting warnings for libraries that use restricted native methods, which Kross2d
                    // definitely does with its sound library:
                    //
                    // The best solution we have for now is to disable the warning by explicitly enabling access.
                    // We also suggest the IgnoreUnrecognizedVMOptions flag here to allow applications to be able to
                    // compile with JDKs older than JDK24. You can remove it if you are intentionally using JDK24+.
                    "-XX:+IgnoreUnrecognizedVMOptions",
                    "--enable-native-access=ALL-UNNAMED",
                )
            }
        }
    }
    js { browser { binaries.executable() } }
    sourceSets {
        commonMain.dependencies {
            api("dev.bitspittle.kross2d:kross2d")
        }
    }
}
