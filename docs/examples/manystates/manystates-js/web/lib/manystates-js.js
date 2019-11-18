if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'manystates-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'manystates-js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'manystates-js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'manystates-js'.");
}
if (typeof this['manystates-common'] === 'undefined') {
  throw new Error("Error loading module 'manystates-js'. Its dependency 'manystates-common' was not found. Please, check whether 'manystates-common' is loaded prior to 'manystates-js'.");
}
this['manystates-js'] = function (_, Kotlin, $module$kross2d, $module$manystates_common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var InitialState = $module$manystates_common.InitialState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new InitialState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('manystates-js', _);
  return _;
}(typeof this['manystates-js'] === 'undefined' ? {} : this['manystates-js'], kotlin, kross2d, this['manystates-common']);
