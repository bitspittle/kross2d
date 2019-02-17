package bitspittle.kross2d.engine.input

enum class Key {
    ESC
    // TODO: Add way more keys!
}

/**
 * An interface that exposes the full state of the keyboard.
 */
interface Keyboard {
    fun isJustPressed(key: Key): Boolean
    fun isDown(key: Key): Boolean
    fun isJustReleased(key: Key): Boolean
    fun isUp(key: Key): Boolean
}

internal class DefaultKeyboard : Keyboard {
    private val keysPrev = mutableSetOf<Key>()
    private val keysCurr = mutableSetOf<Key>()

    fun handleKey(key: Key, isDown: Boolean) {
        if (isDown) keysCurr.add(key) else keysCurr.remove(key)
    }

    fun step() {
        keysPrev.clear()
        keysPrev.addAll(keysCurr)
    }

    override fun isJustPressed(key: Key) = !keysPrev.contains(key) && keysCurr.contains(key)
    override fun isDown(key: Key) = keysCurr.contains(key)
    override fun isJustReleased(key: Key) = keysPrev.contains(key) && !keysCurr.contains(key)
    override fun isUp(key: Key) = !keysCurr.contains(key)
}