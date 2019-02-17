if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'sprite-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'sprite-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'sprite-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'sprite-common'.");
}
this['sprite-common'] = function (_, Kotlin, $module$kross2d) {
  'use strict';
  var Color = $module$kross2d.bitspittle.kross2d.core.graphics.Color;
  var Enum = Kotlin.kotlin.Enum;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var throwISE = Kotlin.throwISE;
  var Vec2_init = $module$kross2d.bitspittle.kross2d.core.math.Vec2_init_vux9f0$;
  var Duration = $module$kross2d.bitspittle.kross2d.core.time.Duration;
  var L300 = Kotlin.Long.fromInt(300);
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_qfcya0$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Pt2 = $module$kross2d.bitspittle.kross2d.core.math.Pt2;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var clamp = $module$kross2d.bitspittle.kross2d.core.math.clamp_wj6e7o$;
  var getValue = Kotlin.kotlin.collections.getValue_t9ocha$;
  var DrawSurface$DrawParams = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.DrawParams;
  var Pt2_init = $module$kross2d.bitspittle.kross2d.core.math.Pt2_init;
  var Vec2_init_0 = $module$kross2d.bitspittle.kross2d.core.math.Vec2_init;
  var throwUPAE = Kotlin.throwUPAE;
  var ensureNotNull = Kotlin.ensureNotNull;
  var numberToInt = Kotlin.numberToInt;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  SpriteState$Dir.prototype = Object.create(Enum.prototype);
  SpriteState$Dir.prototype.constructor = SpriteState$Dir;
  SpriteState.prototype = Object.create(GameState.prototype);
  SpriteState.prototype.constructor = SpriteState;
  var CLEAR_COLOR;
  function SpriteState() {
    GameState.call(this);
    this.playerSheet_kfszcy$_0 = this.playerSheet_kfszcy$_0;
    this.grassTile_36n9h4$_0 = this.grassTile_36n9h4$_0;
    this.player_5pmtvt$_0 = this.player_5pmtvt$_0;
  }
  function SpriteState$Dir(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function SpriteState$Dir_initFields() {
    SpriteState$Dir_initFields = function () {
    };
    SpriteState$Dir$N_instance = new SpriteState$Dir('N', 0);
    SpriteState$Dir$S_instance = new SpriteState$Dir('S', 1);
    SpriteState$Dir$E_instance = new SpriteState$Dir('E', 2);
    SpriteState$Dir$W_instance = new SpriteState$Dir('W', 3);
  }
  var SpriteState$Dir$N_instance;
  function SpriteState$Dir$N_getInstance() {
    SpriteState$Dir_initFields();
    return SpriteState$Dir$N_instance;
  }
  var SpriteState$Dir$S_instance;
  function SpriteState$Dir$S_getInstance() {
    SpriteState$Dir_initFields();
    return SpriteState$Dir$S_instance;
  }
  var SpriteState$Dir$E_instance;
  function SpriteState$Dir$E_getInstance() {
    SpriteState$Dir_initFields();
    return SpriteState$Dir$E_instance;
  }
  var SpriteState$Dir$W_instance;
  function SpriteState$Dir$W_getInstance() {
    SpriteState$Dir_initFields();
    return SpriteState$Dir$W_instance;
  }
  SpriteState$Dir.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Dir',
    interfaces: [Enum]
  };
  function SpriteState$Dir$values() {
    return [SpriteState$Dir$N_getInstance(), SpriteState$Dir$S_getInstance(), SpriteState$Dir$E_getInstance(), SpriteState$Dir$W_getInstance()];
  }
  SpriteState$Dir.values = SpriteState$Dir$values;
  function SpriteState$Dir$valueOf(name) {
    switch (name) {
      case 'N':
        return SpriteState$Dir$N_getInstance();
      case 'S':
        return SpriteState$Dir$S_getInstance();
      case 'E':
        return SpriteState$Dir$E_getInstance();
      case 'W':
        return SpriteState$Dir$W_getInstance();
      default:throwISE('No enum constant SpriteState.Dir.' + name);
    }
  }
  SpriteState$Dir.valueOf_61zpoe$ = SpriteState$Dir$valueOf;
  function SpriteState$Player(playerSheet) {
    SpriteState$Player$Companion_getInstance();
    this.playerSheet_0 = playerSheet;
    this.pos = Pt2_init();
    this.vel = Vec2_init_0();
    this.elapsed = Duration.Companion.zero();
    this.facing = SpriteState$Dir$S_getInstance();
    this.animCycle = 0;
  }
  function SpriteState$Player$Companion() {
    SpriteState$Player$Companion_instance = this;
    this.PLAYER_TILE_SIZE_0 = Vec2_init(16, 16);
    this.PLAYER_DRAW_SIZE_0 = this.PLAYER_TILE_SIZE_0.times_mx4ult$(2.0);
    this.FRAME_DURATION = Duration.Companion.ofMillis_s8cxhz$(L300);
    this.FACING_TO_TILE_X = mapOf([to(SpriteState$Dir$N_getInstance(), 2), to(SpriteState$Dir$S_getInstance(), 0), to(SpriteState$Dir$E_getInstance(), 4), to(SpriteState$Dir$W_getInstance(), 6)]);
  }
  SpriteState$Player$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var SpriteState$Player$Companion_instance = null;
  function SpriteState$Player$Companion_getInstance() {
    if (SpriteState$Player$Companion_instance === null) {
      new SpriteState$Player$Companion();
    }
    return SpriteState$Player$Companion_instance;
  }
  SpriteState$Player.prototype.update_kung05$ = function (ctx) {
    this.elapsed.plusAssign_evd4je$(ctx.timer.lastFrameDuration);
    while (this.elapsed.compareTo_11rb$(SpriteState$Player$Companion_getInstance().FRAME_DURATION) > 0) {
      this.animCycle = (this.animCycle + 1 | 0) % 2;
      this.elapsed.minusAssign_evd4je$(SpriteState$Player$Companion_getInstance().FRAME_DURATION);
    }
    this.vel.set_cz6rql$(Pt2.Companion.ZERO);
    if (ctx.keyboard.isDown_5hom6x$(Key.UP))
      this.vel.y = this.vel.y - 1.0;
    if (ctx.keyboard.isDown_5hom6x$(Key.DOWN))
      this.vel.y = this.vel.y + 1.0;
    if (ctx.keyboard.isDown_5hom6x$(Key.LEFT))
      this.vel.x = this.vel.x - 1.0;
    if (ctx.keyboard.isDown_5hom6x$(Key.RIGHT))
      this.vel.x = this.vel.x + 1.0;
    this.vel.normalize();
    this.vel.timesAssign_mx4ult$(200.0 * ctx.timer.lastFrameDuration.secs);
    if (this.vel.x > 0)
      this.facing = SpriteState$Dir$E_getInstance();
    else if (this.vel.x < 0)
      this.facing = SpriteState$Dir$W_getInstance();
    else if (this.vel.y < 0)
      this.facing = SpriteState$Dir$N_getInstance();
    else if (this.vel.y > 0)
      this.facing = SpriteState$Dir$S_getInstance();
    this.pos.plusAssign_nvqvy9$(this.vel);
    this.pos.x = clamp(this.pos.x, 0.0, ctx.screen.size.x - SpriteState$Player$Companion_getInstance().PLAYER_DRAW_SIZE_0.x);
    this.pos.y = clamp(this.pos.y, 0.0, ctx.screen.size.y - SpriteState$Player$Companion_getInstance().PLAYER_DRAW_SIZE_0.y);
  };
  SpriteState$Player.prototype.draw_glqt06$ = function (ctx) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var tileX = getValue(SpriteState$Player$Companion_getInstance().FACING_TO_TILE_X, this.facing) + this.animCycle | 0;
    var src = new Pt2(tileX * SpriteState$Player$Companion_getInstance().PLAYER_TILE_SIZE_0.x, 0.0);
    tmp$_2 = ctx.screen;
    tmp$ = this.playerSheet_0;
    tmp$_0 = this.pos;
    tmp$_1 = SpriteState$Player$Companion_getInstance().PLAYER_TILE_SIZE_0;
    tmp$_2.draw_ky71b2$(tmp$, new DrawSurface$DrawParams(tmp$_0, src, SpriteState$Player$Companion_getInstance().PLAYER_DRAW_SIZE_0, tmp$_1));
  };
  SpriteState$Player.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Player',
    interfaces: []
  };
  Object.defineProperty(SpriteState.prototype, 'playerSheet_0', {
    get: function () {
      if (this.playerSheet_kfszcy$_0 == null)
        return throwUPAE('playerSheet');
      return this.playerSheet_kfszcy$_0;
    },
    set: function (playerSheet) {
      this.playerSheet_kfszcy$_0 = playerSheet;
    }
  });
  Object.defineProperty(SpriteState.prototype, 'grassTile_0', {
    get: function () {
      if (this.grassTile_36n9h4$_0 == null)
        return throwUPAE('grassTile');
      return this.grassTile_36n9h4$_0;
    },
    set: function (grassTile) {
      this.grassTile_36n9h4$_0 = grassTile;
    }
  });
  Object.defineProperty(SpriteState.prototype, 'player_0', {
    get: function () {
      if (this.player_5pmtvt$_0 == null)
        return throwUPAE('player');
      return this.player_5pmtvt$_0;
    },
    set: function (player) {
      this.player_5pmtvt$_0 = player;
    }
  });
  SpriteState.prototype.init_jezbry$ = function (ctx) {
    this.grassTile_0 = ensureNotNull(ctx.assetLoader.loadImage_61zpoe$('grass.png'));
    this.playerSheet_0 = ensureNotNull(ctx.assetLoader.loadImage_61zpoe$('player.png'));
    this.player_0 = new SpriteState$Player(this.playerSheet_0);
  };
  SpriteState.prototype.update_kung05$ = function (ctx) {
    if (ctx.keyboard.isDown_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
    this.player_0.update_kung05$(ctx);
  };
  SpriteState.prototype.draw_glqt06$ = function (ctx) {
    var tmp$, tmp$_0;
    ctx.screen.clear_msppnp$(CLEAR_COLOR);
    var numTiles = ctx.screen.size.div_nvqvy9$(this.grassTile_0.size);
    tmp$ = numberToInt(numTiles.x);
    for (var i = 0; i <= tmp$; i++) {
      tmp$_0 = numberToInt(numTiles.y);
      for (var j = 0; j <= tmp$_0; j++) {
        ctx.screen.draw_ky71b2$(this.grassTile_0, new DrawSurface$DrawParams(new Pt2(this.grassTile_0.size.x * i, this.grassTile_0.size.y * j)));
      }
    }
    this.player_0.draw_glqt06$(ctx);
  };
  SpriteState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SpriteState',
    interfaces: [GameState]
  };
  Object.defineProperty(SpriteState$Dir, 'N', {
    get: SpriteState$Dir$N_getInstance
  });
  Object.defineProperty(SpriteState$Dir, 'S', {
    get: SpriteState$Dir$S_getInstance
  });
  Object.defineProperty(SpriteState$Dir, 'E', {
    get: SpriteState$Dir$E_getInstance
  });
  Object.defineProperty(SpriteState$Dir, 'W', {
    get: SpriteState$Dir$W_getInstance
  });
  SpriteState.Dir = SpriteState$Dir;
  _.SpriteState = SpriteState;
  CLEAR_COLOR = new Color(0, 0, 0);
  Kotlin.defineModule('sprite-common', _);
  return _;
}(typeof this['sprite-common'] === 'undefined' ? {} : this['sprite-common'], kotlin, kross2d);
