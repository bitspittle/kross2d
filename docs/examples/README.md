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

Run on desktop: `gradlew :docs:examples:helloworld:helloworld-jvm:run`
Run in browser: `gradlew :docs:examples:helloworld:helloworld-js:run`

## Sprite

Demonstrates the loading and rendering of images.

Run on desktop: `gradlew :docs:examples:sprite:sprite-jvm:run`
Run in browser: `gradlew :docs:examples:sprite:sprite-js:run`

## Sprite

Demonstrates mouse input.

Run on desktop: `gradlew :docs:examples:paint:paint-jvm:run`
Run in browser: `gradlew :docs:examples:paint:paint-js:run`

