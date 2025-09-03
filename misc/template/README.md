Follow these steps to convert this template into your own game!

1. Fork a copy of this template directory to your own project.

1. Choose a name for your project. Let's go with `Demo` for now.

1. In the root `settings.gradle.kts` file...
   1. Rename the two references to `yourgame` 
     * For example,
     ```
       rootProject.name = "demo"
       include(":demo")
     ```

1. Rename child directory from `yourname` to your name.
   * For example, `demo`

1. In `commonMain`...
   1. Rename `src/commonMain/kotlin/YourGameState.kt`
      * For example, `DemoState.kt`
   1. Rename the class name inside the file
      * For example, `class DemoState : GameState`
   1. In `build.gradle.kts`, rename the group to something unique that represents
      an group ID for your software.
      * See also [Maven's naming conventions](https://maven.apache.org/guides/mini/guide-naming-conventions.html)

1. In `jvmMain`...
   1. Rename the application title in `src/jvmMain/kotlin/main.kt`
      * For example, `"Your Game"` -> `"Demo"`
   1. Update the state class to what you named it in `commonMain`
      * For example, `YourGameState` -> `DemoState`

1. In `jsMain`...
   1. In `src/jsMain/resources/index.html`...
       1. Change the `Your Game` title
       1. Change the `/yourgame.js` script name
   1. In `src/jsMain/kotlin/main.kt`
      1. Update the state class to what you named it in `commonMain`
          * For example, `YourGameState` -> `DemoState`

1. To test that everything is working, run the following commands in the root
   directory:

   ```
   $ ./gradlew runJvm
   $ ./gradlew jsBroswerProductionRun
   ```

1. If you decide you don't want any particular target, remove it from the `build.gradle.kts` file as well as delete the
   corresponding ${target}Main folder.

1. And *finally*, delete this `README.md`! You don't need it anymore.
