plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "dev.bitspittle.kross2d.examples.sounds"
version = "0.0.1"

kotlin {
    js {
        browser {
            binaries.executable()
        }
    }
    sourceSets {
        jsMain.dependencies {
            implementation(project(":sounds:sounds-common"))
        }
    }
}
