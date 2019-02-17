if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'sprite-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'sprite-js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'sprite-js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'sprite-js'.");
}
if (typeof this['sprite-common'] === 'undefined') {
  throw new Error("Error loading module 'sprite-js'. Its dependency 'sprite-common' was not found. Please, check whether 'sprite-common' is loaded prior to 'sprite-js'.");
}
this['sprite-js'] = function (_, Kotlin, $module$kross2d, $module$sprite_common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var SpriteState = $module$sprite_common.SpriteState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new SpriteState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('sprite-js', _);
  return _;
}(typeof this['sprite-js'] === 'undefined' ? {} : this['sprite-js'], kotlin, kross2d, this['sprite-common']);
