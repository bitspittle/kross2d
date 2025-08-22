# Kross2D ECS

Kross2D provides a Kotlin-only implementation of a basic ECS.

### What's an ECS?

ECS stands for **E**ntity **C**omponent **S**ystem.

If you're not familiar with the ECS architecture pattern, it's fundamentally an approach to break
up your game data and game logic into separate classes. The data classes are ideally small and very
focused in purpose. 

ECS allows for a codebase that's extremely flexible while also, in general, much more cache
friendly than traditional OOP. Your game objects go from being large, complex files to very simple
bags of lego pieces.

* A *component* is just some data.
* An *entity* is a (very simple) collection of one or more components
* A *system* is logic that acts on a family of components, iterating through all families every frame
* A *world* is a collection of entities 

To highlight the strengths of ECS, imagine a beginner trying to
design an RPG.

"OK, I'll need the main player of course, and they have to be able to equip a sword and shield. And
I'll need some enemies to attack."

```
Player class (controllable, health, strength, rendered, animated, collides)
Enemy class (health, strength, rendered, animated, collides)
Weapon class (does damage to an enemy)
Shield class (prevents damage from an enemy)
Item class (can heal / buff the player)
```

Obviously, there's going to be some shared logic between the player and enemy classes. So we need
to create a base class. Let's call it `Actor` for now.

```
Player : Actor // Plus logic for being controlled and using weapons / shields / items
Enemy : Actor
```

Hmmm, but now we also have NPCs, which are rendered, animated, and collidable, but they don't have
health or strength. Time for more base classes!

```
* Player : MortalActor
* Enemy : MortalActor
* Npc : Actor
```

But now a designer points out how *fun* it would be if you could control an enemy temporarily using
a charm skill. At this point, we can...

* copy/paste some code from `Player` to `Enemy` for now
* promote some code up to the `MortalActor` base class, at the risk of it losing its focus
* hack the code by temporarily replacing the `Enemy` with a `Player` with its skin
* give up and reject the feature due to technical limitations.

Next the designer asks for a double-edged sword which damages both the enemy and the player. Or a
shield o' bashing, which can defend attacks but also be used to attack for crushing damage. Or
items that can be applied to weapons to buff them instead. Etc. etc.

You get the idea! It quickly becomes a tangled mess.

With an ECS, you would create components for basic concepts (`Visuals`, `Health`, `Offense`,
`Defense`, `Attacker`, `Defender`, `Equipment`, `Position`, `Target`) and some systems
(`RenderSystem`, `ControllerSystem`, `DamageSystem`). For example, the `DamageSystem` might act on
all entities that have `Attacker`, `Equipment`, and `Target` components.

Sometimes, it can be difficult to debug an ECS, but its strengths are significant enough that it's
a go-to pattern for many games right out of the box.

## Tutorial

### Overview

At a very high overview, *Kross2D ECS* looks like this:

```kotlin
val world = World()

world.addSystem(PhysicsSystem())
world.addSystem(InputSystem())
world.addSystem(RenderSystem())

world.createEntity().apply {
   add(PhysicsComponent(shape))
   add(HealthComponent(maxHealth = 20))
}

world.createEntity().apply {
   add(TextComponent("Player 1"))
   add(PosComponent(20, 20))
}

// in GameState#update

fun update(ctx: UpdateContext) {
   world.update(ctx)
}

// in GameState#draw

fun draw(ctx: DrawContext) {
   world.draw(ctx)
}
```

In other words, you create a world, register some systems with it, create some entities, and then
process the systems.

### Creating a Component

// TODO

### Creating a System

// TODO