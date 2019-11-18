if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'sounds-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'sounds-js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'sounds-js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'sounds-js'.");
}
if (typeof this['sounds-common'] === 'undefined') {
  throw new Error("Error loading module 'sounds-js'. Its dependency 'sounds-common' was not found. Please, check whether 'sounds-common' is loaded prior to 'sounds-js'.");
}
this['sounds-js'] = function (_, Kotlin, $module$kross2d, $module$sounds_common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var SoundsState = $module$sounds_common.SoundsState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new SoundsState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('sounds-js', _);
  return _;
}(typeof this['sounds-js'] === 'undefined' ? {} : this['sounds-js'], kotlin, kross2d, this['sounds-common']);
