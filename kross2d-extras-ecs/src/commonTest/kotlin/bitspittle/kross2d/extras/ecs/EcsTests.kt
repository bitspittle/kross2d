package bitspittle.kross2d.extras.ecs

import bitspittle.kross2d.core.graphics.Colors
import bitspittle.kross2d.core.graphics.ImmutableColor
import bitspittle.kross2d.core.math.Pt2
import bitspittle.kross2d.core.math.Vec2
import bitspittle.kross2d.core.time.Duration
import bitspittle.kross2d.core.time.ImmutableDuration
import bitspittle.kross2d.engine.context.DrawContext
import bitspittle.kross2d.engine.context.UpdateContext
import bitspittle.kross2d.engine.fakes.TestDrawContext
import bitspittle.kross2d.engine.fakes.TestScreen
import bitspittle.kross2d.engine.fakes.TestTimer
import bitspittle.kross2d.engine.fakes.TestUpdateContext
import bitspittle.truthish.assertThat
import bitspittle.truthish.assertThrows
import kotlin.test.Test

class EcsTests {

    class PosComponent(val value: Pt2 = Pt2()) : Component
    class VelComponent(val value: Vec2 = Vec2()) : Component
    class CountdownComponent(val value: Duration = Duration.zero()) : Component {
        val initialValue: ImmutableDuration = value.copy()
    }
    class RenderComponent(val color: ImmutableColor = Colors.CYAN) : Component
    class VirusComponent(var infectedCount: Int = 1) : Component // Component that the InfectionSystem will add to all entities
    class HealingComponent : Component // Will remove the VirusComponent if present

    class MovementSystem : UpdateSystem(Family.all(PosComponent::class, VelComponent::class)) {

        override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
            val pos = entity.get<PosComponent>()
            val vel = entity.get<VelComponent>()

