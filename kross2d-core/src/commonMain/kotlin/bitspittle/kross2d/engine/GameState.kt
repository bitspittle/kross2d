package bitspittle.kross2d.engine

import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.InitContext
import bitspittle.kross2d.engine.context.UpdateContext

/**
 * Interface for code that handles the game loop of some part of a game.
 *
 * A game will always have at least one game state, but it can have many (e.g. title screen, main
 * game, end credits, etc.)
 */
interface GameState {
    fun init(ctx: InitContext) {}
    fun update(ctx: UpdateContext)
    fun draw(ctx: DrawContext)
}
