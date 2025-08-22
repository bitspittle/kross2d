plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "dev.bitspittle.kross2d.examples.manystates"
version = "0.0.1"

dependencies {
    implementation(project(":manystates:manystates-common"))
}

application {
    mainClass.set("ManyStatesRunnerKt")
}
