if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'manystates-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'manystates-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'manystates-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'manystates-common'.");
}
this['manystates-common'] = function (_, Kotlin, $module$kross2d) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var Unit = Kotlin.kotlin.Unit;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var graphics = $module$kross2d.bitspittle.kross2d.core.graphics;
  var trimIndent = Kotlin.kotlin.text.trimIndent_pdl1vz$;
  var Rect_init = $module$kross2d.bitspittle.kross2d.core.geom.Rect_init_nvqvy9$;
  var DrawSurface$TextParams$Anchor = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams.Anchor;
  var DrawSurface$TextParams = $module$kross2d.bitspittle.kross2d.engine.graphics.DrawSurface.TextParams;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  var throwUPAE = Kotlin.throwUPAE;
  var setParent = $module$kross2d.bitspittle.kross2d.core.memory.setParent_wixrfn$;
  function EndState(font) {
    this.font_0 = font;
  }
  function EndState$enter$lambda() {
    println('EndState is no longer active');
    return Unit;
  }
  var Disposable = $module$kross2d.bitspittle.kross2d.core.memory.Disposable;
  disposable$ObjectLiteral.prototype = Object.create(Disposable.prototype);
  disposable$ObjectLiteral.prototype.constructor = disposable$ObjectLiteral;
  function disposable$ObjectLiteral(closure$block, parent) {
    this.closure$block = closure$block;
    Disposable.call(this, parent);
  }
  disposable$ObjectLiteral.prototype.onDisposed = function () {
    this.closure$block();
  };
  disposable$ObjectLiteral.$metadata$ = {kind: Kind_CLASS, interfaces: [Disposable]};
  EndState.prototype.enter_ahvl4o$ = function (ctx) {
    new disposable$ObjectLiteral(EndState$enter$lambda, ctx.scopes.currState);
  };
  EndState.prototype.update_kung05$ = function (ctx) {
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.ESC)) {
      ctx.app.popState();
    }
  };
  EndState.prototype.draw_glqt06$ = function (ctx) {
    var tmp$;
    ctx.screen.clear_cqchof$(graphics.Colors.BLACK);
    if ((tmp$ = this.font_0.data) != null) {
      ctx.screen.drawText_4p9ijz$(tmp$, trimIndent('\n                END STATE\n\n                press ESC to return to INITIAL STATE\n                '), new DrawSurface$TextParams(Rect_init(ctx.screen.size).center, DrawSurface$TextParams$Anchor.BOTTOM));
    }
  };
  EndState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EndState',
    interfaces: [GameState]
  };
  disposable$ObjectLiteral_0.prototype = Object.create(Disposable.prototype);
  disposable$ObjectLiteral_0.prototype.constructor = disposable$ObjectLiteral_0;
  function disposable$ObjectLiteral_0(closure$block, parent) {
    this.closure$block = closure$block;
    Disposable.call(this, parent);
  }
  disposable$ObjectLiteral_0.prototype.onDisposed = function () {
    this.closure$block();
  };
  disposable$ObjectLiteral_0.$metadata$ = {kind: Kind_CLASS, interfaces: [Disposable]};
  function InitialState() {
    this.font_qnkexi$_0 = this.font_qnkexi$_0;
    this.fontDisposedListener_0 = new disposable$ObjectLiteral_0(InitialState$fontDisposedListener$lambda);
  }
  Object.defineProperty(InitialState.prototype, 'font_0', {
    get: function () {
      if (this.font_qnkexi$_0 == null)
        return throwUPAE('font');
      return this.font_qnkexi$_0;
    },
    set: function (font) {
      this.font_qnkexi$_0 = font;
    }
  });
  function InitialState$enter$lambda() {
    println('InitialState is no longer active');
    return Unit;
  }
  disposable$ObjectLiteral_1.prototype = Object.create(Disposable.prototype);
  disposable$ObjectLiteral_1.prototype.constructor = disposable$ObjectLiteral_1;
  function disposable$ObjectLiteral_1(closure$block, parent) {
    this.closure$block = closure$block;
    Disposable.call(this, parent);
  }
  disposable$ObjectLiteral_1.prototype.onDisposed = function () {
    this.closure$block();
  };
  disposable$ObjectLiteral_1.$metadata$ = {kind: Kind_CLASS, interfaces: [Disposable]};
  InitialState.prototype.enter_ahvl4o$ = function (ctx) {
    this.font_0 = ctx.assetLoader.loadFont_rr7ufs$('square.ttf', ctx.scopes.app);
    setParent(this.fontDisposedListener_0, this.font_0);
    new disposable$ObjectLiteral_1(InitialState$enter$lambda, ctx.scopes.currState);
  };
  InitialState.prototype.update_kung05$ = function (ctx) {
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
     else if (ctx.keyboard.isJustPressed_5hom6x$(Key.RIGHT)) {
      ctx.app.pushState_qsnwm5$(new MiddleState(this.font_0));
    }
  };
  InitialState.prototype.draw_glqt06$ = function (ctx) {
    var tmp$;
    ctx.screen.clear_cqchof$(graphics.Colors.BLACK);
    if ((tmp$ = this.font_0.data) != null) {
      ctx.screen.drawText_4p9ijz$(tmp$, trimIndent('\n                INITIAL STATE\n\n                press RIGHT ARROW to go forward\n                press ESC to quit\n                '), new DrawSurface$TextParams(Rect_init(ctx.screen.size).center, DrawSurface$TextParams$Anchor.BOTTOM));
    }
  };
  function InitialState$fontDisposedListener$lambda() {
    println('Font was disposed');
    return Unit;
  }
  InitialState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'InitialState',
    interfaces: [GameState]
  };
  function MiddleState(font) {
    this.font_0 = font;
  }
  function MiddleState$enter$lambda() {
    println('MiddleState is no longer active');
    return Unit;
  }
  disposable$ObjectLiteral_2.prototype = Object.create(Disposable.prototype);
  disposable$ObjectLiteral_2.prototype.constructor = disposable$ObjectLiteral_2;
  function disposable$ObjectLiteral_2(closure$block, parent) {
    this.closure$block = closure$block;
    Disposable.call(this, parent);
  }
  disposable$ObjectLiteral_2.prototype.onDisposed = function () {
    this.closure$block();
  };
  disposable$ObjectLiteral_2.$metadata$ = {kind: Kind_CLASS, interfaces: [Disposable]};
  MiddleState.prototype.enter_ahvl4o$ = function (ctx) {
    new disposable$ObjectLiteral_2(MiddleState$enter$lambda, ctx.scopes.currState);
  };
  MiddleState.prototype.update_kung05$ = function (ctx) {
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.ESC)) {
      ctx.app.popState();
    }
     else if (ctx.keyboard.isJustPressed_5hom6x$(Key.RIGHT)) {
      ctx.app.changeState_qsnwm5$(new EndState(this.font_0));
    }
  };
  MiddleState.prototype.draw_glqt06$ = function (ctx) {
    var tmp$;
    ctx.screen.clear_cqchof$(graphics.Colors.BLACK);
    if ((tmp$ = this.font_0.data) != null) {
      ctx.screen.drawText_4p9ijz$(tmp$, trimIndent('\n                MIDDLE STATE\n\n                press RIGHT ARROW to go forward\n                press ESC to return to INITIAL STATE\n                '), new DrawSurface$TextParams(Rect_init(ctx.screen.size).center, DrawSurface$TextParams$Anchor.BOTTOM));
    }
  };
  MiddleState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MiddleState',
    interfaces: [GameState]
  };
  $$importsForInline$$.kross2d = $module$kross2d;
  _.EndState = EndState;
  _.InitialState = InitialState;
  _.MiddleState = MiddleState;
  Kotlin.defineModule('manystates-common', _);
  return _;
}(typeof this['manystates-common'] === 'undefined' ? {} : this['manystates-common'], kotlin, kross2d);
