if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'colorcanvas-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'colorcanvas-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'colorcanvas-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'colorcanvas-common'.");
}
this['colorcanvas-common'] = function (_, Kotlin, $module$kross2d) {
  'use strict';
  var numberToInt = Kotlin.numberToInt;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  var Color = $module$kross2d.bitspittle.kross2d.core.graphics.Color;
  var Duration = $module$kross2d.bitspittle.kross2d.core.time.Duration;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  ColorCanvasState.prototype = Object.create(GameState.prototype);
  ColorCanvasState.prototype.constructor = ColorCanvasState;
  function ColorCanvasState() {
    GameState.call(this);
    this.clearColor_0 = new Color(0, 0, 0);
    this.elapsed_0 = Duration.Companion.zero();
  }
  var Math_0 = Math;
  ColorCanvasState.prototype.cycleValue_0 = function (max, t) {
    var x = t.secs;
    return numberToInt((Math_0.sin(x) + 1.0) / 2.0 * max);
  };
  ColorCanvasState.prototype.update_kung05$ = function (ctx) {
    if (ctx.keyboard.isDown_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
    this.elapsed_0.plusAssign_evd4je$(ctx.timer.lastFrameDuration);
    this.clearColor_0.r = this.cycleValue_0(255, this.elapsed_0);
    this.clearColor_0.g = this.cycleValue_0(255, this.elapsed_0.times_14dthe$(2.0));
    this.clearColor_0.b = this.cycleValue_0(255, this.elapsed_0.times_14dthe$(3.0));
  };
  ColorCanvasState.prototype.draw_glqt06$ = function (ctx) {
    ctx.screen.clear_msppnp$(this.clearColor_0);
  };
  ColorCanvasState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ColorCanvasState',
    interfaces: [GameState]
  };
  _.ColorCanvasState = ColorCanvasState;
  Kotlin.defineModule('colorcanvas-common', _);
  return _;
}(typeof this['colorcanvas-common'] === 'undefined' ? {} : this['colorcanvas-common'], kotlin, kross2d);
