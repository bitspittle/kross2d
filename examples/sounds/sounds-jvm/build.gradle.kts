plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "dev.bitspittle.kross2d.examples.sounds"
version = "0.0.1"

dependencies {
    implementation(project(":sounds:sounds-common"))
}

application {
    mainClass.set("SoundsRunnerKt")
}
