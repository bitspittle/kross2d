plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "dev.bitspittle.kross2d"
version = libs.versions.kross2d.core.get()

kotlin {
    jvm()
    js { browser() }
    sourceSets {
        commonTest.dependencies {
            implementation(libs.truthish)
            implementation(kotlin("test"))
        }

        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.bundles.jvm.audio)
        }
    }
}
