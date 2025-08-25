plugins {
    `dokka-convention`
}

dependencies {
    dokka(project(":kross2d:core"))
    dokka(project(":kross2d:extras:ecs"))
}

dokka {
    moduleName.set("Kross2D docs")
}
