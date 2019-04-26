package bitspittle.kross2d.extras.ecs

import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.UpdateContext
import kotlin.reflect.KClass

class EntityAlreadyDeletedException : IllegalArgumentException("Entity already deleted")
class ProcessingWorldException : IllegalStateException("The current action is not supported when processing the world")

class World {
    interface ImmutableFacade {
        fun query(family: Family): Sequence<Entity>
        fun query(componentClass: KClass<out Component>): Sequence<Entity>
    }

    interface Facade : ImmutableFacade {
        fun createEntity(): Entity
        fun deleteEntity(entity: Entity)
    }

    private val facade = object : Facade {
        override fun createEntity() = this@World.createEntity()
        override fun deleteEntity(entity: Entity) = this@World.deleteEntity(entity)
        override fun query(family: Family) = this@World.query(family)
        override fun query(componentClass: KClass<out Component>) = this@World.query(componentClass)
    }

    private val updateSystems = mutableListOf<UpdateSystem>()
    private val drawSystems = mutableListOf<DrawSystem>()

    private val entities = mutableListOf<Entity>()
    // If create/deleteEntity is called while processing
    private val entitiesToAdd = mutableListOf<Entity>()
    private val entitiesToDelete = mutableListOf<Entity>()

    private val components = mutableMapOf<KClass<out Component>, MutableMap<Int, Component>>()
    // If add/remove(Component) is called while processing
    private val componentsToAdd = mutableListOf<Pair<Entity, Component>>()
    private val componentsToRemove = mutableListOf<Pair<Entity, KClass<out Component>>>()

    private var processing = false

    fun createEntity(): Entity {
        val newEntity = Entity(entities.size + entitiesToAdd.size, this)
        if (processing) entitiesToAdd.add(newEntity) else entities.add(newEntity)

        return newEntity
    }

    fun deleteEntity(entity: Entity) {
        if (entity.deleted) throw EntityAlreadyDeletedException()

        if (processing) {
            entitiesToDelete.add(entity)
            return
        }

        handleDelete(entity)
    }

    fun addSystem(updateSystem: UpdateSystem) {
        assertNotProcessing()
        updateSystems.add(updateSystem)
    }

    fun removeSystem(updateSystem: UpdateSystem) {
        assertNotProcessing()
        updateSystems.remove(updateSystem)
    }

    fun addSystem(drawSystem: DrawSystem) {
        assertNotProcessing()
        drawSystems.add(drawSystem)
    }

    fun removeSystem(drawSystem: DrawSystem) {
        assertNotProcessing()
        drawSystems.remove(drawSystem)
    }

    fun update(ctx: UpdateContext) {
        process {
            updateSystems.forEach { system ->
                system.beforeUpdates(facade, ctx)
                query(system.family).forEach { entity ->
                    if (system.family.matches(entity)) {
                        system.update(facade, ctx, entity)
                    }
                }
                system.afterUpdates(facade, ctx)
            }
        }
    }

    fun draw(ctx: DrawContext) {
        process {
            drawSystems.forEach { system ->
                system.beforeDraws(facade, ctx)
                query(system.family).forEach { entity ->
                    if (system.family.matches(entity)) {
                        system.draw(facade, ctx, entity)
                    }
                }
                system.afterDraws(facade, ctx)
            }
        }
    }

    private fun process(block: () -> Unit) {
        assertNotProcessing()
        processing = true
        try {
            block()
        }
        finally {
            processing = false
            handlePostProcess()
        }
    }

    private fun assertNotProcessing() {
        if (processing) throw ProcessingWorldException()
    }

    private fun handlePostProcess() {
        entitiesToAdd.forEach { entities.add(it) }
        entitiesToAdd.clear()

        componentsToAdd.forEach { handleAddComponent(it.first, it.second) }
        componentsToAdd.clear()

        componentsToRemove.forEach { handleRemoveComponent(it.first, it.second) }
        componentsToRemove.clear()

        entitiesToDelete.forEach { handleDelete(it) }
        entitiesToDelete.clear()
    }

    /**
     * Query this world for all entities that match the target [family], returning a
     * [Sequence] to the results.
     *
     * Use the [query] method which takes a [KClass] if possible, as it is potentially a lot more
     * performant especially if there are a large number of entities.
     *
     * TODO: Should we optimize this method as well?
     */
    fun query(family: Family): Sequence<Entity> = entities.asSequence().filter { family.matches(it) }

    /**
     * Query this world for all entities that own the target [componentClass], returning a
     * [Sequence] to the results.
     */
    fun query(componentClass: KClass<out Component>): Sequence<Entity> {
        return components[componentClass]?.keys?.asSequence()?.map { id -> entities[id] } ?: sequenceOf()
    }

    internal fun addComponentFor(entity: Entity, component: Component) {
        if (entity.deleted) throw EntityAlreadyDeletedException()
        if (processing) {
            componentsToAdd.add(entity to component)
            return
        }

        handleAddComponent(entity, component)
    }

    @Suppress("UNCHECKED_CAST") // Key/Value are guaranteed to be same type
    @PublishedApi
    internal fun <C: Component> findComponentFor(entity: Entity, componentClass: KClass<C>): C? {
        if (entity.deleted) throw EntityAlreadyDeletedException()
        return components[componentClass]?.get(entity.id) as C?
    }

    @PublishedApi
    internal fun removeComponentFor(entity: Entity, componentClass: KClass<out Component>) {
        if (entity.deleted) throw EntityAlreadyDeletedException()
        if (processing) {
            componentsToRemove.add(entity to componentClass)
            return
        }

        handleRemoveComponent(entity, componentClass)
    }

    private fun handleAddComponent(entity: Entity, component: Component) {
        components.getOrPut(component::class) { mutableMapOf() }[entity.id] = component
    }

    private fun handleRemoveComponent(entity: Entity, componentClass: KClass<out Component>) {
        components[componentClass]?.remove(entity.id)
    }

    private fun handleDelete(entity: Entity) {
        components.values.forEach { it.remove(entity.id) }

        // O(1) to remove from the end of a list so swap before removing
        val lastIndex = entities.lastIndex
        if (entity.id != lastIndex) {
            entities[entity.id] = entities[lastIndex].also { entities[lastIndex] = entities[entity.id] }
        }
        entities.removeAt(lastIndex)
        entity.deleted = true
    }
}