rootProject.name = "kross2d"

include(":kross2d-core")
project(":kross2d-core").name = "kross2d"

// Extras

include(":kross2d-extras-ecs")
project(":kross2d-extras-ecs").name = "kross2d-ecs"
