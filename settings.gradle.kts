rootProject.name = "kross2d"

mapOf(
    ":kross2d:core" to "kross2d",
    ":kross2d:extras:ecs" to "kross2d-ecs"
).forEach { (path, artifactId) ->
    include(path)
    project(path).name = artifactId
}
