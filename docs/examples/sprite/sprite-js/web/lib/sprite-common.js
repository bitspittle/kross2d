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
  var Duration = $module$kross2d.bitspittle.kross2d.core.time.Duration;
  var L300 = Kotlin.Long.fromInt(300);
  var Anim_init = $module$kross2d.bitspittle.kross2d.extras.anim.Anim_init_21rvlz$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var mapOf = Kotlin.kotlin.collections.mapOf_qfcya0$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Rect_init = $module$kross2d.bitspittle.kross2d.core.geom.Rect_init_nvqvy9$;
  var centerIn = $module$kross2d.bitspittle.kross2d.core.geom.centerIn_exd3ih$;
  var Pt2 = $module$kross2d.bitspittle.kross2d.core.math.Pt2;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var getValue = Kotlin.kotlin.collections.getValue_t9ocha$;
  var Unit = Kotlin.kotlin.Unit;
  var clamp = $module$kross2d.bitspittle.kross2d.core.math.clamp_wj6e7o$;
  var DrawSurface$ImageParams = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams;
  var Pt2_init = $module$kross2d.bitspittle.kross2d.core.math.Pt2_init;
  var Vec2_init = $module$kross2d.bitspittle.kross2d.core.math.Vec2_init;
  var throwUPAE = Kotlin.throwUPAE;
  var Vec2_init_0 = $module$kross2d.bitspittle.kross2d.core.math.Vec2_init_vux9f0$;
  var Tiles = $module$kross2d.bitspittle.kross2d.extras.graphics.Tiles;
  var numberToInt = Kotlin.numberToInt;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  SpriteState$Dir.prototype = Object.create(Enum.prototype);
  SpriteState$Dir.prototype.constructor = SpriteState$Dir;
  var CLEAR_COLOR;
  function SpriteState() {
    this.grassAsset_iuoqji$_0 = this.grassAsset_iuoqji$_0;
    this.player_0 = null;
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
  function SpriteState$Player(playerTiles) {
    SpriteState$Player$Companion_getInstance();
    this.playerTiles_0 = playerTiles;
    this.drawSize_0 = this.playerTiles_0.tileSize.times_mx4ult$(2.0);
    this.pos_0 = Pt2_init();
    this.vel_0 = Vec2_init();
    this.currAnim_0 = getValue(SpriteState$Player$Companion_getInstance().FACING_TO_ANIM, SpriteState$Dir$S_getInstance());
  }
  function SpriteState$Player$Companion() {
    SpriteState$Player$Companion_instance = this;
    this.FRAME_DURATION = Duration.Companion.ofMillis_s8cxhz$(L300);
    this.FACING_TO_ANIM = mapOf([to(SpriteState$Dir$N_getInstance(), Anim_init(this.FRAME_DURATION, [2, 3])), to(SpriteState$Dir$S_getInstance(), Anim_init(this.FRAME_DURATION, [0, 1])), to(SpriteState$Dir$E_getInstance(), Anim_init(this.FRAME_DURATION, [4, 5])), to(SpriteState$Dir$W_getInstance(), Anim_init(this.FRAME_DURATION, [6, 7]))]);
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
  SpriteState$Player.prototype.init_ahvl4o$ = function (ctx) {
    this.pos_0.set_cz6rql$(centerIn(this.drawSize_0, Rect_init(ctx.screen.size)));
  };
  SpriteState$Player.prototype.update_kung05$ = function (ctx) {
    var tmp$;
    this.currAnim_0.elapse_evd4je$(ctx.timer.lastFrame);
    this.vel_0.set_cz6rql$(Pt2.Companion.ZERO);
    if (ctx.keyboard.isDown_5hom6x$(Key.UP))
      this.vel_0.y = this.vel_0.y - 1.0;
    if (ctx.keyboard.isDown_5hom6x$(Key.DOWN))
      this.vel_0.y = this.vel_0.y + 1.0;
    if (ctx.keyboard.isDown_5hom6x$(Key.LEFT))
      this.vel_0.x = this.vel_0.x - 1.0;
    if (ctx.keyboard.isDown_5hom6x$(Key.RIGHT))
      this.vel_0.x = this.vel_0.x + 1.0;
    this.vel_0.normalize();
    this.vel_0.timesAssign_mx4ult$(200.0 * ctx.timer.lastFrame.secs);
    if (this.vel_0.x > 0)
      tmp$ = SpriteState$Dir$E_getInstance();
    else if (this.vel_0.x < 0)
      tmp$ = SpriteState$Dir$W_getInstance();
    else if (this.vel_0.y < 0)
      tmp$ = SpriteState$Dir$N_getInstance();
    else if (this.vel_0.y > 0)
      tmp$ = SpriteState$Dir$S_getInstance();
    else
      tmp$ = null;
    if (tmp$ != null) {
      var facing = tmp$;
      this.currAnim_0 = getValue(SpriteState$Player$Companion_getInstance().FACING_TO_ANIM, facing);
    }
    var bounds = ctx.screen.size.minus_nvqvy9$(this.drawSize_0);
    this.pos_0.plusAssign_nvqvy9$(this.vel_0);
    this.pos_0.x = clamp(this.pos_0.x, 0.0, bounds.x);
    this.pos_0.y = clamp(this.pos_0.y, 0.0, bounds.y);
  };
  SpriteState$Player.prototype.draw_glqt06$ = function (ctx) {
    var tileX = this.currAnim_0.value;
    ctx.screen.drawImage_pq2ml$(this.playerTiles_0.getTile_vux9f0$(tileX, 0), new DrawSurface$ImageParams(this.pos_0, this.drawSize_0));
  };
  SpriteState$Player.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Player',
    interfaces: []
  };
  Object.defineProperty(SpriteState.prototype, 'grassAsset_0', {
    get: function () {
      if (this.grassAsset_iuoqji$_0 == null)
        return throwUPAE('grassAsset');
      return this.grassAsset_iuoqji$_0;
    },
    set: function (grassAsset) {
      this.grassAsset_iuoqji$_0 = grassAsset;
    }
  });
  function SpriteState$enter$lambda(closure$ctx, this$SpriteState) {
    return function (image) {
      var tmp$ = this$SpriteState;
      var $receiver = new SpriteState$Player(new Tiles(image, Vec2_init_0(16, 16)));
      $receiver.init_ahvl4o$(closure$ctx);
      tmp$.player_0 = $receiver;
      return Unit;
    };
  }
  SpriteState.prototype.enter_ahvl4o$ = function (ctx) {
    this.grassAsset_0 = ctx.assetLoader.loadImage_rr7ufs$('grass.png');
    ctx.assetLoader.loadImage_rr7ufs$('player.png').onLoaded.plusAssign_qlkmfe$(SpriteState$enter$lambda(ctx, this));
  };
  SpriteState.prototype.update_kung05$ = function (ctx) {
    var tmp$;
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
    (tmp$ = this.player_0) != null ? (tmp$.update_kung05$(ctx), Unit) : null;
  };
  SpriteState.prototype.draw_glqt06$ = function (ctx) {
    var tmp$, tmp$_0;
    ctx.screen.clear_cqchof$(CLEAR_COLOR);
    if ((tmp$ = this.grassAsset_0.data) != null) {
      var tmp$_1, tmp$_2;
      var numTiles = ctx.screen.size.div_nvqvy9$(tmp$.size);
      tmp$_1 = numberToInt(numTiles.x);
      for (var i = 0; i <= tmp$_1; i++) {
        tmp$_2 = numberToInt(numTiles.y);
        for (var j = 0; j <= tmp$_2; j++) {
          ctx.screen.drawImage_pq2ml$(tmp$, new DrawSurface$ImageParams(new Pt2(tmp$.size.x * i, tmp$.size.y * j)));
        }
      }
    }
    (tmp$_0 = this.player_0) != null ? (tmp$_0.draw_glqt06$(ctx), Unit) : null;
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
