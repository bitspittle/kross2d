if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'sounds-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'sounds-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'sounds-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'sounds-common'.");
}
this['sounds-common'] = function (_, Kotlin, $module$kross2d) {
  'use strict';
  var Color = $module$kross2d.bitspittle.kross2d.core.graphics.Color;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Unit = Kotlin.kotlin.Unit;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var IntRange = Kotlin.kotlin.ranges.IntRange;
  var Key$values = $module$kross2d.bitspittle.kross2d.engine.input.Key.values;
  var getOrNull = Kotlin.kotlin.collections.getOrNull_8ujjk8$;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  SoundsState.prototype = Object.create(GameState.prototype);
  SoundsState.prototype.constructor = SoundsState;
  var CLEAR_COLOR;
  function SoundsState() {
    GameState.call(this);
    this.sounds_0 = Kotlin.newArray(10, null);
  }
  function SoundsState$init$lambda$lambda(this$SoundsState, closure$i) {
    return function (it) {
      this$SoundsState.sounds_0[closure$i] = it;
      return Unit;
    };
  }
  var checkIndexOverflow = Kotlin.kotlin.collections.checkIndexOverflow_za3lpa$;
  SoundsState.prototype.init_jezbry$ = function (ctx) {
    var tmp$, tmp$_0;
    var index = 0;
    tmp$ = listOf(['boing.wav', 'boxing_bell.wav', 'chainsaw.wav', 'creak.wav', 'doorbell.wav', 'honk.wav', 'movie_projector.wav', 'ricochet.wav', 'slide_whistle.wav', 'thunk.wav']).iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var i = checkIndexOverflow((tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0));
      ctx.assetLoader.loadSound_61zpoe$(item).onLoaded.plusAssign_qlkmfe$(SoundsState$init$lambda$lambda(this, i));
    }
  };
  SoundsState.prototype.update_kung05$ = function (ctx) {
    if (ctx.keyboard.isDown_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
    var tmp$, tmp$_0;
    var index = 0;
    tmp$ = (new IntRange(Key.NUM_0.ordinal, Key.NUM_9.ordinal)).iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var i = checkIndexOverflow((tmp$_0 = index, index = tmp$_0 + 1 | 0, tmp$_0));
      var tmp$_1;
      var key = Key$values()[item];
      if (ctx.keyboard.isJustPressed_5hom6x$(key)) {
        (tmp$_1 = getOrNull(this.sounds_0, i)) != null ? (tmp$_1.play(), Unit) : null;
      }
    }
  };
  SoundsState.prototype.draw_glqt06$ = function (ctx) {
    ctx.screen.clear_msppnp$(CLEAR_COLOR);
  };
  SoundsState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SoundsState',
    interfaces: [GameState]
  };
  _.SoundsState = SoundsState;
  CLEAR_COLOR = new Color(0, 0, 0);
  Kotlin.defineModule('sounds-common', _);
  return _;
}(typeof this['sounds-common'] === 'undefined' ? {} : this['sounds-common'], kotlin, kross2d);
