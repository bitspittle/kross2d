plugins {
    id("kmp-convention")
    id("dokka-convention")
}

group = "dev.bitspittle.kross2d"
version = libs.versions.kross2d.ecs.get()

kotlin {
    jvm()
    js { browser() }
    sourceSets {
        commonMain.dependencies {
            implementation(project(":kross2d:core"))
        }
        commonTest.dependencies {
            implementation(libs.truthish)
            implementation(kotlin("test"))
        }
    }
}

dokka {
    moduleName.set("Kross2D: ECS")
}
