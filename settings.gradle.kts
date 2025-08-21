pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin-multiplatform") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
            else if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "kross2d"

include(":kross2d-core")
project(":kross2d-core").name = "kross2d"

// Extras

include(":kross2d-extras-ecs")
project(":kross2d-extras-ecs").name = "kross2d-ecs"

// Examples

//val examplesRoot = ":docs:examples"
//
//val exHelloWorld = "$examplesRoot:helloworld:helloworld"
//include("$exHelloWorld-common")
//include("$exHelloWorld-jvm")
//include("$exHelloWorld-js")
//
//val exSprite = "$examplesRoot:sprite:sprite"
//include("$exSprite-common")
//include("$exSprite-jvm")
//include("$exSprite-js")
//
//val exSounds = "$examplesRoot:sounds:sounds"
//include("$exSounds-common")
//include("$exSounds-jvm")
//include("$exSounds-js")
//
//val exManyStates = "$examplesRoot:manystates:manystates"
//include("$exManyStates-common")
//include("$exManyStates-jvm")
//include("$exManyStates-js")
//
//val exPong = "$examplesRoot:pong:pong"
//include("$exPong-common")
//include("$exPong-jvm")
//include("$exPong-js")
//
//val exPaint = "$examplesRoot:paint:paint"
//include("$exPaint-common")
//include("$exPaint-jvm")
//include("$exPaint-js")
