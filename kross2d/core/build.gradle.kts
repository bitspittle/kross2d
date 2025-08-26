plugins {
    id("kmp-convention")
    id("dokka-convention")
}

group = "dev.bitspittle.kross2d"
version = libs.versions.kross2d.core.get()

kotlin {
    jvm()
    js { browser() }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(libs.truthish)
            implementation(kotlin("test"))
        }

        jvmMain.dependencies {
            implementation(libs.bundles.audio.jvm)
        }
    }
}

dokka {
    moduleName.set("Kross2D")
}
