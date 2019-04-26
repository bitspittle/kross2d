package bitspittle.kross2d.extras.ecs

import kotlin.reflect.KClass

/**
 * A test to see if an entity matches the target list of components based on some condition.
 */
typealias EntityMatchesTest = (Entity, List<KClass<out Component>>) -> Boolean

/**
 * A specification for a family of components, useful for testing if an [Entity] belongs to this
 * family or not.
 *
 * Families may be composed to produce extremely flexible constraints.
 */
interface Family {
    companion object {
        /**
         * Family that matches entities which own all components.
         *
         * If no components are specified, e.g. `Family.all()`, this family matches unconditionally
         * against all entities.
         */
        fun all(vararg members: KClass<out Component>): Family = SingleFamily(listOf(*members)) { entity, components ->
            components.all { entity.has(it) }
        }

        /**
         * Family that matches entities which own at least one of the components.
         *
         * If no components are specified, e.g. `Family.any()`, this family will reject all
         * entities unconditionally.
         */
        fun any(vararg members: KClass<out Component>): Family = SingleFamily(listOf(*members)) { entity, components ->
            components.any { entity.has(it) }
        }

        /**
         * Family that matches entities which own only one of the components.
         *
         * This is useful for asserting systems that act on two mutually exclusive components.
         */
        fun one(vararg members: KClass<out Component>): Family = SingleFamily(listOf(*members)) { entity, components ->
            components.count { entity.has(it) } == 1
        }

        /**
         * Family that matches entities which own none of the components.
         *
         * If no components are specified, e.g. `Family.none()`, this family matches unconditionally
         * against all entities.
         */
        fun none(vararg members: KClass<out Component>): Family = SingleFamily(listOf(*members)) { entity, components ->
            components.none { entity.has(it) }
        }

        /**
         * Family with custom behavior.
         */
        fun custom(vararg members: KClass<out Component>, entityMatches: EntityMatchesTest): Family =
            SingleFamily(listOf(*members), entityMatches)

    }
    fun matches(entity: Entity): Boolean
    operator fun plus(other: Family): Family
}

/**
 * A single family of components plus some logic to test against an entity in a fully customizable
 * way.
 *
 * A user can't create directly; instead, they should use [Family.custom] instead.
 */
class SingleFamily internal constructor(
    private val members: List<KClass<out Component>>,
    private var entityMatches: EntityMatchesTest
) : Family {
    override fun matches(entity: Entity): Boolean = entityMatches(entity, members)
    override operator fun plus(other: Family): Family {
        return FamilyGroup(listOf(this, other))
    }
}

/**
 * A group of multiple families.
 *
 * A user can't create directly; instead, they should add [Family] instances together using
 * the `+` operator.
 */
class FamilyGroup internal constructor(private val families: List<Family>): Family {
    override fun matches(entity: Entity): Boolean = families.all { it.matches(entity) }
    override operator fun plus(other: Family): Family {
        return FamilyGroup(this.families + other)
    }
}