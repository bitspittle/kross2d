includeBuild("..") // include kross2d

listOf("helloworld", "sprite", "sounds", "manystates", "pong", "paint").forEach { exampleName ->
    val gradleTaskPrefix = "$exampleName:$exampleName-"

    listOf("common", "jvm", "js").forEach { targetName ->
        include("$gradleTaskPrefix$targetName")
    }
}
