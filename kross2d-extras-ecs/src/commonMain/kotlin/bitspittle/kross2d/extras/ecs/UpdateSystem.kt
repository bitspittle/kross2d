package bitspittle.kross2d.extras.ecs

import bitspittle.kross2d.engine.context.UpdateContext

/**
 * A system that handles update logic on all matching [Entity] instances
 */
abstract class UpdateSystem(val family: Family) {
    /**
     * Called once each frame, before calling the first [update]
     */
    open fun beforeUpdates(world: World.Facade, ctx: UpdateContext) {}

    abstract fun update(world: World.Facade, ctx: UpdateContext, entity: Entity)

    /**
     * Called once each frame, after calling the final [update]
     */
    open fun afterUpdates(world: World.Facade, ctx: UpdateContext) {}
}
