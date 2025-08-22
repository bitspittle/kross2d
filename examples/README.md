# Examples

This README contains a very high level description of the purpose of each
example. 

Every project has classes that implement `GameState` which have more detailed
information. These classes can be found in those project's 
`common/src/commonMain/kotlin` directory (e.g.
`helloworld-common/src/commonMain/kotlin/HelloWorldState.kt`).

Every platform-specific implementation has an application runner, which you can
find in the `$platform/src/main/kotlin` folder (e.g.
`helloworld-jvm/src/main/kotlin/HelloWorldRunner.kt`).

## Hello World

Demonstrates a minimal skeleton for an app using *Kross2D*.

Run on desktop: `gradlew :helloworld:helloworld-jvm:run`
Run in browser: `gradlew :helloworld:helloworld-js:run`

## Sprite

Demonstrates the loading and rendering of images.

Run on desktop: `gradlew :sprite:sprite-jvm:run`
Run in browser: `gradlew :sprite:sprite-js:run`

## Sounds

Demonstrates music and sound effects.

Run on desktop: `gradlew :sounds:sounds-jvm:run`
Run in browser: `gradlew :sounds:sounds-js:run`

## Many States

Demonstrates how you can split a game into multiple separate game states.

Run on desktop: `gradlew :manystates:manystates-jvm:run`
Run in browser: `gradlew :manystates:manystates-js:run`

## Paint

Demonstrates mouse input.

Run on desktop: `gradlew :paint:paint-jvm:run`
Run in browser: `gradlew :paint:paint-js:run`

## Pong

Demonstrates a simple yet complete game. Also, uses the ECS library.

Run on desktop: `gradlew :pong:pong-jvm:run`
Run in browser: `gradlew :pong:pong-js:run`
