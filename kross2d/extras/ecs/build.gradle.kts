plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "dev.bitspittle.kross2d"
version = libs.versions.kross2d.ecs.get()

kotlin {
    jvm()
    js { browser() }
    sourceSets {
        commonMain.dependencies {
            implementation(project(":kross2d:kross2d"))
        }
        commonTest.dependencies {
            implementation(libs.truthish)
            implementation(kotlin("test"))
        }
    }
}
