package bitspittle.kross2d.extras.graphics

import bitspittle.kross2d.core.math.ImmutableVec2
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.memory.Box
import bitspittle.kross2d.core.memory.Disposer
import bitspittle.kross2d.engine.graphics.Image
import bitspittle.kross2d.engine.graphics.subimage

/**
 * A helper class which targets a tiled [Image] and helps break it down into tiles.
 *
 * This class will lazily cache tiles as they are requested.
 */
class Tiles(
    private val image: Box<Image>,
    val tileSize: ImmutableVec2
) {
    private val tiles: MutableMap<Pair<Int, Int>, Box<Image>> = mutableMapOf()

    fun getTile(x: Int, y: Int): Box<Image> =
        tiles.getOrPut(x to y) {
            image.subimage(Pt2(x * tileSize.x, y * tileSize.y), tileSize)
        }
}