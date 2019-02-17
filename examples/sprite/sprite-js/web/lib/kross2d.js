if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kross2d'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kross2d'.");
}
var kross2d = function (_, Kotlin) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Unit = Kotlin.kotlin.Unit;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var numberToInt = Kotlin.numberToInt;
  var Comparable = Kotlin.kotlin.Comparable;
  var kotlin_js_internal_DoubleCompanionObject = Kotlin.kotlin.js.internal.DoubleCompanionObject;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var throwCCE = Kotlin.throwCCE;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  Event.prototype = Object.create(ObservableEvent.prototype);
  Event.prototype.constructor = Event;
  Key.prototype = Object.create(Enum.prototype);
  Key.prototype.constructor = Key;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  function ObservableEvent() {
    this.observers = ArrayList_init();
  }
  ObservableEvent.prototype.plusAssign_qlkmfe$ = function (observer) {
    this.observers.add_11rb$(observer);
  };
  ObservableEvent.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ObservableEvent',
    interfaces: []
  };
  function Event() {
    ObservableEvent.call(this);
  }
  Event.prototype.invoke_11rb$ = function (params) {
    var tmp$;
    tmp$ = this.observers.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      element(params);
    }
  };
  Event.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Event',
    interfaces: [ObservableEvent]
  };
  function ImmutableColor() {
  }
  ImmutableColor.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableColor',
    interfaces: []
  };
  var Math_0 = Math;
  function Color(r, g, b, a) {
    Color$Companion_getInstance();
    if (a === void 0)
      a = 255;
    this.r_xozs7j$_0 = r;
    this.g_xozsh0$_0 = g;
    this.b_xozslb$_0 = b;
    this.a_xozsm6$_0 = a;
    var a_0 = this.r;
    this.r = Math_0.min(a_0, 255);
    var a_1 = this.g;
    this.g = Math_0.min(a_1, 255);
    var a_2 = this.b;
    this.b = Math_0.min(a_2, 255);
    var a_3 = this.a;
    this.a = Math_0.min(a_3, 255);
  }
  Object.defineProperty(Color.prototype, 'r', {
    get: function () {
      return this.r_xozs7j$_0;
    },
    set: function (r) {
      this.r_xozs7j$_0 = r;
    }
  });
  Object.defineProperty(Color.prototype, 'g', {
    get: function () {
      return this.g_xozsh0$_0;
    },
    set: function (g) {
      this.g_xozsh0$_0 = g;
    }
  });
  Object.defineProperty(Color.prototype, 'b', {
    get: function () {
      return this.b_xozslb$_0;
    },
    set: function (b) {
      this.b_xozslb$_0 = b;
    }
  });
  Object.defineProperty(Color.prototype, 'a', {
    get: function () {
      return this.a_xozsm6$_0;
    },
    set: function (a) {
      this.a_xozsm6$_0 = a;
    }
  });
  function Color$Companion() {
    Color$Companion_instance = this;
    this.A_MASK = -16777216;
    this.R_MASK = 16711680;
    this.G_MASK = 65280;
    this.B_MASK = 255;
  }
  Color$Companion.prototype.fromArgb_za3lpa$ = function (argb) {
    return new Color(argb & 16711680, argb & 65280, argb & 255, argb & -16777216);
  };
  Color$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Color$Companion_instance = null;
  function Color$Companion_getInstance() {
    if (Color$Companion_instance === null) {
      new Color$Companion();
    }
    return Color$Companion_instance;
  }
  Object.defineProperty(Color.prototype, 'argb', {
    get: function () {
      return this.a << 24 | this.r << 16 | this.g << 8 | this.b;
    }
  });
  Color.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Color',
    interfaces: [ImmutableColor]
  };
  function Color_init(r, g, b, a, $this) {
    if (a === void 0)
      a = 1.0;
    $this = $this || Object.create(Color.prototype);
    Color.call($this, numberToInt(255 * r), numberToInt(255 * g), numberToInt(255 * b), numberToInt(255 * a));
    return $this;
  }
  Color.prototype.component1 = function () {
    return this.r;
  };
  Color.prototype.component2 = function () {
    return this.g;
  };
  Color.prototype.component3 = function () {
    return this.b;
  };
  Color.prototype.component4 = function () {
    return this.a;
  };
  Color.prototype.copy_tjonv8$ = function (r, g, b, a) {
    return new Color(r === void 0 ? this.r : r, g === void 0 ? this.g : g, b === void 0 ? this.b : b, a === void 0 ? this.a : a);
  };
  Color.prototype.toString = function () {
    return 'Color(r=' + Kotlin.toString(this.r) + (', g=' + Kotlin.toString(this.g)) + (', b=' + Kotlin.toString(this.b)) + (', a=' + Kotlin.toString(this.a)) + ')';
  };
  Color.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.r) | 0;
    result = result * 31 + Kotlin.hashCode(this.g) | 0;
    result = result * 31 + Kotlin.hashCode(this.b) | 0;
    result = result * 31 + Kotlin.hashCode(this.a) | 0;
    return result;
  };
  Color.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.r, other.r) && Kotlin.equals(this.g, other.g) && Kotlin.equals(this.b, other.b) && Kotlin.equals(this.a, other.a)))));
  };
  function clamp($receiver, min, max) {
    var b = Math_0.min(max, $receiver);
    return Math_0.max(min, b);
  }
  function clamp_0($receiver, min, max) {
    var b = max.compareTo_11rb$($receiver) <= 0 ? max : $receiver;
    return min.compareTo_11rb$(b) >= 0 ? min : b;
  }
  function clamp_1($receiver, min, max) {
    var b = Math_0.min(max, $receiver);
    return Math_0.max(min, b);
  }
  function clamp_2($receiver, min, max) {
    var b = Math_0.min(max, $receiver);
    return Math_0.max(min, b);
  }
  function ImmutablePt2() {
  }
  ImmutablePt2.prototype.isZero = function () {
    return this.x === 0.0 && this.y === 0.0;
  };
  ImmutablePt2.prototype.unaryMinus = function () {
    return new Pt2(-this.x, -this.y);
  };
  ImmutablePt2.prototype.plus_nvqvy9$ = function (rhs) {
    return new Pt2(this.x + rhs.x, this.y + rhs.y);
  };
  ImmutablePt2.prototype.minus_nvqvy9$ = function (rhs) {
    return new Pt2(this.x - rhs.x, this.y - rhs.y);
  };
  ImmutablePt2.prototype.minus_cz6rql$ = function (rhs) {
    return new Vec2(this.x - rhs.x, this.y - rhs.y);
  };
  ImmutablePt2.prototype.times_nvqvy9$ = function (rhs) {
    return new Pt2(this.x * rhs.x, this.y * rhs.y);
  };
  ImmutablePt2.prototype.times_mx4ult$ = function (value) {
    return new Pt2(this.x * value, this.y * value);
  };
  ImmutablePt2.prototype.div_nvqvy9$ = function (rhs) {
    return new Pt2(this.x / rhs.x, this.y / rhs.y);
  };
  ImmutablePt2.prototype.div_mx4ult$ = function (value) {
    return new Pt2(this.x / value, this.y / value);
  };
  ImmutablePt2.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutablePt2',
    interfaces: []
  };
  function Pt2(x, y) {
    Pt2$Companion_getInstance();
    this.x_6j1ntv$_0 = x;
    this.y_6j1nuq$_0 = y;
  }
  Object.defineProperty(Pt2.prototype, 'x', {
    get: function () {
      return this.x_6j1ntv$_0;
    },
    set: function (x) {
      this.x_6j1ntv$_0 = x;
    }
  });
  Object.defineProperty(Pt2.prototype, 'y', {
    get: function () {
      return this.y_6j1nuq$_0;
    },
    set: function (y) {
      this.y_6j1nuq$_0 = y;
    }
  });
  function Pt2$Companion() {
    Pt2$Companion_instance = this;
    this.ZERO = Pt2_init();
  }
  Pt2$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Pt2$Companion_instance = null;
  function Pt2$Companion_getInstance() {
    if (Pt2$Companion_instance === null) {
      new Pt2$Companion();
    }
    return Pt2$Companion_instance;
  }
  Pt2.prototype.set_cz6rql$ = function (other) {
    this.x = other.x;
    this.y = other.y;
  };
  Pt2.prototype.set_nvqvy9$ = function (other) {
    this.x = other.x;
    this.y = other.y;
  };
  Pt2.prototype.plusAssign_nvqvy9$ = function (rhs) {
    this.x = this.x + rhs.x;
    this.y = this.y + rhs.y;
  };
  Pt2.prototype.minusAssign_nvqvy9$ = function (rhs) {
    this.x = this.x - rhs.x;
    this.y = this.y - rhs.y;
  };
  Pt2.prototype.timesAssign_nvqvy9$ = function (rhs) {
    this.x = this.x * rhs.x;
    this.y = this.y * rhs.y;
  };
  Pt2.prototype.timesAssign_mx4ult$ = function (value) {
    this.x = this.x * value;
    this.y = this.y * value;
  };
  Pt2.prototype.divAssign_nvqvy9$ = function (rhs) {
    this.x = this.x / rhs.x;
    this.y = this.y / rhs.y;
  };
  Pt2.prototype.divAssign_mx4ult$ = function (value) {
    this.x = this.x / value;
    this.y = this.y / value;
  };
  Pt2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Pt2',
    interfaces: [ImmutablePt2]
  };
  function Pt2_init($this) {
    $this = $this || Object.create(Pt2.prototype);
    Pt2.call($this, 0.0, 0.0);
    return $this;
  }
  function Pt2_init_0(x, y, $this) {
    $this = $this || Object.create(Pt2.prototype);
    Pt2.call($this, x, y);
    return $this;
  }
  function Pt2_init_1(pt, $this) {
    $this = $this || Object.create(Pt2.prototype);
    Pt2.call($this, pt.x, pt.y);
    return $this;
  }
  function Pt2_init_2(vec, $this) {
    $this = $this || Object.create(Pt2.prototype);
    Pt2.call($this, vec.x, vec.y);
    return $this;
  }
  Pt2.prototype.component1 = function () {
    return this.x;
  };
  Pt2.prototype.component2 = function () {
    return this.y;
  };
  Pt2.prototype.copy_dleff0$ = function (x, y) {
    return new Pt2(x === void 0 ? this.x : x, y === void 0 ? this.y : y);
  };
  Pt2.prototype.toString = function () {
    return 'Pt2(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + ')';
  };
  Pt2.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    return result;
  };
  Pt2.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y)))));
  };
  function ImmutableVec2() {
  }
  ImmutableVec2.prototype.isZero = function () {
    return this.x === 0.0 && this.y === 0.0;
  };
  ImmutableVec2.prototype.len2 = function () {
    return this.x * this.x + this.y * this.y;
  };
  ImmutableVec2.prototype.len = function () {
    var x = this.len2();
    return Math_0.sqrt(x);
  };
  ImmutableVec2.prototype.normalized = function () {
    return this.isZero() ? Vec2$Companion_getInstance().ZERO : this.div_mx4ult$(this.len());
  };
  ImmutableVec2.prototype.unaryMinus = function () {
    return new Vec2(-this.x, -this.y);
  };
  ImmutableVec2.prototype.plus_nvqvy9$ = function (other) {
    return new Vec2(this.x + other.x, this.y + other.y);
  };
  ImmutableVec2.prototype.minus_nvqvy9$ = function (other) {
    return new Vec2(this.x - other.x, this.y - other.y);
  };
  ImmutableVec2.prototype.times_nvqvy9$ = function (other) {
    return new Vec2(this.x * other.x, this.y * other.y);
  };
  ImmutableVec2.prototype.times_mx4ult$ = function (value) {
    return new Vec2(this.x * value, this.y * value);
  };
  ImmutableVec2.prototype.div_nvqvy9$ = function (other) {
    return new Vec2(this.x / other.x, this.y / other.y);
  };
  ImmutableVec2.prototype.div_mx4ult$ = function (value) {
    return new Vec2(this.x / value, this.y / value);
  };
  ImmutableVec2.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableVec2',
    interfaces: []
  };
  function Vec2(x, y) {
    Vec2$Companion_getInstance();
    this.x_cc33$_0 = x;
    this.y_cc28$_0 = y;
  }
  Object.defineProperty(Vec2.prototype, 'x', {
    get: function () {
      return this.x_cc33$_0;
    },
    set: function (x) {
      this.x_cc33$_0 = x;
    }
  });
  Object.defineProperty(Vec2.prototype, 'y', {
    get: function () {
      return this.y_cc28$_0;
    },
    set: function (y) {
      this.y_cc28$_0 = y;
    }
  });
  function Vec2$Companion() {
    Vec2$Companion_instance = this;
    this.ZERO = Vec2_init();
  }
  Vec2$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Vec2$Companion_instance = null;
  function Vec2$Companion_getInstance() {
    if (Vec2$Companion_instance === null) {
      new Vec2$Companion();
    }
    return Vec2$Companion_instance;
  }
  Vec2.prototype.set_cz6rql$ = function (other) {
    this.x = other.x;
    this.y = other.y;
  };
  Vec2.prototype.set_nvqvy9$ = function (other) {
    this.x = other.x;
    this.y = other.y;
  };
  Vec2.prototype.normalize = function () {
    if (!this.isZero()) {
      var len = this.len();
      this.x = this.x / len;
      this.y = this.y / len;
    }
  };
  Vec2.prototype.plusAssign_nvqvy9$ = function (other) {
    this.x = this.x + other.x;
    this.y = this.y + other.y;
  };
  Vec2.prototype.minusAssign_nvqvy9$ = function (other) {
    this.x = this.x - other.x;
    this.y = this.y - other.y;
  };
  Vec2.prototype.timesAssign_nvqvy9$ = function (other) {
    this.x = this.x * other.x;
    this.y = this.y * other.y;
  };
  Vec2.prototype.timesAssign_mx4ult$ = function (value) {
    this.x = this.x * value;
    this.y = this.y * value;
  };
  Vec2.prototype.divAssign_nvqvy9$ = function (other) {
    this.x = this.x / other.x;
    this.y = this.y / other.y;
  };
  Vec2.prototype.divAssign_mx4ult$ = function (value) {
    this.x = this.x / value;
    this.y = this.y / value;
  };
  Vec2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Vec2',
    interfaces: [ImmutableVec2]
  };
  function Vec2_init($this) {
    $this = $this || Object.create(Vec2.prototype);
    Vec2.call($this, 0.0, 0.0);
    return $this;
  }
  function Vec2_init_0(x, y, $this) {
    $this = $this || Object.create(Vec2.prototype);
    Vec2.call($this, x, y);
    return $this;
  }
  function Vec2_init_1(vec, $this) {
    $this = $this || Object.create(Vec2.prototype);
    Vec2.call($this, vec.x, vec.y);
    return $this;
  }
  function Vec2_init_2(pt, $this) {
    $this = $this || Object.create(Vec2.prototype);
    Vec2.call($this, pt.x, pt.y);
    return $this;
  }
  Vec2.prototype.component1 = function () {
    return this.x;
  };
  Vec2.prototype.component2 = function () {
    return this.y;
  };
  Vec2.prototype.copy_dleff0$ = function (x, y) {
    return new Vec2(x === void 0 ? this.x : x, y === void 0 ? this.y : y);
  };
  Vec2.prototype.toString = function () {
    return 'Vec2(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + ')';
  };
  Vec2.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    return result;
  };
  Vec2.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y)))));
  };
  function ImmutableDuration() {
  }
  Object.defineProperty(ImmutableDuration.prototype, 'micros', {
    get: function () {
      return this.nanos / 1000.0;
    }
  });
  Object.defineProperty(ImmutableDuration.prototype, 'millis', {
    get: function () {
      return this.micros / 1000.0;
    }
  });
  Object.defineProperty(ImmutableDuration.prototype, 'secs', {
    get: function () {
      return this.millis / 1000.0;
    }
  });
  Object.defineProperty(ImmutableDuration.prototype, 'mins', {
    get: function () {
      return this.secs / 60.0;
    }
  });
  ImmutableDuration.prototype.isZero = function () {
    return this.nanos === 0.0;
  };
  ImmutableDuration.prototype.copy = function () {
    var $receiver = new Duration();
    $receiver.setFrom_evd4je$(this);
    return $receiver;
  };
  ImmutableDuration.prototype.plus_evd4je$ = function (rhs) {
    return new Duration(this.nanos + rhs.nanos);
  };
  ImmutableDuration.prototype.minus_evd4je$ = function (rhs) {
    return new Duration(this.nanos - rhs.nanos);
  };
  ImmutableDuration.prototype.times_14dthe$ = function (value) {
    return new Duration(this.nanos * value);
  };
  ImmutableDuration.prototype.div_14dthe$ = function (value) {
    return new Duration(this.nanos / value);
  };
  ImmutableDuration.prototype.compareTo_11rb$ = function (other) {
    return Kotlin.compareTo(this.nanos, other.nanos);
  };
  ImmutableDuration.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableDuration',
    interfaces: [Comparable]
  };
  function max(a, b) {
    return a.nanos > b.nanos ? a : b;
  }
  function min(a, b) {
    return a.nanos < b.nanos ? a : b;
  }
  function Duration(nanos) {
    Duration$Companion_getInstance();
    if (nanos === void 0)
      nanos = 0.0;
    this.nanos_x06d89$_0 = nanos;
  }
  Object.defineProperty(Duration.prototype, 'nanos', {
    get: function () {
      return this.nanos_x06d89$_0;
    },
    set: function (nanos) {
      this.nanos_x06d89$_0 = nanos;
    }
  });
  function Duration$Companion() {
    Duration$Companion_instance = this;
    this.ZERO = new Duration();
    this.MAX = new Duration$Companion$MAX$ObjectLiteral();
    this.MIN = new Duration$Companion$MIN$ObjectLiteral();
  }
  Duration$Companion.prototype.zero = function () {
    return new Duration();
  };
  Duration$Companion.prototype.ofNanos_14dthe$ = function (nanos) {
    return new Duration(nanos);
  };
  Duration$Companion.prototype.ofMicros_14dthe$ = function (micros) {
    var $receiver = new Duration();
    $receiver.micros = micros;
    return $receiver;
  };
  Duration$Companion.prototype.ofMillis_14dthe$ = function (millis) {
    var $receiver = new Duration();
    $receiver.millis = millis;
    return $receiver;
  };
  Duration$Companion.prototype.ofSeconds_14dthe$ = function (secs) {
    var $receiver = new Duration();
    $receiver.secs = secs;
    return $receiver;
  };
  Duration$Companion.prototype.ofMinutes_14dthe$ = function (mins) {
    var $receiver = new Duration();
    $receiver.mins = mins;
    return $receiver;
  };
  Duration$Companion.prototype.ofNanos_s8cxhz$ = function (nanos) {
    return this.ofNanos_14dthe$(nanos.toNumber());
  };
  Duration$Companion.prototype.ofMicros_s8cxhz$ = function (micros) {
    return this.ofMicros_14dthe$(micros.toNumber());
  };
  Duration$Companion.prototype.ofMillis_s8cxhz$ = function (millis) {
    return this.ofMillis_14dthe$(millis.toNumber());
  };
  Duration$Companion.prototype.ofSeconds_s8cxhz$ = function (secs) {
    return this.ofSeconds_14dthe$(secs.toNumber());
  };
  Duration$Companion.prototype.ofMinutes_s8cxhz$ = function (mins) {
    return this.ofMinutes_14dthe$(mins.toNumber());
  };
  function Duration$Companion$MAX$ObjectLiteral() {
    this.nanos_u4knw6$_0 = kotlin_js_internal_DoubleCompanionObject.POSITIVE_INFINITY;
    this.micros_ipb68$_0 = kotlin_js_internal_DoubleCompanionObject.POSITIVE_INFINITY;
    this.millis_dv47b$_0 = kotlin_js_internal_DoubleCompanionObject.POSITIVE_INFINITY;
    this.secs_845e83$_0 = kotlin_js_internal_DoubleCompanionObject.POSITIVE_INFINITY;
    this.mins_87ddoi$_0 = kotlin_js_internal_DoubleCompanionObject.POSITIVE_INFINITY;
  }
  Object.defineProperty(Duration$Companion$MAX$ObjectLiteral.prototype, 'nanos', {
    get: function () {
      return this.nanos_u4knw6$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MAX$ObjectLiteral.prototype, 'micros', {
    get: function () {
      return this.micros_ipb68$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MAX$ObjectLiteral.prototype, 'millis', {
    get: function () {
      return this.millis_dv47b$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MAX$ObjectLiteral.prototype, 'secs', {
    get: function () {
      return this.secs_845e83$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MAX$ObjectLiteral.prototype, 'mins', {
    get: function () {
      return this.mins_87ddoi$_0;
    }
  });
  Duration$Companion$MAX$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ImmutableDuration]
  };
  function Duration$Companion$MIN$ObjectLiteral() {
    this.nanos_kjpl7g$_0 = kotlin_js_internal_DoubleCompanionObject.NEGATIVE_INFINITY;
    this.micros_8r1xtq$_0 = kotlin_js_internal_DoubleCompanionObject.NEGATIVE_INFINITY;
    this.millis_8m7qut$_0 = kotlin_js_internal_DoubleCompanionObject.NEGATIVE_INFINITY;
    this.secs_s2wbjl$_0 = kotlin_js_internal_DoubleCompanionObject.NEGATIVE_INFINITY;
    this.mins_s64b00$_0 = kotlin_js_internal_DoubleCompanionObject.NEGATIVE_INFINITY;
  }
  Object.defineProperty(Duration$Companion$MIN$ObjectLiteral.prototype, 'nanos', {
    get: function () {
      return this.nanos_kjpl7g$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MIN$ObjectLiteral.prototype, 'micros', {
    get: function () {
      return this.micros_8r1xtq$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MIN$ObjectLiteral.prototype, 'millis', {
    get: function () {
      return this.millis_8m7qut$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MIN$ObjectLiteral.prototype, 'secs', {
    get: function () {
      return this.secs_s2wbjl$_0;
    }
  });
  Object.defineProperty(Duration$Companion$MIN$ObjectLiteral.prototype, 'mins', {
    get: function () {
      return this.mins_s64b00$_0;
    }
  });
  Duration$Companion$MIN$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ImmutableDuration]
  };
  Duration$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Duration$Companion_instance = null;
  function Duration$Companion_getInstance() {
    if (Duration$Companion_instance === null) {
      new Duration$Companion();
    }
    return Duration$Companion_instance;
  }
  Object.defineProperty(Duration.prototype, 'micros', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableDuration.prototype, 'micros');
    },
    set: function (value) {
      this.nanos = value * 1000.0;
    }
  });
  Object.defineProperty(Duration.prototype, 'millis', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableDuration.prototype, 'millis');
    },
    set: function (value) {
      this.micros = value * 1000.0;
    }
  });
  Object.defineProperty(Duration.prototype, 'secs', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableDuration.prototype, 'secs');
    },
    set: function (value) {
      this.millis = value * 1000.0;
    }
  });
  Object.defineProperty(Duration.prototype, 'mins', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableDuration.prototype, 'mins');
    },
    set: function (value) {
      this.secs = value * 60.0;
    }
  });
  Duration.prototype.setFrom_evd4je$ = function (rhs) {
    this.nanos = rhs.nanos;
  };
  Duration.prototype.clampToMax_evd4je$ = function (other) {
    var a = this.nanos;
    var b = other.nanos;
    this.nanos = Math_0.max(a, b);
  };
  Duration.prototype.clampToMin_evd4je$ = function (other) {
    var a = this.nanos;
    var b = other.nanos;
    this.nanos = Math_0.min(a, b);
  };
  Duration.prototype.plusAssign_evd4je$ = function (rhs) {
    this.nanos = this.nanos + rhs.nanos;
  };
  Duration.prototype.minusAssign_evd4je$ = function (rhs) {
    this.nanos = this.nanos - rhs.nanos;
  };
  Duration.prototype.timesAssign_14dthe$ = function (value) {
    this.nanos = this.nanos * value;
  };
  Duration.prototype.divAssign_14dthe$ = function (value) {
    this.nanos = this.nanos / value;
  };
  Duration.prototype.toString = function () {
    return this.secs.toString() + 's';
  };
  Duration.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Duration',
    interfaces: [ImmutableDuration]
  };
  Duration.prototype.component1 = function () {
    return this.nanos;
  };
  Duration.prototype.copy_14dthe$ = function (nanos) {
    return new Duration(nanos === void 0 ? this.nanos : nanos);
  };
  Duration.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.nanos) | 0;
    return result;
  };
  Duration.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && Kotlin.equals(this.nanos, other.nanos))));
  };
  function Instant(nanos) {
    Instant$Companion_getInstance();
    this.nanos_0 = nanos;
  }
  function Instant$Companion() {
    Instant$Companion_instance = this;
  }
  Instant$Companion.prototype.now = function () {
    return new Instant(nowNs());
  };
  Instant$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Instant$Companion_instance = null;
  function Instant$Companion_getInstance() {
    if (Instant$Companion_instance === null) {
      new Instant$Companion();
    }
    return Instant$Companion_instance;
  }
  Instant.prototype.minus_cj7vob$ = function (rhs) {
    return new Duration(this.nanos_0.subtract(rhs.nanos_0).toNumber());
  };
  Instant.prototype.compareTo_11rb$ = function (other) {
    return this.nanos_0.compareTo_11rb$(other.nanos_0);
  };
  Instant.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Instant',
    interfaces: [Comparable]
  };
  function GameState() {
  }
  GameState.prototype.init_jezbry$ = function (ctx) {
  };
  GameState.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'GameState',
    interfaces: []
  };
  function ApplicationFacade() {
  }
  ApplicationFacade.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ApplicationFacade',
    interfaces: []
  };
  function Application(params, initialState) {
    this.backend_0 = new ApplicationBackend(params);
    this.currentState_0 = initialState;
    var keyboard = new DefaultKeyboard();
    this.backend_0.keyPressed.plusAssign_qlkmfe$(Application_init$lambda(keyboard));
    this.backend_0.keyReleased.plusAssign_qlkmfe$(Application_init$lambda_0(keyboard));
    var appFacade = new Application_init$ObjectLiteral(this);
    var timer = new DefaultTimer();
    var assetLoader = new AssetLoader(params.assetsRoot);
    var initContext = new Application_init$ObjectLiteral_0(assetLoader, timer);
    var updateContext = new Application_init$ObjectLiteral_1(appFacade, assetLoader, this, keyboard, timer);
    var drawContext = new Application_init$ObjectLiteral_2(this, timer);
    this.currentState_0.init_jezbry$(initContext);
    var frameStart = {v: Instant$Companion_getInstance().now()};
    this.backend_0.runForever_o14v8n$(Application_init$lambda_1(frameStart, timer, this, updateContext, keyboard, drawContext));
  }
  function Application_init$lambda(closure$keyboard) {
    return function (key) {
      closure$keyboard.handleKey_vzxlbm$(key, true);
      return Unit;
    };
  }
  function Application_init$lambda_0(closure$keyboard) {
    return function (key) {
      closure$keyboard.handleKey_vzxlbm$(key, false);
      return Unit;
    };
  }
  function Application_init$ObjectLiteral(this$Application) {
    this.this$Application = this$Application;
  }
  Application_init$ObjectLiteral.prototype.quit = function () {
    this.this$Application.backend_0.quit();
  };
  Application_init$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ApplicationFacade]
  };
  function Application_init$ObjectLiteral_0(closure$assetLoader, closure$timer) {
    this.assetLoader_ttqb77$_0 = closure$assetLoader;
    this.timer_rwuagb$_0 = closure$timer;
  }
  Object.defineProperty(Application_init$ObjectLiteral_0.prototype, 'assetLoader', {
    get: function () {
      return this.assetLoader_ttqb77$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_0.prototype, 'timer', {
    get: function () {
      return this.timer_rwuagb$_0;
    }
  });
  Application_init$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [InitContext]
  };
  function Application_init$ObjectLiteral_1(closure$appFacade, closure$assetLoader, this$Application, closure$keyboard, closure$timer) {
    this.app_2v28a9$_0 = closure$appFacade;
    this.assetLoader_ttqb77$_0 = closure$assetLoader;
    this.screen_4j6xmw$_0 = this$Application.backend_0.drawSurface;
    this.keyboard_jnaahp$_0 = closure$keyboard;
    this.timer_rwuagb$_0 = closure$timer;
  }
  Object.defineProperty(Application_init$ObjectLiteral_1.prototype, 'app', {
    get: function () {
      return this.app_2v28a9$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_1.prototype, 'assetLoader', {
    get: function () {
      return this.assetLoader_ttqb77$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_1.prototype, 'screen', {
    get: function () {
      return this.screen_4j6xmw$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_1.prototype, 'keyboard', {
    get: function () {
      return this.keyboard_jnaahp$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_1.prototype, 'timer', {
    get: function () {
      return this.timer_rwuagb$_0;
    }
  });
  Application_init$ObjectLiteral_1.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [UpdateContext]
  };
  function Application_init$ObjectLiteral_2(this$Application, closure$timer) {
    this.screen_4j6xmw$_0 = this$Application.backend_0.drawSurface;
    this.timer_rwuagb$_0 = closure$timer;
  }
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'screen', {
    get: function () {
      return this.screen_4j6xmw$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'timer', {
    get: function () {
      return this.timer_rwuagb$_0;
    }
  });
  Application_init$ObjectLiteral_2.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [DrawContext]
  };
  function Application_init$lambda_1(closure$frameStart, closure$timer, this$Application, closure$updateContext, closure$keyboard, closure$drawContext) {
    return function () {
      var lastFrameStart = closure$frameStart.v;
      closure$frameStart.v = Instant$Companion_getInstance().now();
      closure$timer.lastFrameDuration.setFrom_evd4je$(closure$frameStart.v.minus_cj7vob$(lastFrameStart));
      this$Application.currentState_0.update_kung05$(closure$updateContext);
      closure$keyboard.step();
      this$Application.currentState_0.draw_glqt06$(closure$drawContext);
      return Unit;
    };
  }
  Application.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Application',
    interfaces: []
  };
  function launch(appParams, initialState) {
    new Application(appParams, initialState);
  }
  function DrawContext() {
  }
  DrawContext.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DrawContext',
    interfaces: []
  };
  function InitContext() {
  }
  InitContext.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'InitContext',
    interfaces: []
  };
  function UpdateContext() {
  }
  UpdateContext.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'UpdateContext',
    interfaces: []
  };
  function ImmutableDrawSurface() {
  }
  ImmutableDrawSurface.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableDrawSurface',
    interfaces: []
  };
  function DrawSurface() {
  }
  function DrawSurface$DrawParams(dest, src, destSize, srcSize) {
    if (dest === void 0)
      dest = Pt2$Companion_getInstance().ZERO;
    if (src === void 0)
      src = Pt2$Companion_getInstance().ZERO;
    if (destSize === void 0)
      destSize = null;
    if (srcSize === void 0)
      srcSize = null;
    this.dest = dest;
    this.src = src;
    this.destSize = destSize;
    this.srcSize = srcSize;
  }
  DrawSurface$DrawParams.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DrawParams',
    interfaces: []
  };
  DrawSurface.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DrawSurface',
    interfaces: [ImmutableDrawSurface]
  };
  function Key(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Key_initFields() {
    Key_initFields = function () {
    };
    Key$ESC_instance = new Key('ESC', 0);
    Key$UP_instance = new Key('UP', 1);
    Key$DOWN_instance = new Key('DOWN', 2);
    Key$LEFT_instance = new Key('LEFT', 3);
    Key$RIGHT_instance = new Key('RIGHT', 4);
  }
  var Key$ESC_instance;
  function Key$ESC_getInstance() {
    Key_initFields();
    return Key$ESC_instance;
  }
  var Key$UP_instance;
  function Key$UP_getInstance() {
    Key_initFields();
    return Key$UP_instance;
  }
  var Key$DOWN_instance;
  function Key$DOWN_getInstance() {
    Key_initFields();
    return Key$DOWN_instance;
  }
  var Key$LEFT_instance;
  function Key$LEFT_getInstance() {
    Key_initFields();
    return Key$LEFT_instance;
  }
  var Key$RIGHT_instance;
  function Key$RIGHT_getInstance() {
    Key_initFields();
    return Key$RIGHT_instance;
  }
  Key.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Key',
    interfaces: [Enum]
  };
  function Key$values() {
    return [Key$ESC_getInstance(), Key$UP_getInstance(), Key$DOWN_getInstance(), Key$LEFT_getInstance(), Key$RIGHT_getInstance()];
  }
  Key.values = Key$values;
  function Key$valueOf(name) {
    switch (name) {
      case 'ESC':
        return Key$ESC_getInstance();
      case 'UP':
        return Key$UP_getInstance();
      case 'DOWN':
        return Key$DOWN_getInstance();
      case 'LEFT':
        return Key$LEFT_getInstance();
      case 'RIGHT':
        return Key$RIGHT_getInstance();
      default:throwISE('No enum constant bitspittle.kross2d.engine.input.Key.' + name);
    }
  }
  Key.valueOf_61zpoe$ = Key$valueOf;
  function Keyboard() {
  }
  Keyboard.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Keyboard',
    interfaces: []
  };
  var LinkedHashSet_init = Kotlin.kotlin.collections.LinkedHashSet_init_287e2$;
  function DefaultKeyboard() {
    this.keysPrev_0 = LinkedHashSet_init();
    this.keysCurr_0 = LinkedHashSet_init();
  }
  DefaultKeyboard.prototype.handleKey_vzxlbm$ = function (key, isDown) {
    if (isDown)
      this.keysCurr_0.add_11rb$(key);
    else
      this.keysCurr_0.remove_11rb$(key);
  };
  DefaultKeyboard.prototype.step = function () {
    this.keysPrev_0.clear();
    this.keysPrev_0.addAll_brywnq$(this.keysCurr_0);
  };
  DefaultKeyboard.prototype.isJustPressed_5hom6x$ = function (key) {
    return !this.keysPrev_0.contains_11rb$(key) && this.keysCurr_0.contains_11rb$(key);
  };
  DefaultKeyboard.prototype.isDown_5hom6x$ = function (key) {
    return this.keysCurr_0.contains_11rb$(key);
  };
  DefaultKeyboard.prototype.isJustReleased_5hom6x$ = function (key) {
    return this.keysPrev_0.contains_11rb$(key) && !this.keysCurr_0.contains_11rb$(key);
  };
  DefaultKeyboard.prototype.isUp_5hom6x$ = function (key) {
    return !this.keysCurr_0.contains_11rb$(key);
  };
  DefaultKeyboard.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultKeyboard',
    interfaces: [Keyboard]
  };
  function Timer() {
  }
  Timer.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Timer',
    interfaces: []
  };
  function DefaultTimer(lastFrameDuration) {
    if (lastFrameDuration === void 0)
      lastFrameDuration = Duration$Companion_getInstance().zero();
    this.lastFrameDuration_eq5ioq$_0 = lastFrameDuration;
  }
  Object.defineProperty(DefaultTimer.prototype, 'lastFrameDuration', {
    get: function () {
      return this.lastFrameDuration_eq5ioq$_0;
    }
  });
  DefaultTimer.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultTimer',
    interfaces: [Timer]
  };
  function now($receiver) {
    return Instant$Companion_getInstance().now();
  }
  var measure = defineInlineFunction('kross2d.bitspittle.kross2d.engine.time.measure_5ndm34$', wrapFunction(function () {
    var Instant = _.bitspittle.kross2d.core.time.Instant;
    return function ($receiver, block) {
      var start = Instant.Companion.now();
      block();
      return Instant.Companion.now().minus_cj7vob$(start);
    };
  }));
  function nowNs() {
    var nowMs = Date.now();
    var nowNs = nowMs * 1000000;
    return Kotlin.Long.fromNumber(nowNs);
  }
  function AppParams(canvasElement, assetsRoot) {
    if (assetsRoot === void 0)
      assetsRoot = 'assets';
    this.canvasElement = canvasElement;
    this.assetsRoot = assetsRoot;
  }
  AppParams.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AppParams',
    interfaces: []
  };
  function ApplicationBackend(params) {
    var tmp$;
    this.ctx_0 = Kotlin.isType(tmp$ = params.canvasElement.getContext('2d'), CanvasRenderingContext2D) ? tmp$ : throwCCE();
    this.ctx_0.imageSmoothingEnabled = false;
    var handleKeyEvent = ApplicationBackend_init$handleKeyEvent(this);
    window.onkeydown = ApplicationBackend_init$lambda(handleKeyEvent);
    window.onkeyup = ApplicationBackend_init$lambda_0(handleKeyEvent);
    this.drawSurface = new ApplicationBackend$drawSurface$ObjectLiteral(this, params);
    this._keyPressed_0 = new Event();
    this.keyPressed = this._keyPressed_0;
    this._keyReleased_0 = new Event();
    this.keyReleased = this._keyReleased_0;
  }
  ApplicationBackend.prototype.runForever_o14v8n$ = function (frameStep) {
    window.setInterval(frameStep, 16);
  };
  ApplicationBackend.prototype.quit = function () {
  };
  function ApplicationBackend_init$handleKeyEvent(this$ApplicationBackend) {
    return function (keyEvent, isDown) {
      var tmp$;
      println(keyEvent.code);
      switch (keyEvent.code) {
        case 'Escape':
          tmp$ = Key$ESC_getInstance();
          break;
        case 'ArrowUp':
          tmp$ = Key$UP_getInstance();
          break;
        case 'ArrowDown':
          tmp$ = Key$DOWN_getInstance();
          break;
        case 'ArrowLeft':
          tmp$ = Key$LEFT_getInstance();
          break;
        case 'ArrowRight':
          tmp$ = Key$RIGHT_getInstance();
          break;
        default:tmp$ = null;
          break;
      }
      if (tmp$ != null) {
        var $receiver = tmp$;
        var this$ApplicationBackend_0 = this$ApplicationBackend;
        if (isDown)
          this$ApplicationBackend_0._keyPressed_0.invoke_11rb$($receiver);
        else
          this$ApplicationBackend_0._keyReleased_0.invoke_11rb$($receiver);
      }
    };
  }
  function ApplicationBackend_init$lambda(closure$handleKeyEvent) {
    return function (it) {
      closure$handleKeyEvent(it, true);
      return Unit;
    };
  }
  function ApplicationBackend_init$lambda_0(closure$handleKeyEvent) {
    return function (it) {
      closure$handleKeyEvent(it, false);
      return Unit;
    };
  }
  function ApplicationBackend$drawSurface$ObjectLiteral(this$ApplicationBackend, closure$params) {
    this.this$ApplicationBackend = this$ApplicationBackend;
    var it = closure$params.canvasElement;
    this.size_pjtos$_0 = Vec2_init_0(it.width, it.height);
  }
  Object.defineProperty(ApplicationBackend$drawSurface$ObjectLiteral.prototype, 'size', {
    get: function () {
      return this.size_pjtos$_0;
    }
  });
  ApplicationBackend$drawSurface$ObjectLiteral.prototype.clear_msppnp$ = function (color) {
    this.this$ApplicationBackend.ctx_0.fillStyle = 'rgb(' + color.r + ', ' + color.g + ', ' + color.b + ')';
    this.this$ApplicationBackend.ctx_0.fillRect(0.0, 0.0, this.size.x, this.size.y);
  };
  ApplicationBackend$drawSurface$ObjectLiteral.prototype.draw_ky71b2$ = function (image, params) {
    var tmp$, tmp$_0;
    var dest = params.dest;
    var src = params.src;
    var srcSize = (tmp$ = params.srcSize) != null ? tmp$ : image.size;
    var destSize = (tmp$_0 = params.destSize) != null ? tmp$_0 : srcSize;
    this.this$ApplicationBackend.ctx_0.drawImage(image.jsImage, src.x, src.y, srcSize.x, srcSize.y, dest.x, dest.y, destSize.x, destSize.y);
  };
  ApplicationBackend$drawSurface$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [DrawSurface]
  };
  ApplicationBackend.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ApplicationBackend',
    interfaces: []
  };
  function AssetLoader(root) {
    this.root_0 = root;
  }
  AssetLoader.prototype.loadImage_61zpoe$ = function (relativePath) {
    return new Image_0(this.root_0 + '/' + relativePath);
  };
  AssetLoader.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssetLoader',
    interfaces: []
  };
  function Image_0(path) {
    var $receiver = new Image();
    $receiver.src = path;
    this.jsImage = $receiver;
  }
  Object.defineProperty(Image_0.prototype, 'size', {
    get: function () {
      return Vec2_init_0(this.jsImage.width, this.jsImage.height);
    }
  });
  Image_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Image',
    interfaces: []
  };
  var package$bitspittle = _.bitspittle || (_.bitspittle = {});
  var package$kross2d = package$bitspittle.kross2d || (package$bitspittle.kross2d = {});
  var package$core = package$kross2d.core || (package$kross2d.core = {});
  var package$event = package$core.event || (package$core.event = {});
  package$event.ObservableEvent = ObservableEvent;
  package$event.Event = Event;
  var package$graphics = package$core.graphics || (package$core.graphics = {});
  package$graphics.ImmutableColor = ImmutableColor;
  Object.defineProperty(Color, 'Companion', {
    get: Color$Companion_getInstance
  });
  package$graphics.Color_init_7b5o5w$ = Color_init;
  package$graphics.Color = Color;
  var package$math = package$core.math || (package$core.math = {});
  package$math.clamp_e4yvb3$ = clamp;
  package$math.clamp_ekzx8g$ = clamp_0;
  package$math.clamp_wj6e7o$ = clamp_1;
  package$math.clamp_nig4hr$ = clamp_2;
  package$math.ImmutablePt2 = ImmutablePt2;
  Object.defineProperty(Pt2, 'Companion', {
    get: Pt2$Companion_getInstance
  });
  package$math.Pt2_init = Pt2_init;
  package$math.Pt2_init_vux9f0$ = Pt2_init_0;
  package$math.Pt2_init_cz6rql$ = Pt2_init_1;
  package$math.Pt2_init_nvqvy9$ = Pt2_init_2;
  package$math.Pt2 = Pt2;
  package$math.ImmutableVec2 = ImmutableVec2;
  Object.defineProperty(Vec2, 'Companion', {
    get: Vec2$Companion_getInstance
  });
  package$math.Vec2_init = Vec2_init;
  package$math.Vec2_init_vux9f0$ = Vec2_init_0;
  package$math.Vec2_init_nvqvy9$ = Vec2_init_1;
  package$math.Vec2_init_cz6rql$ = Vec2_init_2;
  package$math.Vec2 = Vec2;
  var package$time = package$core.time || (package$core.time = {});
  package$time.ImmutableDuration = ImmutableDuration;
  package$time.max_qyyj9w$ = max;
  package$time.min_qyyj9w$ = min;
  Object.defineProperty(Duration, 'Companion', {
    get: Duration$Companion_getInstance
  });
  package$time.Duration = Duration;
  Object.defineProperty(Instant, 'Companion', {
    get: Instant$Companion_getInstance
  });
  package$time.Instant = Instant;
  var package$engine = package$kross2d.engine || (package$kross2d.engine = {});
  package$engine.GameState = GameState;
  var package$app = package$engine.app || (package$engine.app = {});
  package$app.ApplicationFacade = ApplicationFacade;
  package$app.Application = Application;
  package$app.launch_hapb61$ = launch;
  var package$context = package$engine.context || (package$engine.context = {});
  package$context.DrawContext = DrawContext;
  package$context.InitContext = InitContext;
  package$context.UpdateContext = UpdateContext;
  var package$graphics_0 = package$engine.graphics || (package$engine.graphics = {});
  package$graphics_0.ImmutableDrawSurface = ImmutableDrawSurface;
  DrawSurface.DrawParams = DrawSurface$DrawParams;
  package$graphics_0.DrawSurface = DrawSurface;
  Object.defineProperty(Key, 'ESC', {
    get: Key$ESC_getInstance
  });
  Object.defineProperty(Key, 'UP', {
    get: Key$UP_getInstance
  });
  Object.defineProperty(Key, 'DOWN', {
    get: Key$DOWN_getInstance
  });
  Object.defineProperty(Key, 'LEFT', {
    get: Key$LEFT_getInstance
  });
  Object.defineProperty(Key, 'RIGHT', {
    get: Key$RIGHT_getInstance
  });
  var package$input = package$engine.input || (package$engine.input = {});
  package$input.Key = Key;
  package$input.Keyboard = Keyboard;
  package$input.DefaultKeyboard = DefaultKeyboard;
  var package$time_0 = package$engine.time || (package$engine.time = {});
  package$time_0.Timer = Timer;
  package$time_0.DefaultTimer = DefaultTimer;
  package$time_0.now_ydye95$ = now;
  $$importsForInline$$.kross2d = _;
  package$time_0.measure_5ndm34$ = measure;
  package$time.nowNs_8be2vx$ = nowNs;
  package$app.AppParams = AppParams;
  package$app.ApplicationBackend = ApplicationBackend;
  var package$assets = package$engine.assets || (package$engine.assets = {});
  package$assets.AssetLoader = AssetLoader;
  package$graphics_0.Image = Image_0;
  Pt2.prototype.isZero = ImmutablePt2.prototype.isZero;
  Pt2.prototype.unaryMinus = ImmutablePt2.prototype.unaryMinus;
  Pt2.prototype.plus_nvqvy9$ = ImmutablePt2.prototype.plus_nvqvy9$;
  Pt2.prototype.minus_nvqvy9$ = ImmutablePt2.prototype.minus_nvqvy9$;
  Pt2.prototype.minus_cz6rql$ = ImmutablePt2.prototype.minus_cz6rql$;
  Pt2.prototype.times_nvqvy9$ = ImmutablePt2.prototype.times_nvqvy9$;
  Pt2.prototype.times_mx4ult$ = ImmutablePt2.prototype.times_mx4ult$;
  Pt2.prototype.div_nvqvy9$ = ImmutablePt2.prototype.div_nvqvy9$;
  Pt2.prototype.div_mx4ult$ = ImmutablePt2.prototype.div_mx4ult$;
  Vec2.prototype.isZero = ImmutableVec2.prototype.isZero;
  Vec2.prototype.len2 = ImmutableVec2.prototype.len2;
  Vec2.prototype.len = ImmutableVec2.prototype.len;
  Vec2.prototype.normalized = ImmutableVec2.prototype.normalized;
  Vec2.prototype.unaryMinus = ImmutableVec2.prototype.unaryMinus;
  Vec2.prototype.plus_nvqvy9$ = ImmutableVec2.prototype.plus_nvqvy9$;
  Vec2.prototype.minus_nvqvy9$ = ImmutableVec2.prototype.minus_nvqvy9$;
  Vec2.prototype.times_nvqvy9$ = ImmutableVec2.prototype.times_nvqvy9$;
  Vec2.prototype.times_mx4ult$ = ImmutableVec2.prototype.times_mx4ult$;
  Vec2.prototype.div_nvqvy9$ = ImmutableVec2.prototype.div_nvqvy9$;
  Vec2.prototype.div_mx4ult$ = ImmutableVec2.prototype.div_mx4ult$;
  Duration$Companion$MAX$ObjectLiteral.prototype.isZero = ImmutableDuration.prototype.isZero;
  Duration$Companion$MAX$ObjectLiteral.prototype.copy = ImmutableDuration.prototype.copy;
  Duration$Companion$MAX$ObjectLiteral.prototype.plus_evd4je$ = ImmutableDuration.prototype.plus_evd4je$;
  Duration$Companion$MAX$ObjectLiteral.prototype.minus_evd4je$ = ImmutableDuration.prototype.minus_evd4je$;
  Duration$Companion$MAX$ObjectLiteral.prototype.times_14dthe$ = ImmutableDuration.prototype.times_14dthe$;
  Duration$Companion$MAX$ObjectLiteral.prototype.div_14dthe$ = ImmutableDuration.prototype.div_14dthe$;
  Duration$Companion$MAX$ObjectLiteral.prototype.compareTo_11rb$ = ImmutableDuration.prototype.compareTo_11rb$;
  Duration$Companion$MIN$ObjectLiteral.prototype.isZero = ImmutableDuration.prototype.isZero;
  Duration$Companion$MIN$ObjectLiteral.prototype.copy = ImmutableDuration.prototype.copy;
  Duration$Companion$MIN$ObjectLiteral.prototype.plus_evd4je$ = ImmutableDuration.prototype.plus_evd4je$;
  Duration$Companion$MIN$ObjectLiteral.prototype.minus_evd4je$ = ImmutableDuration.prototype.minus_evd4je$;
  Duration$Companion$MIN$ObjectLiteral.prototype.times_14dthe$ = ImmutableDuration.prototype.times_14dthe$;
  Duration$Companion$MIN$ObjectLiteral.prototype.div_14dthe$ = ImmutableDuration.prototype.div_14dthe$;
  Duration$Companion$MIN$ObjectLiteral.prototype.compareTo_11rb$ = ImmutableDuration.prototype.compareTo_11rb$;
  Duration.prototype.isZero = ImmutableDuration.prototype.isZero;
  Duration.prototype.copy = ImmutableDuration.prototype.copy;
  Duration.prototype.plus_evd4je$ = ImmutableDuration.prototype.plus_evd4je$;
  Duration.prototype.minus_evd4je$ = ImmutableDuration.prototype.minus_evd4je$;
  Duration.prototype.times_14dthe$ = ImmutableDuration.prototype.times_14dthe$;
  Duration.prototype.div_14dthe$ = ImmutableDuration.prototype.div_14dthe$;
  Duration.prototype.compareTo_11rb$ = ImmutableDuration.prototype.compareTo_11rb$;
  Kotlin.defineModule('kross2d', _);
  return _;
}(typeof kross2d === 'undefined' ? {} : kross2d, kotlin);
