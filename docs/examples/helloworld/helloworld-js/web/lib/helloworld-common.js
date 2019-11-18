if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'helloworld-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'helloworld-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'helloworld-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'helloworld-common'.");
}
this['helloworld-common'] = function (_, Kotlin, $module$kross2d) {
  'use strict';
  var numberToInt = Kotlin.numberToInt;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var Color = $module$kross2d.bitspittle.kross2d.core.graphics.Color;
  var Duration = $module$kross2d.bitspittle.kross2d.core.time.Duration;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  function HelloWorldState() {
    this.clearColor_0 = new Color(0, 0, 0);
    this.elapsed_0 = Duration.Companion.zero();
  }
  var Math_0 = Math;
  HelloWorldState.prototype.cycleValue_0 = function (max, t) {
    var x = t.secs;
    return numberToInt((Math_0.sin(x) + 1.0) / 2.0 * max);
  };
  HelloWorldState.prototype.update_kung05$ = function (ctx) {
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
    this.elapsed_0.plusAssign_evd4je$(ctx.timer.lastFrame);
    this.clearColor_0.r = this.cycleValue_0(255, this.elapsed_0);
    this.clearColor_0.g = this.cycleValue_0(255, this.elapsed_0.times_14dthe$(2.0));
    this.clearColor_0.b = this.cycleValue_0(255, this.elapsed_0.times_14dthe$(3.0));
  };
  HelloWorldState.prototype.draw_glqt06$ = function (ctx) {
    ctx.screen.clear_cqchof$(this.clearColor_0);
  };
  HelloWorldState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HelloWorldState',
    interfaces: [GameState]
  };
  _.HelloWorldState = HelloWorldState;
  HelloWorldState.prototype.enter_ahvl4o$ = GameState.prototype.enter_ahvl4o$;
  Kotlin.defineModule('helloworld-common', _);
  return _;
}(typeof this['helloworld-common'] === 'undefined' ? {} : this['helloworld-common'], kotlin, kross2d);
