// include kross2d
includeBuild("..") {
    dependencySubstitution {
        substitute(module("dev.bitspittle.kross2d:kross2d"))
            .using(project(":kross2d:core"))

        substitute(module("dev.bitspittle.kross2d:kross2d-ecs"))
            .using(project(":kross2d:extras:ecs"))
    }
}

include("helloworld")
include("sprite")
include("sounds")
include("manystates")
include("pong")
include("paint")

