Follow these steps to convert this template into your own game!

1. Fork a copy of this template directory to your own project.

1. Choose a name for your project. Let's go with `Demo` for now.

1. In the root `settings.gradle` file...
   1. Rename the two references to `yourgame` 
     * For example,
     ```
       rootProject.name = "demo"
       include(":demo-common")
     ```

1. Rename base directories from `yourname` to your name.
   * For example, `demo-common`, `demo-jvm`, and `demo-js`

1. In `common`...
   1. Rename `src/commonMain/kotlin/YourGameState.kt`
      * For example, `DemoState.kt`
   1. Rename the class name inside the file
      * For example, `class DemoState : GameState`
   1. In `build.gradle`, rename the group to something unique that represents
      you
      * See also [Maven's naming conventions](https://maven.apache.org/guides/mini/guide-naming-conventions.html)

1. In `jvm`...
   1. Rename `src/main/kotlin/YourGameRunner.kt`
      * For example, `DemoRunner.kt`
   1. In `build.gradle`...
      1. Update the `common` build dependency
         * For example, `compile project(":demo-common")`
      1. Update the main class name
         * For example, `def mainClass = 'DemoRunnerKt`

1. In `js`...
   1. Rename `src/main/kotlin/YourGameRunner.kt`
   1. In `build.gradle`...
      1. Update the `common` build dependency
   1. In `web/index.html`...
      1. Change the `Your Game` title
      1. Change the `yourgame` includes

1. To test that everything is working, run the following commands in the root
   directory:

   ```
   $ ./gradlew run
   $ ./gradlew runWeb
   ```

 1. And *finally*, delete these `README.md`! You don't need it anymore.
