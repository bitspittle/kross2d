package bitspittle.kross2d.extras.ecs

/**
 * Simple marker component, which users should tag potential components with.
 *
 * This helps limit code completion suggestions and also adds a bit of explicit sanity checking
 * to prevent users from accidentally adding a different, similarly named class from elsewhere
 * in the codebase as a component by accident.
 */
interface Component