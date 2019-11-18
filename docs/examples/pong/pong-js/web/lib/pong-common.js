if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'pong-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'pong-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'pong-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'pong-common'.");
}
if (typeof this['kross2d-ecs'] === 'undefined') {
  throw new Error("Error loading module 'pong-common'. Its dependency 'kross2d-ecs' was not found. Please, check whether 'kross2d-ecs' is loaded prior to 'pong-common'.");
}
this['pong-common'] = function (_, Kotlin, $module$kross2d, $module$kross2d_ecs) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Vec2_init = $module$kross2d.bitspittle.kross2d.core.math.Vec2_init_vux9f0$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Unit = Kotlin.kotlin.Unit;
  var ScopedObserver = $module$kross2d.bitspittle.kross2d.core.event.ScopedObserver;
  var Pt2 = $module$kross2d.bitspittle.kross2d.core.math.Pt2;
  var Pt2_init = $module$kross2d.bitspittle.kross2d.core.math.Pt2_init_vux9f0$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Rect_init = $module$kross2d.bitspittle.kross2d.core.geom.Rect_init_nvqvy9$;
  var Random = Kotlin.kotlin.random.Random;
  var graphics = $module$kross2d.bitspittle.kross2d.core.graphics;
  var World = $module$kross2d_ecs.bitspittle.kross2d.extras.ecs.World;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  var Circle_init = $module$kross2d.bitspittle.kross2d.core.geom.Circle_init;
  var Vec2_init_0 = $module$kross2d.bitspittle.kross2d.core.math.Vec2_init;
  var Component = $module$kross2d_ecs.bitspittle.kross2d.extras.ecs.Component;
  var Rect_init_0 = $module$kross2d.bitspittle.kross2d.core.geom.Rect_init;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var Family = $module$kross2d_ecs.bitspittle.kross2d.extras.ecs.Family;
  var getKClass = Kotlin.getKClass;
  var UpdateSystem = $module$kross2d_ecs.bitspittle.kross2d.extras.ecs.UpdateSystem;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var clamp = $module$kross2d.bitspittle.kross2d.core.math.clamp_wj6e7o$;
  var throwUPAE = Kotlin.throwUPAE;
  var DrawSurface$ImageParams = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.ImageParams;
  var DrawSystem = $module$kross2d_ecs.bitspittle.kross2d.extras.ecs.DrawSystem;
  var DrawSurface$TextParams$Anchor = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams.Anchor;
  var DrawSurface$TextParams = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams;
  Side.prototype = Object.create(Enum.prototype);
  Side.prototype.constructor = Side;
  BallSystem.prototype = Object.create(UpdateSystem.prototype);
  BallSystem.prototype.constructor = BallSystem;
  BounceSystem$Dir.prototype = Object.create(Enum.prototype);
  BounceSystem$Dir.prototype.constructor = BounceSystem$Dir;
  BounceSystem.prototype = Object.create(UpdateSystem.prototype);
  BounceSystem.prototype.constructor = BounceSystem;
  InputSystem.prototype = Object.create(UpdateSystem.prototype);
  InputSystem.prototype.constructor = InputSystem;
  PaddleSystem.prototype = Object.create(UpdateSystem.prototype);
  PaddleSystem.prototype.constructor = PaddleSystem;
  RenderSystem.prototype = Object.create(DrawSystem.prototype);
  RenderSystem.prototype.constructor = RenderSystem;
  ScoreDrawSystem.prototype = Object.create(DrawSystem.prototype);
  ScoreDrawSystem.prototype.constructor = ScoreDrawSystem;
  ScoreUpdateSystem.prototype = Object.create(UpdateSystem.prototype);
  ScoreUpdateSystem.prototype.constructor = ScoreUpdateSystem;
  function PongState() {
    PongState$Companion_getInstance();
    this.world_0 = new World();
    this.scoreBoard_0 = new ScoreBoard();
    this.spritePaddle_0 = null;
    this.spriteBall_0 = null;
  }
  function PongState$Companion() {
    PongState$Companion_instance = this;
    this.ARENA_SIZE = Vec2_init(100, 100);
  }
  PongState$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var PongState$Companion_instance = null;
  function PongState$Companion_getInstance() {
    if (PongState$Companion_instance === null) {
      new PongState$Companion();
    }
    return PongState$Companion_instance;
  }
  function PongState$enter$lambda(this$PongState) {
    return function (it) {
      var tmp$;
      var font = it.derive_mx4ult$(16.0);
      tmp$ = listOf([Side$LEFT_getInstance(), Side$RIGHT_getInstance()]).iterator();
      while (tmp$.hasNext()) {
        var side = tmp$.next();
        var $receiver = this$PongState.world_0.createEntity();
        $receiver.add_qbdilf$(new Score(this$PongState.scoreBoard_0, side));
        $receiver.add_qbdilf$(new Printable(font));
      }
      return Unit;
    };
  }
  function PongState$enter$lambda_0(this$PongState) {
    return function (it) {
      this$PongState.spritePaddle_0 = it.subimage_tu0i02$(Pt2.Companion.ZERO, Vec2_init(4, 16));
      this$PongState.spriteBall_0 = it.subimage_tu0i02$(Pt2_init(4, 0), Vec2_init(4, 4));
      var $receiver = new Paddle(Side$LEFT_getInstance());
      var this$PongState_0 = this$PongState;
      $receiver.shape.size.set_nvqvy9$(ensureNotNull(this$PongState_0.spritePaddle_0).size);
      $receiver.shape.y = (PongState$Companion_getInstance().ARENA_SIZE.y - $receiver.shape.h) / 2.0;
      var $receiver_0 = this$PongState_0.world_0.createEntity();
      $receiver_0.add_qbdilf$($receiver);
      $receiver_0.add_qbdilf$(new Body($receiver.shape));
      $receiver_0.add_qbdilf$(new Renderable(ensureNotNull(this$PongState_0.spritePaddle_0)));
      var $receiver_1 = new Paddle(Side$RIGHT_getInstance());
      var this$PongState_1 = this$PongState;
      $receiver_1.shape.size.set_nvqvy9$(ensureNotNull(this$PongState_1.spritePaddle_0).size);
      $receiver_1.shape.x = PongState$Companion_getInstance().ARENA_SIZE.x - $receiver_1.shape.w;
      $receiver_1.shape.y = (PongState$Companion_getInstance().ARENA_SIZE.y - $receiver_1.shape.h) / 2.0;
      var $receiver_2 = this$PongState_1.world_0.createEntity();
      $receiver_2.add_qbdilf$($receiver_1);
      $receiver_2.add_qbdilf$(new Body($receiver_1.shape));
      $receiver_2.add_qbdilf$(new Renderable(ensureNotNull(this$PongState_1.spritePaddle_0)));
      var $receiver_3 = new Ball();
      var this$PongState_2 = this$PongState;
      $receiver_3.shape.center.set_cz6rql$(Rect_init(PongState$Companion_getInstance().ARENA_SIZE).center);
      $receiver_3.shape.radius = ensureNotNull(this$PongState_2.spriteBall_0).size.y / 2;
      var randomSignX = Random.Default.nextBoolean() ? 1 : -1;
      var randomSignY = Random.Default.nextBoolean() ? 1 : -1;
      $receiver_3.vel.set_nvqvy9$(Vec2_init(75 * randomSignX | 0, 50 * randomSignY | 0));
      var $receiver_4 = this$PongState_2.world_0.createEntity();
      $receiver_4.add_qbdilf$($receiver_3);
      $receiver_4.add_qbdilf$(new Body($receiver_3.shape));
      $receiver_4.add_qbdilf$(new Renderable(ensureNotNull(this$PongState_2.spriteBall_0)));
      return Unit;
    };
  }
  PongState.prototype.enter_ahvl4o$ = function (ctx) {
    ctx.assetLoader.loadFont_rr7ufs$('font/square.ttf').onLoaded.plusAssign_fyy9ck$(new ScopedObserver(ctx.scopes.currState, PongState$enter$lambda(this)));
    ctx.assetLoader.loadImage_rr7ufs$('image/pong.png').onLoaded.plusAssign_fyy9ck$(new ScopedObserver(ctx.scopes.currState, PongState$enter$lambda_0(this)));
    this.world_0.addSystem_46cjsi$(new PaddleSystem(PongState$Companion_getInstance().ARENA_SIZE));
    this.world_0.addSystem_46cjsi$(new BallSystem());
    this.world_0.addSystem_46cjsi$(new InputSystem());
    this.world_0.addSystem_46cjsi$(new BounceSystem(PongState$Companion_getInstance().ARENA_SIZE, ctx.assetLoader.loadSound_rr7ufs$('audio/bounce.wav')));
    this.world_0.addSystem_46cjsi$(new ScoreUpdateSystem(PongState$Companion_getInstance().ARENA_SIZE, this.scoreBoard_0, ctx.assetLoader.loadSound_rr7ufs$('audio/score.wav')));
    this.world_0.addSystem_uk5q8j$(new ScoreDrawSystem(PongState$Companion_getInstance().ARENA_SIZE));
    this.world_0.addSystem_uk5q8j$(new RenderSystem(PongState$Companion_getInstance().ARENA_SIZE));
  };
  PongState.prototype.update_kung05$ = function (ctx) {
    this.world_0.update_kung05$(ctx);
  };
  PongState.prototype.draw_glqt06$ = function (ctx) {
    ctx.screen.clear_cqchof$(graphics.Colors.BLACK);
    this.world_0.draw_glqt06$(ctx);
  };
  PongState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PongState',
    interfaces: [GameState]
  };
  function Ball() {
    this.shape = Circle_init();
    this.vel = Vec2_init_0();
  }
  Ball.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Ball',
    interfaces: [Component]
  };
  function Body(shape) {
    this.shape = shape;
  }
  Body.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Body',
    interfaces: [Component]
  };
  function Paddle(side) {
    this.side = side;
    this.shape = Rect_init_0();
    this.vel = Vec2_init_0();
  }
  Paddle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Paddle',
    interfaces: [Component]
  };
  function Printable(font) {
    this.font = font;
  }
  Printable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Printable',
    interfaces: [Component]
  };
  function Renderable(image) {
    this.image = image;
  }
  Renderable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Renderable',
    interfaces: [Component]
  };
  function Score(scoreBoard, side) {
    this.scoreBoard_0 = scoreBoard;
    this.side = side;
  }
  Object.defineProperty(Score.prototype, 'value', {
    get: function () {
      return this.side === Side$LEFT_getInstance() ? this.scoreBoard_0.left : this.scoreBoard_0.right;
    }
  });
  Score.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Score',
    interfaces: [Component]
  };
  function ScoreBoard() {
    this.left = 0;
    this.right = 0;
  }
  ScoreBoard.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ScoreBoard',
    interfaces: []
  };
  function Side(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Side_initFields() {
    Side_initFields = function () {
    };
    Side$LEFT_instance = new Side('LEFT', 0);
    Side$RIGHT_instance = new Side('RIGHT', 1);
  }
  var Side$LEFT_instance;
  function Side$LEFT_getInstance() {
    Side_initFields();
    return Side$LEFT_instance;
  }
  var Side$RIGHT_instance;
  function Side$RIGHT_getInstance() {
    Side_initFields();
    return Side$RIGHT_instance;
  }
  Side.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Side',
    interfaces: [Enum]
  };
  function Side$values() {
    return [Side$LEFT_getInstance(), Side$RIGHT_getInstance()];
  }
  Side.values = Side$values;
  function Side$valueOf(name) {
    switch (name) {
      case 'LEFT':
        return Side$LEFT_getInstance();
      case 'RIGHT':
        return Side$RIGHT_getInstance();
      default:throwISE('No enum constant objects.Side.' + name);
    }
  }
  Side.valueOf_61zpoe$ = Side$valueOf;
  function BallSystem() {
    UpdateSystem.call(this, Family.Companion.all_ntvadq$([getKClass(Ball)]));
  }
  BallSystem.prototype.update_uy1jsw$ = function (world, ctx, entity) {
    var ball = ensureNotNull(entity.find_kwubw2$(getKClass(Ball)));
    ball.shape.center.plusAssign_nvqvy9$(ball.vel.times_mx4ult$(ctx.timer.lastFrame.secs));
  };
  BallSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BallSystem',
    interfaces: [UpdateSystem]
  };
  function BounceSystem(arenaSize, soundBounce) {
    UpdateSystem.call(this, Family.Companion.all_ntvadq$([getKClass(Ball)]));
    this.arenaSize_0 = arenaSize;
    this.soundBounce_0 = soundBounce;
  }
  function BounceSystem$Dir(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function BounceSystem$Dir_initFields() {
    BounceSystem$Dir_initFields = function () {
    };
    BounceSystem$Dir$HORIZ_instance = new BounceSystem$Dir('HORIZ', 0);
    BounceSystem$Dir$VERT_instance = new BounceSystem$Dir('VERT', 1);
  }
  var BounceSystem$Dir$HORIZ_instance;
  function BounceSystem$Dir$HORIZ_getInstance() {
    BounceSystem$Dir_initFields();
    return BounceSystem$Dir$HORIZ_instance;
  }
  var BounceSystem$Dir$VERT_instance;
  function BounceSystem$Dir$VERT_getInstance() {
    BounceSystem$Dir_initFields();
    return BounceSystem$Dir$VERT_instance;
  }
  BounceSystem$Dir.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Dir',
    interfaces: [Enum]
  };
  function BounceSystem$Dir$values() {
    return [BounceSystem$Dir$HORIZ_getInstance(), BounceSystem$Dir$VERT_getInstance()];
  }
  BounceSystem$Dir.values = BounceSystem$Dir$values;
  function BounceSystem$Dir$valueOf(name) {
    switch (name) {
      case 'HORIZ':
        return BounceSystem$Dir$HORIZ_getInstance();
      case 'VERT':
        return BounceSystem$Dir$VERT_getInstance();
      default:throwISE('No enum constant systems.BounceSystem.Dir.' + name);
    }
  }
  BounceSystem$Dir.valueOf_61zpoe$ = BounceSystem$Dir$valueOf;
  BounceSystem.prototype.update_uy1jsw$ = function (world, ctx, entity) {
    var ball = ensureNotNull(entity.find_kwubw2$(getKClass(Ball)));
    var ballRect = ball.shape.toBoundingRect();
    if (ballRect.y <= 0.0 && ball.vel.y < 0.0 || (ballRect.y2 >= this.arenaSize_0.y && ball.vel.y > 0.0)) {
      this.bounce_0(ball, BounceSystem$Dir$VERT_getInstance());
    }
    var paddles = world.query_qvsabv$(getKClass(Paddle));
    var tmp$;
    tmp$ = paddles.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var paddle = ensureNotNull(element.find_kwubw2$(getKClass(Paddle)));
      if (paddle.shape.intersects_np3yo7$(ballRect)) {
        if (paddle.side === Side$LEFT_getInstance() && ball.vel.x < 0.0 || (paddle.side === Side$RIGHT_getInstance() && ball.vel.x > 0.0)) {
          this.bounce_0(ball, BounceSystem$Dir$HORIZ_getInstance());
        }
      }
    }
  };
  BounceSystem.prototype.bounce_0 = function (ball, dir) {
    var tmp$;
    if (dir === BounceSystem$Dir$HORIZ_getInstance()) {
      ball.vel.x = ball.vel.x * -1.0;
    }
     else if (dir === BounceSystem$Dir$VERT_getInstance()) {
      ball.vel.y = ball.vel.y * -1.0;
    }
    (tmp$ = this.soundBounce_0.data) != null ? tmp$.play() : null;
  };
  BounceSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BounceSystem',
    interfaces: [UpdateSystem]
  };
  var PADDLE_SPEED;
  function InputSystem() {
    UpdateSystem.call(this, Family.Companion.all_ntvadq$([getKClass(Paddle)]));
  }
  InputSystem.prototype.update_uy1jsw$ = function (world, ctx, entity) {
    var paddle = ensureNotNull(entity.find_kwubw2$(getKClass(Paddle)));
    switch (paddle.side.name) {
      case 'LEFT':
        if (ctx.keyboard.isDown_5hom6x$(Key.W))
          paddle.vel.y = -PADDLE_SPEED;
        else if (ctx.keyboard.isDown_5hom6x$(Key.S))
          paddle.vel.y = PADDLE_SPEED;
        else
          paddle.vel.y = 0.0;
        break;
      case 'RIGHT':
        if (ctx.keyboard.isDown_5hom6x$(Key.UP))
          paddle.vel.y = -PADDLE_SPEED;
        else if (ctx.keyboard.isDown_5hom6x$(Key.DOWN))
          paddle.vel.y = PADDLE_SPEED;
        else
          paddle.vel.y = 0.0;
        break;
    }
  };
  InputSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'InputSystem',
    interfaces: [UpdateSystem]
  };
  function PaddleSystem(arenaSize) {
    UpdateSystem.call(this, Family.Companion.all_ntvadq$([getKClass(Paddle)]));
    this.arenaSize_0 = arenaSize;
  }
  PaddleSystem.prototype.update_uy1jsw$ = function (world, ctx, entity) {
    var paddle = ensureNotNull(entity.find_kwubw2$(getKClass(Paddle)));
    paddle.shape.pos.plusAssign_nvqvy9$(paddle.vel.times_mx4ult$(ctx.timer.lastFrame.secs));
    paddle.shape.y = clamp(paddle.shape.y, 0.0, this.arenaSize_0.y - paddle.shape.h);
  };
  PaddleSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PaddleSystem',
    interfaces: [UpdateSystem]
  };
  function RenderSystem(worldSize) {
    DrawSystem.call(this, Family.Companion.all_ntvadq$([getKClass(Renderable), getKClass(Body)]));
    this.worldSize_0 = worldSize;
    this.scale_cj2dxt$_0 = this.scale_cj2dxt$_0;
  }
  Object.defineProperty(RenderSystem.prototype, 'scale_0', {
    get: function () {
      if (this.scale_cj2dxt$_0 == null)
        return throwUPAE('scale');
      return this.scale_cj2dxt$_0;
    },
    set: function (scale) {
      this.scale_cj2dxt$_0 = scale;
    }
  });
  RenderSystem.prototype.beforeDraws_nri52c$ = function (world, ctx) {
    this.scale_0 = ctx.screen.size.div_nvqvy9$(this.worldSize_0);
  };
  RenderSystem.prototype.draw_bg386z$ = function (world, ctx, entity) {
    var image = ensureNotNull(entity.find_kwubw2$(getKClass(Renderable))).image;
    var rect = ensureNotNull(entity.find_kwubw2$(getKClass(Body))).shape.toBoundingRect();
    ctx.screen.drawImage_pq2ml$(image, new DrawSurface$ImageParams(rect.pos.times_nvqvy9$(this.scale_0), rect.size.times_nvqvy9$(this.scale_0)));
  };
  RenderSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RenderSystem',
    interfaces: [DrawSystem]
  };
  function ScoreDrawSystem(worldSize) {
    DrawSystem.call(this, Family.Companion.all_ntvadq$([getKClass(Printable), getKClass(Score)]));
    this.worldSize_0 = worldSize;
    this.scale_4yjbfn$_0 = this.scale_4yjbfn$_0;
    this.xLeft_0 = this.worldSize_0.x * 4.0 / 10.0;
    this.xRight_0 = this.worldSize_0.x * 6.0 / 10.0;
    this.y_0 = this.worldSize_0.y / 8.0;
  }
  Object.defineProperty(ScoreDrawSystem.prototype, 'scale_0', {
    get: function () {
      if (this.scale_4yjbfn$_0 == null)
        return throwUPAE('scale');
      return this.scale_4yjbfn$_0;
    },
    set: function (scale) {
      this.scale_4yjbfn$_0 = scale;
    }
  });
  ScoreDrawSystem.prototype.beforeDraws_nri52c$ = function (world, ctx) {
    this.scale_0 = ctx.screen.size.div_nvqvy9$(this.worldSize_0);
  };
  ScoreDrawSystem.prototype.draw_bg386z$ = function (world, ctx, entity) {
    var printable = ensureNotNull(entity.find_kwubw2$(getKClass(Printable)));
    var score = ensureNotNull(entity.find_kwubw2$(getKClass(Score)));
    var x = score.side === Side$LEFT_getInstance() ? this.xLeft_0 : this.xRight_0;
    ctx.screen.drawText_4p9ijz$(printable.font, score.value.toString(), new DrawSurface$TextParams((new Pt2(x, this.y_0)).times_nvqvy9$(this.scale_0), DrawSurface$TextParams$Anchor.CENTER));
  };
  ScoreDrawSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ScoreDrawSystem',
    interfaces: [DrawSystem]
  };
  function ScoreUpdateSystem(arenaSize, scoreBoard, soundScore) {
    UpdateSystem.call(this, Family.Companion.all_ntvadq$([getKClass(Ball)]));
    this.arenaSize_0 = arenaSize;
    this.scoreBoard_0 = scoreBoard;
    this.soundScore_0 = soundScore;
  }
  ScoreUpdateSystem.prototype.update_uy1jsw$ = function (world, ctx, entity) {
    var tmp$, tmp$_0;
    var ball = ensureNotNull(entity.find_kwubw2$(getKClass(Ball)));
    var ballRect = ball.shape.toBoundingRect();
    if (ballRect.x <= 0.0) {
      var tmp$_1;
      tmp$_1 = this.scoreBoard_0;
      tmp$_1.right = tmp$_1.right + 1 | 0;
      tmp$ = true;
    }
     else if (ballRect.x2 >= this.arenaSize_0.x) {
      var tmp$_2;
      tmp$_2 = this.scoreBoard_0;
      tmp$_2.left = tmp$_2.left + 1 | 0;
      tmp$ = true;
    }
     else
      tmp$ = false;
    var scored = tmp$;
    if (scored) {
      (tmp$_0 = this.soundScore_0.data) != null ? tmp$_0.play() : null;
      ball.vel.x = -ball.vel.x;
      ball.shape.center.x = this.arenaSize_0.x / 2.0;
    }
  };
  ScoreUpdateSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ScoreUpdateSystem',
    interfaces: [UpdateSystem]
  };
  Object.defineProperty(PongState, 'Companion', {
    get: PongState$Companion_getInstance
  });
  _.PongState = PongState;
  var package$components = _.components || (_.components = {});
  package$components.Ball = Ball;
  package$components.Body = Body;
  package$components.Paddle = Paddle;
  package$components.Printable = Printable;
  package$components.Renderable = Renderable;
  package$components.Score = Score;
  var package$objects = _.objects || (_.objects = {});
  package$objects.ScoreBoard = ScoreBoard;
  Object.defineProperty(Side, 'LEFT', {
    get: Side$LEFT_getInstance
  });
  Object.defineProperty(Side, 'RIGHT', {
    get: Side$RIGHT_getInstance
  });
  package$objects.Side = Side;
  $$importsForInline$$['kross2d-ecs'] = $module$kross2d_ecs;
  var package$systems = _.systems || (_.systems = {});
  package$systems.BallSystem = BallSystem;
  Object.defineProperty(BounceSystem$Dir, 'HORIZ', {
    get: BounceSystem$Dir$HORIZ_getInstance
  });
  Object.defineProperty(BounceSystem$Dir, 'VERT', {
    get: BounceSystem$Dir$VERT_getInstance
  });
  BounceSystem.Dir = BounceSystem$Dir;
  package$systems.BounceSystem = BounceSystem;
  package$systems.InputSystem = InputSystem;
  package$systems.PaddleSystem = PaddleSystem;
  package$systems.RenderSystem = RenderSystem;
  package$systems.ScoreDrawSystem = ScoreDrawSystem;
  package$systems.ScoreUpdateSystem = ScoreUpdateSystem;
  PADDLE_SPEED = 75.0;
  Kotlin.defineModule('pong-common', _);
  return _;
}(typeof this['pong-common'] === 'undefined' ? {} : this['pong-common'], kotlin, kross2d, this['kross2d-ecs']);
