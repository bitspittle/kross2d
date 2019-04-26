package bitspittle.kross2d.extras.ecs

import kotlin.reflect.KClass

// TODO: Make this an inline class when that feature stabilizes
class Entity internal constructor(internal val id: Int, @PublishedApi internal val world: World) {
    internal var deleted = false

    fun add(component: Component) {
        world.addComponentFor(this, component)
    }

    fun <C: Component> find(componentClass: KClass<C>): C? {
        return world.findComponentFor(this, componentClass)
    }

    fun <C: Component> get(componentClass: KClass<C>): C {
        return find(componentClass)!!
    }

    inline fun <reified C: Component> find(): C? {
        return find(C::class)
    }

    inline fun <reified C: Component> get(): C {
        return find()!!
    }

    fun <C: Component> remove(componentClass: KClass<C>) {
        world.removeComponentFor(this, componentClass)
    }

    inline fun <reified C: Component> remove() {
        remove(C::class)
    }

    fun <C: Component> has(componentClass: KClass<C>): Boolean {
        return find(componentClass) != null
    }

    inline fun <reified C: Component> has(): Boolean {
        return has(C::class)
    }
}