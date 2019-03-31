package bitspittle.kross2d.extras.anim

import bitspittle.kross2d.core.time.Duration
import bitspittle.kross2d.core.time.ImmutableDuration

/**
 * A keyframe represents a snapshotted value (and, if specified, a lerping function to handle
 * smooth transitions between the current keyframe and the next one)
 */
data class KeyFrame<T>(val value: T, val duration: ImmutableDuration, val lerp: Lerp<T> = Lerps::noLerp)

/**
 * A strategy that controls how an animation plays, usually related to what the animation should do
 * once the current elapsed time has passed the end of the animation. Several strategie are
 * provided out of the box (which should cover almost all cases).
 */
interface PlayStrategy {
    val elapsed: ImmutableDuration
    fun reset()
    fun elapse(duration: ImmutableDuration, animLength: ImmutableDuration)
}

/**
 * A [PlayStrategy] which plays an animation once and then stops.
 */
class OneShotPlayStrategy : PlayStrategy {
    private val _elapsed = Duration.zero()
    override val elapsed = _elapsed

    override fun reset() {
        _elapsed.setFrom(Duration.ZERO)
    }

    override fun elapse(duration: ImmutableDuration, animLength: ImmutableDuration) {
        _elapsed += duration
        _elapsed.clampToMax(animLength)
    }
}

/**
 * A [PlayStrategy] which repeats an animation from the beginning once it reaches the end.
 */
class LoopingPlayStrategy : PlayStrategy {
    private val _elapsed = Duration.zero()
    override val elapsed = _elapsed

    override fun reset() {
        _elapsed.setFrom(Duration.ZERO)
    }

    override fun elapse(duration: ImmutableDuration, animLength: ImmutableDuration) {
        _elapsed += duration
        while (_elapsed >= animLength) {
            _elapsed -= animLength
        }
    }
}

/**
 * A [PlayStrategy] which goes from start to end in a forward direction, then from end to start
 * in a reverse direction.
 *
 * Note that you may want to be careful with your first and last keyframes when using this
 * strategy, as you may spend twice as long in both of them as you bounce back and forth.
 * This works better for frames that also [Lerp], but keep in mind another solution (e.g.
 * for bouncing static animations) is to just create a [LoopingPlayStrategy] and manually specify
 * the values in both directions.
 *
 */
class BouncingPlayStrategy : PlayStrategy {
    private var elapseForward = true

    private val _elapsed = Duration.zero()
    override val elapsed = _elapsed

    override fun reset() {
        _elapsed.setFrom(Duration.ZERO)
        elapseForward = true
    }

    override fun elapse(duration: ImmutableDuration, animLength: ImmutableDuration) {
        if (elapseForward) {
            _elapsed += duration
            if (_elapsed >= animLength) {
                val extra = _elapsed - animLength
                _elapsed.setFrom(animLength)
                elapseForward = false
                elapse(extra, animLength)
            }
        }
        else {
            _elapsed -= duration
            if (_elapsed < Duration.ZERO) {
                val extra = -_elapsed
                _elapsed.setFrom(Duration.ZERO)
                elapseForward = true
                elapse(extra, animLength)
            }
        }
    }
}

/**
 * An animation is a list of sequential [KeyFrame].
 *
 * To use it, simply keep calling [elapse] and then ask the animation for its current [value].
 *
 * For example, if you want to use this class to animate a series of [Image]s, each frame being
 * 300ms long, you could register them like so:
 *
 * ```
 * // In init()
 * val FRAME_DURATION = Duration.ofMillis(300)
 * anim = Anim(FRAME_DURATION, image1, image2, image3)
 *
 * // In update()
 * anim.elapse(ctx.timer.lastFrameDuration)
 *
 * // In draw()
 * ctx.screen.draw(anim.value, ... params ...)
 *
 * ```
 *
 * Callers can also pass in a custom [PlayStrategy]. The default behavior loops the current
 * animation.
 *
 * @param T The type of the value being keyframed
 */
class Anim<T>(val keyframes: List<KeyFrame<T>>, private val playStrategy: PlayStrategy = LoopingPlayStrategy()) {
    constructor(
        vararg keyframes: KeyFrame<T>,
        playStrategy: PlayStrategy = LoopingPlayStrategy()
    ) : this(listOf(*keyframes), playStrategy)

    constructor(
        frameDuration: ImmutableDuration,
        vararg values: T,
        playStrategy: PlayStrategy = LoopingPlayStrategy(),
        defaultLerp: Lerp<T> = Lerps::noLerp
    ) : this(listOf(*values).map { KeyFrame(it, frameDuration, defaultLerp) }, playStrategy)

    var value: T = keyframes[0].value
        private set

    /**
     * The total duration of this animation (i.e. the sum of all of its keyframes)
     */
    val duration: ImmutableDuration

    /**
     * The time each frame starts at, starting with the second frame. (We skip the first frame as
     * an optimization because it always starts at time 0)
     *
     * Because of the awkward indexing, code shouldn't access this directly - instead,
     * call [frameStart].
     */
    private val startTimes: List<Duration>

    private var currFrame: Int = 0

    init {
        if (keyframes.isEmpty()) {
            throw IllegalArgumentException("Animation must contain at least one frame")
        }

        // Index 'n' is the start duration for frame 'n + 1'
        // We can skip index 0 because it's always zero
        startTimes = List(keyframes.size - 1) { Duration.zero() }

        val accum = Duration.zero()
        keyframes.take(keyframes.size - 1).forEachIndexed { i, keyframe ->
            accum += keyframe.duration
            startTimes[i].setFrom(accum)
        }
        accum += keyframes.last().duration
        duration = accum
    }

    /**
     * Reset this animation back to the beginning.
     */
    fun reset() {
        playStrategy.reset()
        elapse(Duration.ZERO) // Cause `value` to be updated
    }

    /**
     * Move this animation some amount of time forward. After calling this function, the
     * [value] property may change.
     */
    fun elapse(duration: ImmutableDuration) {
        playStrategy.elapse(duration, this.duration)

        currFrame = startTimes.indexOfFirst { it > playStrategy.elapsed }
        if (currFrame < 0) currFrame = keyframes.lastIndex // Only happens if we're on the last frame

        value = if (currFrame == keyframes.lastIndex) {
            keyframes[currFrame].value
        } else {
            val start = keyframes[currFrame].value
            val end = keyframes[currFrame + 1].value

            val percent = ((playStrategy.elapsed.nanos - frameStart(currFrame).nanos) / keyframes[currFrame].duration.nanos).toFloat()
            keyframes[currFrame].lerp(percent, start, end)
        }
    }

    private fun frameStart(frame: Int): ImmutableDuration = startTimes.getOrElse(frame - 1) { Duration.ZERO }
}