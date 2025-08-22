plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "dev.bitspittle.kross2d.examples.helloworld"
version = "0.0.1"

dependencies {
    implementation(project(":helloworld:helloworld-common"))
}

application {
    mainClass.set("HelloWorldRunnerKt")
}
