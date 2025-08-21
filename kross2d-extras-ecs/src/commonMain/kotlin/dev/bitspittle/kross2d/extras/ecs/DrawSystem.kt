package dev.bitspittle.kross2d.extras.ecs

import dev.bitspittle.kross2d.engine.context.DrawContext

/**
 * A system that handles rendering logic on all matching [Entity] instances
 */
abstract class DrawSystem(val family: Family) {
    /**
     * Called once each frame, before calling the first [draw]
     */
    open fun beforeDraws(world: World.ImmutableFacade, ctx: DrawContext) {}

    abstract fun draw(world: World.ImmutableFacade, ctx: DrawContext, entity: Entity)

    /**
     * Called once each frame, after calling the final [draw]
     */
    open fun afterDraws(world: World.ImmutableFacade, ctx: DrawContext) {}
}
