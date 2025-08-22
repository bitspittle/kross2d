plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "dev.bitspittle.kross2d.examples.pong"
version = "0.0.1"

dependencies {
    implementation(project(":pong:pong-common"))
}

application {
    mainClass.set("PongRunnerKt")
}
