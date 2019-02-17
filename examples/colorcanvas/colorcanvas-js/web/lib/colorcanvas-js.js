if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'colorcanvas-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'colorcanvas-js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'colorcanvas-js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'colorcanvas-js'.");
}
if (typeof this['colorcanvas-common'] === 'undefined') {
  throw new Error("Error loading module 'colorcanvas-js'. Its dependency 'colorcanvas-common' was not found. Please, check whether 'colorcanvas-common' is loaded prior to 'colorcanvas-js'.");
}
this['colorcanvas-js'] = function (_, Kotlin, $module$kross2d, $module$colorcanvas_common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var ColorCanvasState = $module$colorcanvas_common.ColorCanvasState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new ColorCanvasState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('colorcanvas-js', _);
  return _;
}(typeof this['colorcanvas-js'] === 'undefined' ? {} : this['colorcanvas-js'], kotlin, kross2d, this['colorcanvas-common']);
