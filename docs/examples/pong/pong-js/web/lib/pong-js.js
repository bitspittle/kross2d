if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'pong-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'pong-js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'pong-js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'pong-js'.");
}
if (typeof this['pong-common'] === 'undefined') {
  throw new Error("Error loading module 'pong-js'. Its dependency 'pong-common' was not found. Please, check whether 'pong-common' is loaded prior to 'pong-js'.");
}
this['pong-js'] = function (_, Kotlin, $module$kross2d, $module$pong_common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var PongState = $module$pong_common.PongState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new PongState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('pong-js', _);
  return _;
}(typeof this['pong-js'] === 'undefined' ? {} : this['pong-js'], kotlin, kross2d, this['pong-common']);
