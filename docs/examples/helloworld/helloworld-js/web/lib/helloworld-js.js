if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'helloworld-js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'helloworld-js'.");
}
if (typeof kross2d === 'undefined') {
  throw new Error("Error loading module 'helloworld-js'. Its dependency 'kross2d' was not found. Please, check whether 'kross2d' is loaded prior to 'helloworld-js'.");
}
if (typeof this['helloworld-common'] === 'undefined') {
  throw new Error("Error loading module 'helloworld-js'. Its dependency 'helloworld-common' was not found. Please, check whether 'helloworld-common' is loaded prior to 'helloworld-js'.");
}
this['helloworld-js'] = function (_, Kotlin, $module$kross2d, $module$helloworld_common) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var AppParams = $module$kross2d.bitspittle.kross2d.engine.app.AppParams;
  var HelloWorldState = $module$helloworld_common.HelloWorldState;
  var launch = $module$kross2d.bitspittle.kross2d.engine.app.launch_hapb61$;
  function main() {
    var tmp$;
    var canvas = Kotlin.isType(tmp$ = document.querySelector('#glCanvas'), HTMLCanvasElement) ? tmp$ : throwCCE();
    launch(new AppParams(canvas), new HelloWorldState());
  }
  _.main = main;
  main();
  Kotlin.defineModule('helloworld-js', _);
  return _;
}(typeof this['helloworld-js'] === 'undefined' ? {} : this['helloworld-js'], kotlin, kross2d, this['helloworld-common']);
