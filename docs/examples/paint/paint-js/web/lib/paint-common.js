if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'paint-common'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'paint-common'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'paint-common'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'paint-common'.");
}
this['paint-common'] = function (_, Kotlin, $module$kross2d) {
  'use strict';
  var graphics = $module$kross2d.bitspittle.kross2d.core.graphics;
  var Key = $module$kross2d.bitspittle.kross2d.engine.input.Key;
  var Button = $module$kross2d.bitspittle.kross2d.engine.input.Button;
  var last = Kotlin.kotlin.collections.last_2p1efm$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var lastOrNull = Kotlin.kotlin.collections.lastOrNull_2p1efm$;
  var Pt2_init = $module$kross2d.bitspittle.kross2d.core.math.Pt2_init_cz6rql$;
  var asSequence = Kotlin.kotlin.collections.asSequence_7wnvza$;
  var filter = Kotlin.kotlin.sequences.filter_euau3h$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var GameState = $module$kross2d.bitspittle.kross2d.engine.GameState;
  var CLEAR_COLOR;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  function PaintState() {
    this.paths_0 = ArrayList_init();
    this.currPath_0 = null;
  }
  PaintState.prototype.update_kung05$ = function (ctx) {
    var tmp$, tmp$_0;
    if (ctx.keyboard.isJustPressed_5hom6x$(Key.ESC)) {
      ctx.app.quit();
    }
     else if (ctx.keyboard.isJustPressed_5hom6x$(Key.BACKSPACE)) {
      this.paths_0.clear();
    }
    if (ctx.mouse.isDown_mrmd4$(Button.LEFT)) {
      if (this.currPath_0 == null) {
        this.paths_0.add_11rb$(ArrayList_init());
        this.currPath_0 = last(this.paths_0);
      }
      var redundantPoint = (tmp$_0 = (tmp$ = lastOrNull(ensureNotNull(this.currPath_0))) != null ? tmp$.equals(ctx.mouse.pos) : null) != null ? tmp$_0 : false;
      if (!redundantPoint) {
        ensureNotNull(this.currPath_0).add_11rb$(Pt2_init(ctx.mouse.pos));
      }
    }
     else {
      this.currPath_0 = null;
    }
  };
  function PaintState$draw$lambda(line) {
    return line.size >= 2;
  }
  PaintState.prototype.draw_glqt06$ = function (ctx) {
    ctx.screen.clear_cqchof$(CLEAR_COLOR);
    var tmp$;
    tmp$ = filter(asSequence(this.paths_0), PaintState$draw$lambda).iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var tmp$_0;
      tmp$_0 = element.size;
      for (var i = 1; i < tmp$_0; i++) {
        ctx.screen.drawLine_hi675t$(element.get_za3lpa$(i - 1 | 0), element.get_za3lpa$(i), graphics.Colors.BLACK);
      }
    }
  };
  PaintState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PaintState',
    interfaces: [GameState]
  };
  _.PaintState = PaintState;
  PaintState.prototype.enter_ahvl4o$ = GameState.prototype.enter_ahvl4o$;
  CLEAR_COLOR = graphics.Colors.GREY192;
  Kotlin.defineModule('paint-common', _);
  return _;
}(typeof this['paint-common'] === 'undefined' ? {} : this['paint-common'], kotlin, kross2d);