            pos.value += (vel.value * ctx.timer.lastFrame.secs.toFloat())
        }
    }

    class CountdownSystem : UpdateSystem(Family.all(CountdownComponent::class)) {

        override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
            val count = entity.get<CountdownComponent>()
            count.value -= ctx.timer.lastFrame

            if (count.value <= Duration.ZERO) {
                world.deleteEntity(entity)
                assertThat(entity.deleted).isFalse() // Entity is deleted after processing loop is over
            }
        }
    }

    class RenderSystem : DrawSystem(Family.all(RenderComponent::class)) {

        override fun draw(world: World.ImmutableFacade, ctx: DrawContext, entity: Entity) {
            val renderable = entity.get<RenderComponent>()
            ctx.screen.clear(renderable.color)
        }
    }

    class EmitterSystem : UpdateSystem(Family.all(CountdownComponent::class)) {

        override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
            val count = entity.get<CountdownComponent>()
            count.value -= ctx.timer.lastFrame

            while (count.value <= Duration.ZERO) {
                world.createEntity().apply {
                    add(PosComponent())
                    add(VelComponent(Vec2(1, 0)))
                }
                count.value += count.initialValue
            }
        }
    }

    class CountingSystem(family: Family) : UpdateSystem(family) {
        var count = 0
            private set

        override fun beforeUpdates(world: World.Facade, ctx: UpdateContext) {
            count = 0
        }

        override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
            ++count
        }
    }

    class InfectionSystem : UpdateSystem(Family.all()) {

        override fun update(world: World.Facade, ctx: UpdateContext, entity: Entity) {
            val virus = entity.find<VirusComponent>()
            if (virus == null) {
                entity.add(VirusComponent())
                // Add is lazy, will take affect next frame
                assertThat(entity.has<VirusComponent>()).isFalse()
            }
            else {
                virus.infectedCount++

                if (entity.has<HealingComponent>()) {
                    entity.remove<VirusComponent>()
                    // Remove is lazy, will take affect next frame
                    assertThat(entity.has<VirusComponent>()).isTrue()
                }
            }
        }
    }

    @Test
    fun createAndRemoveEntities() {
        val world = World()
        val e1 = world.createEntity()
        val e2 = world.createEntity()
        val e3 = world.createEntity()

        assertThat(e1.deleted).isFalse()
        assertThat(e2.deleted).isFalse()
        assertThat(e3.deleted).isFalse()

        world.deleteEntity(e2)

        assertThat(e1.deleted).isFalse()
        assertThat(e2.deleted).isTrue()
        assertThat(e3.deleted).isFalse()
    }

    @Test
    fun addAndRemoveComponents() {
        val world = World()
        val entity = world.createEntity()

        entity.add(PosComponent())
        entity.add(VelComponent())

        assertThat(entity.find<PosComponent>()).isNotNull()
        assertThat(entity.find<VelComponent>()).isNotNull()
        assertThat(entity.find<CountdownComponent>()).isNull()

        assertThat(entity.has<PosComponent>()).isTrue()
        assertThat(entity.has<VelComponent>()).isTrue()
        assertThat(entity.has<CountdownComponent>()).isFalse()

        entity.remove<VelComponent>()

        assertThat(entity.has<PosComponent>()).isTrue()
        assertThat(entity.has<VelComponent>()).isFalse()
    }

    @Test
    fun tryingToUseDeletedEntitiesThrowsExceptions() {
        val world = World()
        val entity = world.createEntity()
        world.deleteEntity(entity)

        assertThrows<EntityAlreadyDeletedException> { entity.add(PosComponent()) }
        assertThrows<EntityAlreadyDeletedException> { entity.find<PosComponent>() }
        assertThrows<EntityAlreadyDeletedException> { entity.remove<PosComponent>() }
        assertThrows<EntityAlreadyDeletedException> { world.deleteEntity(entity) }
    }

    @Test
    fun canQueryEntities() {
        val world = World()
        val e1 = world.createEntity().apply {
            add(PosComponent())
            add(VelComponent())
        }
        val e2 = world.createEntity().apply {
            add(VelComponent())
        }
        val e3 = world.createEntity().apply {
            add(PosComponent())
        }
        val e4 = world.createEntity().apply {
            add(VelComponent())
            add(PosComponent())
            add(CountdownComponent())
        }
        val empty = world.createEntity()

        assertThat(world.query(Family.all(PosComponent::class, VelComponent::class))).containsExactly(e1, e4)
        assertThat(world.query(Family.all())).containsExactly(e1, e2, e3, e4, empty)
        assertThat(world.query(Family.any())).isEmpty()
        assertThat(world.query(Family.one())).isEmpty()
        assertThat(world.query(Family.none())).containsExactly(e1, e2, e3, e4, empty)
        assertThat(world.query(PosComponent::class)).containsExactly(e1, e3, e4)
    }

    @Test
    fun testFamilyMatches() {
        val world = World()
        val e1 = world.createEntity().apply {
            add(PosComponent())
            add(VelComponent())
        }
        val e2 = world.createEntity().apply {
            add(PosComponent())
            add(VelComponent())
            add(CountdownComponent())
        }
        val e3 = world.createEntity().apply {
            add(VelComponent())
            add(CountdownComponent())
            add(RenderComponent())
        }
        val e4 = world.createEntity().apply {
            add(CountdownComponent())
            add(RenderComponent())
        }
        val empty = world.createEntity()

        Family.all(PosComponent::class, VelComponent::class).let { family ->
            assertThat(family.matches(e1)).isTrue()
            assertThat(family.matches(e2)).isTrue()
            assertThat(family.matches(e3)).isFalse()
            assertThat(family.matches(e4)).isFalse()
            assertThat(family.matches(empty)).isFalse()
        }

        Family.any(CountdownComponent::class, RenderComponent::class).let { family ->
            assertThat(family.matches(e1)).isFalse()
            assertThat(family.matches(e2)).isTrue()
            assertThat(family.matches(e3)).isTrue()
            assertThat(family.matches(e4)).isTrue()
            assertThat(family.matches(empty)).isFalse()
        }

        Family.one(VelComponent::class, CountdownComponent::class).let { family ->
            assertThat(family.matches(e1)).isTrue()
            assertThat(family.matches(e2)).isFalse()
            assertThat(family.matches(e3)).isFalse()
            assertThat(family.matches(e4)).isTrue()
            assertThat(family.matches(empty)).isFalse()
        }

        (Family.all(PosComponent::class, VelComponent::class) + Family.none(CountdownComponent::class)).let { family ->
            assertThat(family.matches(e1)).isTrue()
            assertThat(family.matches(e2)).isFalse()
            assertThat(family.matches(e3)).isFalse()
            assertThat(family.matches(e4)).isFalse()
            assertThat(family.matches(empty)).isFalse()
        }

        Family.none(VelComponent::class).let { family ->
            assertThat(family.matches(e1)).isFalse()
            assertThat(family.matches(e2)).isFalse()
            assertThat(family.matches(e3)).isFalse()
            assertThat(family.matches(e4)).isTrue()
            assertThat(family.matches(empty)).isTrue()
        }

        Family.custom { entity, _ -> entity === e3 || entity === empty }.let { family ->
            assertThat(family.matches(e1)).isFalse()
            assertThat(family.matches(e2)).isFalse()
            assertThat(family.matches(e3)).isTrue()
            assertThat(family.matches(e4)).isFalse()
            assertThat(family.matches(empty)).isTrue()
        }


        Family.all().let { family ->
            assertThat(family.matches(e1)).isTrue()
            assertThat(family.matches(e2)).isTrue()
            assertThat(family.matches(e3)).isTrue()
            assertThat(family.matches(e4)).isTrue()
            assertThat(family.matches(empty)).isTrue()
        }
    }

    @Test
    fun canProcessUpdateSystems() {
        val world = World()
        world.addSystem(MovementSystem())
        world.addSystem(CountdownSystem())
        val e1 = world.createEntity().apply {
            add(PosComponent(Pt2(1, 2)))
            add(VelComponent(Vec2(3, 4)))
        }
        val e2 = world.createEntity().apply {
            add(PosComponent(Pt2(-10, -20)))
        }
        val e3 = world.createEntity().apply {
            add(PosComponent())
            add(VelComponent(Vec2(1000, 500)))
            add(CountdownComponent(Duration.ofSeconds(2.1)))
        }
        val e4 = world.createEntity().apply {
            add(CountdownComponent(Duration.ofSeconds(1.1)))
        }

        val testTimer = TestTimer()
        val ctx = TestUpdateContext(testTimer)

        testTimer.lastFrame = Duration.ofSeconds(1)
        world.update(ctx)

        assertThat(e1.get<PosComponent>().value).isEqualTo(Pt2(4, 6))
        assertThat(e2.get<PosComponent>().value).isEqualTo(Pt2(-10, -20))
        assertThat(e3.get<PosComponent>().value).isEqualTo(Pt2(1000, 500))
        assertThat(e3.get<CountdownComponent>().value).isEqualTo(Duration.ofSeconds(1.1))
        assertThat(e4.get<CountdownComponent>().value).isEqualTo(Duration.ofSeconds(0.1))

        world.update(ctx)

        assertThat(e1.get<PosComponent>().value).isEqualTo(Pt2(7, 10))
        assertThat(e2.get<PosComponent>().value).isEqualTo(Pt2(-10, -20))
        assertThat(e3.get<PosComponent>().value).isEqualTo(Pt2(2000, 1000))
        assertThat(e3.get<CountdownComponent>().value).isEqualTo(Duration.ofSeconds(0.1))
        assertThat(e4.deleted).isTrue()

        world.update(ctx)
        assertThat(e1.get<PosComponent>().value).isEqualTo(Pt2(10, 14))
        assertThat(e2.get<PosComponent>().value).isEqualTo(Pt2(-10, -20))
        assertThat(e3.deleted).isTrue()
        assertThat(e4.deleted).isTrue()
    }

    @Test
    fun canProcessDrawSystems() {
        val world = World()
        world.addSystem(RenderSystem())
        world.createEntity().apply { add(RenderComponent(Colors.MAGENTA)) }
        val testScreen = TestScreen()
        val ctx = TestDrawContext(testScreen)

        assertThat(testScreen.clearColor).isNotEqualTo(Colors.MAGENTA)
        world.draw(ctx)
        assertThat(testScreen.clearColor).isEqualTo(Colors.MAGENTA)
    }

    @Test
    fun canRemoveSystems() {
        val testTimer = TestTimer()
        val ctx = TestUpdateContext(testTimer)

        val world = World()
        val countdownSystem = CountdownSystem()
        world.addSystem(countdownSystem)
        val e3 = world.createEntity().apply { add(CountdownComponent(Duration.ofSeconds(3))) }
        val e2 = world.createEntity().apply { add(CountdownComponent(Duration.ofSeconds(2))) }
        val e1 = world.createEntity().apply { add(CountdownComponent(Duration.ofSeconds(1))) }

        testTimer.lastFrame = Duration.ofSeconds(1.5)
        world.update(ctx)

        assertThat(e3.deleted).isFalse()
        assertThat(e2.deleted).isFalse()
        assertThat(e1.deleted).isTrue()

        testTimer.lastFrame = Duration.ofMinutes(9999)
        world.removeSystem(countdownSystem)
        world.update(ctx)

        assertThat(e3.deleted).isFalse()
        assertThat(e2.deleted).isFalse()
    }

    @Test
    fun entitiesCanBeAddedMidUpdate() {
        val testTimer = TestTimer()
        val ctx = TestUpdateContext(testTimer)

        val world = World()
        val projectileCounter = CountingSystem(Family.all(PosComponent::class, VelComponent::class))
        world.addSystem(EmitterSystem())
        world.addSystem(projectileCounter)

        // This emitter will be found by the EmitterSystem to generate new entities every 3 seconds
        world.createEntity().apply { add(CountdownComponent(Duration.ofSeconds(3))) }

        assertThat(projectileCounter.count).isEqualTo(0)


        testTimer.lastFrame = Duration.ofSeconds(3)
        world.update(ctx)
        // Projectile just added, won't be processed until next frame
        assertThat(projectileCounter.count).isEqualTo(0)

        testTimer.lastFrame = Duration.ofSeconds(0)
        world.update(ctx)
        assertThat(projectileCounter.count).isEqualTo(1)

        testTimer.lastFrame = Duration.ofSeconds(9)
        world.update(ctx)
        // Counting system will update next frame
        assertThat(projectileCounter.count).isEqualTo(1)

        testTimer.lastFrame = Duration.ofSeconds(0)
        world.update(ctx)
        assertThat(projectileCounter.count).isEqualTo(4)
    }

    @Test
    fun componentsCanBeAddedAndRemovedMidUpdate() {
        val world = World()
        world.addSystem(InfectionSystem())

        val e = world.createEntity()
        assertThat(e.has<VirusComponent>()).isFalse()

        val ctx = TestUpdateContext()
        world.update(ctx) // InfectionSystem adds a VirusComponent
        assertThat(e.get<VirusComponent>().infectedCount).isEqualTo(1)

        world.update(ctx)
        assertThat(e.get<VirusComponent>().infectedCount).isEqualTo(2)

        e.add(HealingComponent())
        world.update(ctx)
        assertThat(e.has<VirusComponent>()).isFalse()
    }
}