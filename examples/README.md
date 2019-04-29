# Examples

This README contains a very high level description of the purpose of each
example. 

Every project has classes that implement `GameState` which have more detailed
information. These classes can be found in those project's 
`common/src/commonMain/kotlin` directory (e.g.
`colorcanvas-common/src/commonMain/kotlin/ColorCanvasState.kt`).

Every platform-specific implementation has an application runner, which you can
find in the `$platform/src/main/kotlin` folder (e.g.
`colorcanvas-jvm/src/main/kotlin/ColorCanvasRunner.kt`).

## Color Canvas

Demonstrates a minimal skeleton for an app using *Kross2D*.

Run on desktop: `gradlew :examples:colorcanvas:colorcanvas-jvm:run`
Run in browser: `gradlew :examples:colorcanvas:colorcanvas-js:run`

## Sprite

Demonstrates the loading and rendering of images.

Run on desktop: `gradlew :examples:colorcanvas:sprite-jvm:run`
Run in browser: `gradlew :examples:colorcanvas:sprite-js:run`

