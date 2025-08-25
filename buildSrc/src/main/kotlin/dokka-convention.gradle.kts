plugins {
    id("org.jetbrains.dokka")
}

dokka {
    dokkaSourceSets.configureEach {
        sourceLink {
            val path = project.projectDir.relativeTo(project.rootProject.projectDir).invariantSeparatorsPath
            localDirectory = project.projectDir.resolve("src")
            remoteUrl("https://github.com/bitspittle/kross2d/tree/main/$path/src")
            remoteLineSuffix = "#L"
        }
    }
}