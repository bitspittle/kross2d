plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "dev.bitspittle.kross2d.examples.pong"
version = "0.0.1"

kotlin {
    jvm()
    js { browser() }
    sourceSets {
        commonMain.dependencies {
            api("dev.bitspittle.kross2d:kross2d")
            api("dev.bitspittle.kross2d:kross2d-ecs")
        }
    }
}
