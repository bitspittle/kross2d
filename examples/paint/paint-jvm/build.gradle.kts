plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "dev.bitspittle.kross2d.examples.paint"
version = "0.0.1"

dependencies {
    implementation(project(":paint:paint-common"))
}

application {
    mainClass.set("PaintRunnerKt")
}
