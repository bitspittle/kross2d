plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "dev.bitspittle.kross2d.examples.sprite"
version = "0.0.1"

dependencies {
    implementation(project(":sprite:sprite-common"))
}

application {
    mainClass.set("SpriteRunnerKt")
}
