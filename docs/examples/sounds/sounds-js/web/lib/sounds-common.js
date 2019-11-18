if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'sounds-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'sounds-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'sounds-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'sounds-common'.");
}
this['sounds-common'] = function (_, Kotlin, $module$kross2d) {
  'use strict';
  var trimIndent = Kotlin.kotlin.text.trimIndent_pdl1vz$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var throwUPAE = Kotlin.throwUPAE;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Unit = Kotlin.kotlin.Unit;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var IntRange = Kotlin.kotlin.ranges.IntRange;
  var Key$values = $module$kross2d.bitspittle.kross2d.engine.input.Key.values;
  var graphics = $module$kross2d.bitspittle.kross2d.core.graphics;
  var Rect_init = $module$kross2d.bitspittle.kross2d.core.geom.Rect_init_nvqvy9$;
  var DrawSurface$TextParams$Anchor = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams.Anchor;
  var DrawSurface$TextParams = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams;
  var Pt2_init = $module$kross2d.bitspittle.kross2d.core.math.Pt2_init_vux9f0$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  function SoundsState() {
    SoundsState$Companion_getInstance();
    this.sounds_8bos8b$_0 = this.sounds_8bos8b$_0;
    this.music_j2383u$_0 = this.music_j2383u$_0;
    this.font_0 = null;
    this.fontLarge_0 = null;
    this.globallyPaused_0 = false;
  }
  function SoundsState$Companion() {
    SoundsState$Companion_instance = this;
    this.STR_PAUSED = 'PAUSED';
    this.STR_MESSAGE = trimIndent('\n                press 0-9 to play sounds\n                press SPACE to toggle pause\n                press ESC to quit\n            ');
  }
  SoundsState$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SoundsState$Companion_instance = null;
  function SoundsState$Companion_getInstance() {
    if (SoundsState$Companion_instance === null) {
      new SoundsState$Companion();
    }
    return SoundsState$Companion_instance;
  }
  Object.defineProperty(SoundsState.prototype, 'sounds_0', {
    get: function () {
      if (this.sounds_8bos8b$_0 == null)
        return throwUPAE('sounds');
      return this.sounds_8bos8b$_0;
    },
    set: function (sounds) {
      this.sounds_8bos8b$_0 = sounds;
    }
  });
  Object.defineProperty(SoundsState.prototype, 'music_0', {
    get: function () {
      if (this.music_j2383u$_0 == null)
        return throwUPAE('music');
      return this.music_j2383u$_0;
    },
    set: function (music) {
      this.music_j2383u$_0 = music;
    }
  });
  function SoundsState$enter$lambda(this$SoundsState) {
    return function (it) {
      this$SoundsState.font_0 = it;
      this$SoundsState.fontLarge_0 = it.derive_mx4ult$(24.0);
      return Unit;
    };
  }
  function SoundsState$enter$lambda_0(it) {
    it.play();
    return Unit;
  }
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  SoundsState.prototype.enter_ahvl4o$ = function (ctx) {
    var $receiver = listOf(['boing.wav', 'boxing_bell.wav', 'chainsaw.wav', 'creak.wav', 'doorbell.wav', 'honk.wav', 'movie_projector.wav', 'ricochet.wav', 'slide_whistle.wav', 'thunk.wav']);
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(ctx.assetLoader.loadSound_rr7ufs$(item));
    }
    this.sounds_0 = destination;
    ctx.assetLoader.loadFont_rr7ufs$('square.ttf').onLoaded.plusAssign_qlkmfe$(SoundsState$enter$lambda(this));
    this.music_0 = ctx.assetLoader.loadMusic_rr7ufs$('battle.ogg');
    this.music_0.onLoaded.plusAssign_qlkmfe$(SoundsState$enter$lambda_0);
  };
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var wrapFunction = Kotlin.wrapFunction;
  var mapNotNullTo$lambda = wrapFunction(function () {
    return function (closure$transform, closure$destination) {
      return function (element) {
        var tmp$;
        if ((tmp$ = closure$transform(element)) != null) {
          closure$destination.add_11rb$(tmp$);
        }
        return Unit;
      };
    };
  });
  var checkIndexOverflow = Kotlin.kotlin.collections.checkIndexOverflow_za3lpa$;
  SoundsState.prototype.update_kung05$ = function (ctx) {
    var tmp$;
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.SPACE)) {
      this.globallyPaused_0 = !this.globallyPaused_0;
      var $receiver = this.sounds_0;
      var destination = ArrayList_init_0();
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        var tmp$_0_0;
        if ((tmp$_0_0 = element.data) != null) {
          destination.add_11rb$(tmp$_0_0);
        }
      }
      var tmp$_1;
      tmp$_1 = destination.iterator();
      while (tmp$_1.hasNext()) {
        var element_0 = tmp$_1.next();
        if (this.globallyPaused_0)
          element_0.pause_8wv9oi$();
        else
          element_0.resume_8wv9oi$();
      }
      if ((tmp$ = this.music_0.data) != null) {
        if (this.globallyPaused_0)
          tmp$.pause();
        else
          tmp$.resume();
      }
    }
    if (!this.globallyPaused_0) {
      var tmp$_2, tmp$_0_1;
      var index = 0;
      tmp$_2 = (new IntRange(Key.NUM_0.ordinal, Key.NUM_9.ordinal)).iterator();
      while (tmp$_2.hasNext()) {
        var item = tmp$_2.next();
        var i = checkIndexOverflow((tmp$_0_1 = index, index = tmp$_0_1 + 1 | 0, tmp$_0_1));
        var tmp$_3;
        var key = Key$values()[item];
        if (ctx.keyboard.isJustPressed_5hom6x$(key)) {
          (tmp$_3 = this.sounds_0.get_za3lpa$(i).data) != null ? tmp$_3.play() : null;
        }
      }
    }
  };
  SoundsState.prototype.draw_glqt06$ = function (ctx) {
    var tmp$, tmp$_0;
    ctx.screen.clear_cqchof$(graphics.Colors.BLACK);
    if (this.globallyPaused_0) {
      if ((tmp$ = this.fontLarge_0) != null) {
        ctx.screen.drawText_4p9ijz$(tmp$, SoundsState$Companion_getInstance().STR_PAUSED, new DrawSurface$TextParams(Rect_init(ctx.screen.size).center, DrawSurface$TextParams$Anchor.CENTER));
      }
    }
     else {
      if ((tmp$_0 = this.font_0) != null) {
        ctx.screen.drawText_4p9ijz$(tmp$_0, SoundsState$Companion_getInstance().STR_MESSAGE, new DrawSurface$TextParams(Pt2_init(10, 10), void 0, void 0, tmp$_0.size * 0.5));
      }
    }
  };
  SoundsState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SoundsState',
    interfaces: [GameState]
  };
  Object.defineProperty(SoundsState, 'Companion', {
    get: SoundsState$Companion_getInstance
  });
  _.SoundsState = SoundsState;
  Kotlin.defineModule('sounds-common', _);
  return _;
}(typeof this['sounds-common'] === 'undefined' ? {} : this['sounds-common'], kotlin, kross2d);
