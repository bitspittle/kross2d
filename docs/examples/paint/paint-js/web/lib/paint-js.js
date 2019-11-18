if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'paint-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'paint-js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'paint-js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'paint-js'.");
}
if (typeof this['paint-common'] === 'undefined') {
  throw new Error("Error loading module 'paint-js'. Its dependency 'paint-common' was not found. Please, check whether 'paint-common' is loaded prior to 'paint-js'.");
}
this['paint-js'] = function (_, Kotlin, $module$kross2d, $module$paint_common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var PaintState = $module$paint_common.PaintState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new PaintState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('paint-js', _);
  return _;
}(typeof this['paint-js'] === 'undefined' ? {} : this['paint-js'], kotlin, kross2d, this['paint-common']);
