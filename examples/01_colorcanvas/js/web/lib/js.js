if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'js'.");
}
if (typeof common === 'undefined') {
  throw new Error("Error loading module 'js'. Its dependency 'common' was not found. Please, check whether 'common' is loaded prior to 'js'.");
}
var js = function (_, Kotlin, $module$kross2d, $module$common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var ColorCanvasState = $module$common.ColorCanvasState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new ColorCanvasState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('js', _);
  return _;
}(typeof js === 'undefined' ? {} : js, kotlin, kross2d, common);
