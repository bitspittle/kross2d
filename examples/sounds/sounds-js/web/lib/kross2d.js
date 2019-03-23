if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kross2d'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kross2d'.");
}
var kross2d = function (_, Kotlin) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Unit = Kotlin.kotlin.Unit;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var numberToInt = Kotlin.numberToInt;
  var hashCode = Kotlin.hashCode;
  var Comparable = Kotlin.kotlin.Comparable;
  var kotlin_js_internal_DoubleCompanionObject = Kotlin.kotlin.js.internal.DoubleCompanionObject;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException_init_pdl1vj$;
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var getCallableRef = Kotlin.getCallableRef;
  var get_lastIndex = Kotlin.kotlin.collections.get_lastIndex_55thoc$;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var take = Kotlin.kotlin.collections.take_ba2ldo$;
  var last = Kotlin.kotlin.collections.last_2p1efm$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var math = Kotlin.kotlin.math;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var throwCCE = Kotlin.throwCCE;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  Event.prototype = Object.create(ObservableEvent.prototype);
  Event.prototype.constructor = Event;
  Rect$Companion$EMPTY$ObjectLiteral.prototype = Object.create(ImmutableRect.prototype);
  Rect$Companion$EMPTY$ObjectLiteral.prototype.constructor = Rect$Companion$EMPTY$ObjectLiteral;
  Rect.prototype = Object.create(ImmutableRect.prototype);
  Rect.prototype.constructor = Rect;
  Pt2$Companion$ZERO$ObjectLiteral.prototype = Object.create(ImmutablePt2.prototype);
  Pt2$Companion$ZERO$ObjectLiteral.prototype.constructor = Pt2$Companion$ZERO$ObjectLiteral;
  Pt2.prototype = Object.create(ImmutablePt2.prototype);
  Pt2.prototype.constructor = Pt2;
  Vec2$Companion$ZERO$ObjectLiteral.prototype = Object.create(ImmutableVec2.prototype);
  Vec2$Companion$ZERO$ObjectLiteral.prototype.constructor = Vec2$Companion$ZERO$ObjectLiteral;
  Vec2.prototype = Object.create(ImmutableVec2.prototype);
  Vec2.prototype.constructor = Vec2;
  Duration$Companion$MAX$ObjectLiteral.prototype = Object.create(ImmutableDuration.prototype);
  Duration$Companion$MAX$ObjectLiteral.prototype.constructor = Duration$Companion$MAX$ObjectLiteral;
  Duration$Companion$MIN$ObjectLiteral.prototype = Object.create(ImmutableDuration.prototype);
  Duration$Companion$MIN$ObjectLiteral.prototype.constructor = Duration$Companion$MIN$ObjectLiteral;
  Duration.prototype = Object.create(ImmutableDuration.prototype);
  Duration.prototype.constructor = Duration;
  Asset$State.prototype = Object.create(Enum.prototype);
  Asset$State.prototype.constructor = Asset$State;
  Key.prototype = Object.create(Enum.prototype);
  Key.prototype.constructor = Key;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  function ObservableEvent(onAdded) {
    this.onAdded_tlnqc7$_0 = onAdded;
    this.observers = ArrayList_init();
  }
  ObservableEvent.prototype.plusAssign_qlkmfe$ = function (observer) {
    this.observers.add_11rb$(observer);
    this.onAdded_tlnqc7$_0();
  };
  ObservableEvent.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ObservableEvent',
    interfaces: []
  };
  function Event(onAdded) {
    if (onAdded === void 0)
      onAdded = Event_init$lambda;
    ObservableEvent.call(this, onAdded);
  }
  Event.prototype.invoke_11rb$ = function (params) {
    var tmp$;
    tmp$ = this.observers.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      element(params);
    }
  };
  Event.prototype.clear = function () {
    this.observers.clear();
  };
  function Event_init$lambda() {
    return Unit;
  }
  Event.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Event',
    interfaces: [ObservableEvent]
  };
  function ImmutableRect() {
  }
  Object.defineProperty(ImmutableRect.prototype, 'x', {
    get: function () {
      return this.pos.x;
    }
  });
  Object.defineProperty(ImmutableRect.prototype, 'y', {
    get: function () {
      return this.pos.y;
    }
  });
  Object.defineProperty(ImmutableRect.prototype, 'w', {
    get: function () {
      return this.size.x;
    }
  });
  Object.defineProperty(ImmutableRect.prototype, 'h', {
    get: function () {
      return this.size.y;
    }
  });
  Object.defineProperty(ImmutableRect.prototype, 'center', {
    get: function () {
      return new Pt2(this.x + this.w / 2.0, this.y + this.h / 2.0);
    }
  });
  Object.defineProperty(ImmutableRect.prototype, 'area', {
    get: function () {
      return this.size.x * this.size.y;
    }
  });
  ImmutableRect.prototype.intersects_np3yo7$ = function (other) {
    var tmp$;
    if (this.x + this.w < other.x)
      tmp$ = false;
    else if (this.x > other.x + other.w)
      tmp$ = false;
    else if (this.y + this.h < other.y)
      tmp$ = false;
    else if (this.y > other.y + other.h)
      tmp$ = false;
    else
      tmp$ = true;
    return tmp$;
  };
  var Math_0 = Math;
  ImmutableRect.prototype.intersection_np3yo7$ = function (other) {
    if (!this.intersects_np3yo7$(other)) {
      return Rect_init();
    }
    var a = this.x;
    var b = other.x;
    var x0 = Math_0.max(a, b);
    var a_0 = this.y;
    var b_0 = other.y;
    var y0 = Math_0.max(a_0, b_0);
    var a_1 = this.x + this.w;
    var b_1 = other.x + other.w;
    var x1 = Math_0.min(a_1, b_1);
    var a_2 = this.y + this.h;
    var b_2 = other.y + this.h;
    var y1 = Math_0.min(a_2, b_2);
    return Rect_init_1(x0, y0, x1 - x0, y1 - y0);
  };
  ImmutableRect.prototype.equals = function (other) {
    var tmp$, tmp$_0;
    if (Kotlin.isType(other, ImmutableRect)) {
      return ((tmp$ = this.pos) != null ? tmp$.equals(other.pos) : null) && ((tmp$_0 = this.size) != null ? tmp$_0.equals(other.size) : null);
    }
    return false;
  };
  ImmutableRect.prototype.hashCode = function () {
    return this.pos.hashCode() + (31 * this.size.hashCode() | 0) | 0;
  };
  ImmutableRect.prototype.toString = function () {
    return 'Rect { (' + this.x + ', ' + this.y + '), (' + this.w + ', ' + this.h + ') }';
  };
  ImmutableRect.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ImmutableRect',
    interfaces: []
  };
  function centerIn($receiver, rect) {
    var center = Pt2_init_1(rect.center);
    center.minusAssign_nvqvy9$($receiver.div_mx4ult$(2.0));
    return center;
  }
  function Rect(pos, size) {
    Rect$Companion_getInstance();
    ImmutableRect.call(this);
    this.pos_d5paeb$_0 = Pt2_init_1(pos);
    this.size_i8cnes$_0 = Vec2_init_1(size);
  }
  function Rect$Companion() {
    Rect$Companion_instance = this;
    this.EMPTY = new Rect$Companion$EMPTY$ObjectLiteral();
  }
  function Rect$Companion$EMPTY$ObjectLiteral() {
    ImmutableRect.call(this);
    this.pos_w4h4dn$_0 = Pt2$Companion_getInstance().ZERO;
    this.size_1gpq7o$_0 = Vec2$Companion_getInstance().ZERO;
  }
  Object.defineProperty(Rect$Companion$EMPTY$ObjectLiteral.prototype, 'pos', {
    get: function () {
      return this.pos_w4h4dn$_0;
    }
  });
  Object.defineProperty(Rect$Companion$EMPTY$ObjectLiteral.prototype, 'size', {
    get: function () {
      return this.size_1gpq7o$_0;
    }
  });
  Rect$Companion$EMPTY$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ImmutableRect]
  };
  Rect$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Rect$Companion_instance = null;
  function Rect$Companion_getInstance() {
    if (Rect$Companion_instance === null) {
      new Rect$Companion();
    }
    return Rect$Companion_instance;
  }
  Object.defineProperty(Rect.prototype, 'pos', {
    get: function () {
      return this.pos_d5paeb$_0;
    }
  });
  Object.defineProperty(Rect.prototype, 'size', {
    get: function () {
      return this.size_i8cnes$_0;
    }
  });
  Object.defineProperty(Rect.prototype, 'x', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableRect.prototype, 'x');
    },
    set: function (value) {
      this.pos.x = value;
    }
  });
  Object.defineProperty(Rect.prototype, 'y', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableRect.prototype, 'y');
    },
    set: function (value) {
      this.pos.y = value;
    }
  });
  Object.defineProperty(Rect.prototype, 'w', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableRect.prototype, 'w');
    },
    set: function (value) {
      this.size.x = value;
    }
  });
  Object.defineProperty(Rect.prototype, 'h', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableRect.prototype, 'h');
    },
    set: function (value) {
      this.size.y = value;
    }
  });
  Rect.prototype.set_np3yo7$ = function (other) {
    this.pos.set_cz6rql$(other.pos);
    this.size.set_nvqvy9$(other.size);
  };
  Rect.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Rect',
    interfaces: [ImmutableRect]
  };
  function Rect_init($this) {
    $this = $this || Object.create(Rect.prototype);
    Rect.call($this, Pt2$Companion_getInstance().ZERO, Vec2$Companion_getInstance().ZERO);
    return $this;
  }
  function Rect_init_0(size, $this) {
    $this = $this || Object.create(Rect.prototype);
    Rect.call($this, Pt2$Companion_getInstance().ZERO, size);
    return $this;
  }
  function Rect_init_1(x, y, w, h, $this) {
    $this = $this || Object.create(Rect.prototype);
    Rect.call($this, new Pt2(x, y), new Vec2(w, h));
    return $this;
  }
  function Rect_init_2(x, y, w, h, $this) {
    $this = $this || Object.create(Rect.prototype);
    Rect.call($this, Pt2_init_0(x, y), Vec2_init_0(w, h));
    return $this;
  }
  function Rect_init_3(other, $this) {
    $this = $this || Object.create(Rect.prototype);
    Rect.call($this, other.pos, other.size);
    return $this;
  }
  function ImmutableColor() {
  }
  ImmutableColor.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableColor',
    interfaces: []
  };
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
  ImmutablePt2.prototype.equals = function (other) {
    return Kotlin.isType(other, ImmutablePt2) ? this.x === other.x && this.y === other.y : false;
  };
  ImmutablePt2.prototype.hashCode = function () {
    return hashCode(this.x) + (31 * hashCode(this.y) | 0) | 0;
  };
  ImmutablePt2.prototype.toString = function () {
    return 'Pt2 { (' + this.x + ', ' + this.y + ') }';
  };
  ImmutablePt2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ImmutablePt2',
    interfaces: []
  };
  function Pt2(x, y) {
    Pt2$Companion_getInstance();
    ImmutablePt2.call(this);
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
    this.ZERO = new Pt2$Companion$ZERO$ObjectLiteral();
  }
  function Pt2$Companion$ZERO$ObjectLiteral() {
    ImmutablePt2.call(this);
    this.x_auz8ho$_0 = 0.0;
    this.y_auz8gt$_0 = 0.0;
  }
  Object.defineProperty(Pt2$Companion$ZERO$ObjectLiteral.prototype, 'x', {
    get: function () {
      return this.x_auz8ho$_0;
    }
  });
  Object.defineProperty(Pt2$Companion$ZERO$ObjectLiteral.prototype, 'y', {
    get: function () {
      return this.y_auz8gt$_0;
    }
  });
  Pt2$Companion$ZERO$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ImmutablePt2]
  };
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
  ImmutableVec2.prototype.equals = function (other) {
    return Kotlin.isType(other, ImmutableVec2) ? this.x === other.x && this.y === other.y : false;
  };
  ImmutableVec2.prototype.hashCode = function () {
    return hashCode(this.x) + (31 * hashCode(this.y) | 0) | 0;
  };
  ImmutableVec2.prototype.toString = function () {
    return 'Vec2 { (' + this.x + ', ' + this.y + ') }';
  };
  ImmutableVec2.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ImmutableVec2',
    interfaces: []
  };
  function Vec2(x, y) {
    Vec2$Companion_getInstance();
    ImmutableVec2.call(this);
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
    this.ZERO = new Vec2$Companion$ZERO$ObjectLiteral();
  }
  function Vec2$Companion$ZERO$ObjectLiteral() {
    ImmutableVec2.call(this);
    this.x_bayi7m$_0 = 0.0;
    this.y_bayi8h$_0 = 0.0;
  }
  Object.defineProperty(Vec2$Companion$ZERO$ObjectLiteral.prototype, 'x', {
    get: function () {
      return this.x_bayi7m$_0;
    }
  });
  Object.defineProperty(Vec2$Companion$ZERO$ObjectLiteral.prototype, 'y', {
    get: function () {
      return this.y_bayi8h$_0;
    }
  });
  Vec2$Companion$ZERO$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ImmutableVec2]
  };
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
  ImmutableDuration.prototype.unaryMinus = function () {
    return new Duration(-this.nanos);
  };
  ImmutableDuration.prototype.compareTo_11rb$ = function (other) {
    return Kotlin.compareTo(this.nanos, other.nanos);
  };
  ImmutableDuration.prototype.equals = function (other) {
    return Kotlin.isType(other, ImmutableDuration) ? this.nanos === other.nanos : false;
  };
  ImmutableDuration.prototype.hashCode = function () {
    return hashCode(this.nanos);
  };
  ImmutableDuration.prototype.toString = function () {
    return 'Duration { ' + this.nanos + 'ns }';
  };
  ImmutableDuration.$metadata$ = {
    kind: Kind_CLASS,
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
    ImmutableDuration.call(this);
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
    ImmutableDuration.call(this);
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
    ImmutableDuration.call(this);
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
  Duration.prototype.clampToMax_evd4je$ = function (possibleMax) {
    var a = this.nanos;
    var b = possibleMax.nanos;
    this.nanos = Math_0.min(a, b);
  };
  Duration.prototype.clampToMin_evd4je$ = function (possibleMin) {
    var a = this.nanos;
    var b = possibleMin.nanos;
    this.nanos = Math_0.max(a, b);
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
  Instant.prototype.toString = function () {
    return 'Instant { ' + this.nanos_0.toString() + 'ns }';
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
    var initContext = new Application_init$ObjectLiteral_0(assetLoader, this, timer);
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
  function Application_init$ObjectLiteral_0(closure$assetLoader, this$Application, closure$timer) {
    this.assetLoader_ttqb77$_0 = closure$assetLoader;
    this.screen_4j6xmw$_0 = this$Application.backend_0.drawSurface;
    this.timer_rwuagb$_0 = closure$timer;
  }
  Object.defineProperty(Application_init$ObjectLiteral_0.prototype, 'assetLoader', {
    get: function () {
      return this.assetLoader_ttqb77$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_0.prototype, 'screen', {
    get: function () {
      return this.screen_4j6xmw$_0;
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
  function Asset(path) {
    this.path = path;
    this.state_8ymihc$_0 = Asset$State$LOADING_getInstance();
    this._onLoaded_0 = new Event(Asset$_onLoaded$lambda(this));
    this.onLoaded = this._onLoaded_0;
    this.value_7tqoz4$_0 = null;
  }
  function Asset$State(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Asset$State_initFields() {
    Asset$State_initFields = function () {
    };
    Asset$State$LOADING_instance = new Asset$State('LOADING', 0);
    Asset$State$SUCCEEDED_instance = new Asset$State('SUCCEEDED', 1);
    Asset$State$FAILED_instance = new Asset$State('FAILED', 2);
  }
  var Asset$State$LOADING_instance;
  function Asset$State$LOADING_getInstance() {
    Asset$State_initFields();
    return Asset$State$LOADING_instance;
  }
  var Asset$State$SUCCEEDED_instance;
  function Asset$State$SUCCEEDED_getInstance() {
    Asset$State_initFields();
    return Asset$State$SUCCEEDED_instance;
  }
  var Asset$State$FAILED_instance;
  function Asset$State$FAILED_getInstance() {
    Asset$State_initFields();
    return Asset$State$FAILED_instance;
  }
  Asset$State.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'State',
    interfaces: [Enum]
  };
  function Asset$State$values() {
    return [Asset$State$LOADING_getInstance(), Asset$State$SUCCEEDED_getInstance(), Asset$State$FAILED_getInstance()];
  }
  Asset$State.values = Asset$State$values;
  function Asset$State$valueOf(name) {
    switch (name) {
      case 'LOADING':
        return Asset$State$LOADING_getInstance();
      case 'SUCCEEDED':
        return Asset$State$SUCCEEDED_getInstance();
      case 'FAILED':
        return Asset$State$FAILED_getInstance();
      default:throwISE('No enum constant bitspittle.kross2d.engine.assets.Asset.State.' + name);
    }
  }
  Asset$State.valueOf_61zpoe$ = Asset$State$valueOf;
  Object.defineProperty(Asset.prototype, 'state', {
    get: function () {
      return this.state_8ymihc$_0;
    },
    set: function (state) {
      this.state_8ymihc$_0 = state;
    }
  });
  Object.defineProperty(Asset.prototype, 'value', {
    get: function () {
      return this.value_7tqoz4$_0;
    },
    set: function (value) {
      this.value_7tqoz4$_0 = value;
    }
  });
  Asset.prototype.setValue_1c3m6u$ = function (value) {
    this.assertLoading_0();
    if (value != null) {
      this.state = Asset$State$SUCCEEDED_getInstance();
      this.fireOnLoaded_0(value);
      this.value = value;
    }
     else {
      this.state = Asset$State$FAILED_getInstance();
    }
  };
  Asset.prototype.fireOnLoaded_0 = function (value) {
    this._onLoaded_0.invoke_11rb$(value);
    this._onLoaded_0.clear();
  };
  Asset.prototype.notifyFailure_8be2vx$ = function () {
    this.assertLoading_0();
    this.state = Asset$State$FAILED_getInstance();
  };
  Asset.prototype.assertLoading_0 = function () {
    if (this.state !== Asset$State$LOADING_getInstance()) {
      throw IllegalStateException_init('Attempting to modify frozen AssetHandle');
    }
  };
  function Asset$_onLoaded$lambda(this$Asset) {
    return function () {
      var tmp$;
      if ((tmp$ = this$Asset.value) != null) {
        this$Asset.fireOnLoaded_0(tmp$);
      }
      return Unit;
    };
  }
  Asset.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Asset',
    interfaces: []
  };
  function AssetLoader(root) {
    this.backend_0 = new AssetLoaderBackend(root);
  }
  AssetLoader.prototype.loadImage_61zpoe$ = function (relativePath) {
    var $receiver = new Asset(relativePath);
    this.backend_0.loadImageInto_ylngmp$($receiver);
    return $receiver;
  };
  AssetLoader.prototype.loadSound_61zpoe$ = function (relativePath) {
    var $receiver = new Asset(relativePath);
    this.backend_0.loadSoundInto_pecovy$($receiver);
    return $receiver;
  };
  AssetLoader.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssetLoader',
    interfaces: []
  };
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
  function DrawSurface$DrawParams(dest, destSize) {
    if (dest === void 0)
      dest = Pt2$Companion_getInstance().ZERO;
    if (destSize === void 0)
      destSize = null;
    this.dest = dest;
    this.destSize = destSize;
  }
  DrawSurface$DrawParams.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DrawParams',
    interfaces: []
  };
  DrawSurface.prototype.draw_ky71b2$ = function (image, params, callback$default) {
    if (params === void 0)
      params = new DrawSurface$DrawParams();
    callback$default ? callback$default(image, params) : this.draw_ky71b2$$default(image, params);
  };
  DrawSurface.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DrawSurface',
    interfaces: [ImmutableDrawSurface]
  };
  function Image_0(data, pos, sizeOverride) {
    this.data_8be2vx$ = data;
    this.pos_8be2vx$ = pos;
    this.sizeOverride_0 = sizeOverride;
  }
  Object.defineProperty(Image_0.prototype, 'size', {
    get: function () {
      var tmp$;
      return (tmp$ = this.sizeOverride_0) != null ? tmp$ : this.data_8be2vx$.size;
    }
  });
  Image_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Image',
    interfaces: []
  };
  function Image_init(data, $this) {
    $this = $this || Object.create(Image_0.prototype);
    Image_0.call($this, data, Pt2$Companion_getInstance().ZERO, null);
    return $this;
  }
  function Image_init_0(other, pos, size, $this) {
    $this = $this || Object.create(Image_0.prototype);
    Image_0.call($this, other.data_8be2vx$, other.pos_8be2vx$.plus_nvqvy9$(Vec2_init_2(pos)), size);
    return $this;
  }
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
    Key$NUM_0_instance = new Key('NUM_0', 5);
    Key$NUM_1_instance = new Key('NUM_1', 6);
    Key$NUM_2_instance = new Key('NUM_2', 7);
    Key$NUM_3_instance = new Key('NUM_3', 8);
    Key$NUM_4_instance = new Key('NUM_4', 9);
    Key$NUM_5_instance = new Key('NUM_5', 10);
    Key$NUM_6_instance = new Key('NUM_6', 11);
    Key$NUM_7_instance = new Key('NUM_7', 12);
    Key$NUM_8_instance = new Key('NUM_8', 13);
    Key$NUM_9_instance = new Key('NUM_9', 14);
    Key$SPACE_instance = new Key('SPACE', 15);
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
  var Key$NUM_0_instance;
  function Key$NUM_0_getInstance() {
    Key_initFields();
    return Key$NUM_0_instance;
  }
  var Key$NUM_1_instance;
  function Key$NUM_1_getInstance() {
    Key_initFields();
    return Key$NUM_1_instance;
  }
  var Key$NUM_2_instance;
  function Key$NUM_2_getInstance() {
    Key_initFields();
    return Key$NUM_2_instance;
  }
  var Key$NUM_3_instance;
  function Key$NUM_3_getInstance() {
    Key_initFields();
    return Key$NUM_3_instance;
  }
  var Key$NUM_4_instance;
  function Key$NUM_4_getInstance() {
    Key_initFields();
    return Key$NUM_4_instance;
  }
  var Key$NUM_5_instance;
  function Key$NUM_5_getInstance() {
    Key_initFields();
    return Key$NUM_5_instance;
  }
  var Key$NUM_6_instance;
  function Key$NUM_6_getInstance() {
    Key_initFields();
    return Key$NUM_6_instance;
  }
  var Key$NUM_7_instance;
  function Key$NUM_7_getInstance() {
    Key_initFields();
    return Key$NUM_7_instance;
  }
  var Key$NUM_8_instance;
  function Key$NUM_8_getInstance() {
    Key_initFields();
    return Key$NUM_8_instance;
  }
  var Key$NUM_9_instance;
  function Key$NUM_9_getInstance() {
    Key_initFields();
    return Key$NUM_9_instance;
  }
  var Key$SPACE_instance;
  function Key$SPACE_getInstance() {
    Key_initFields();
    return Key$SPACE_instance;
  }
  Key.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Key',
    interfaces: [Enum]
  };
  function Key$values() {
    return [Key$ESC_getInstance(), Key$UP_getInstance(), Key$DOWN_getInstance(), Key$LEFT_getInstance(), Key$RIGHT_getInstance(), Key$NUM_0_getInstance(), Key$NUM_1_getInstance(), Key$NUM_2_getInstance(), Key$NUM_3_getInstance(), Key$NUM_4_getInstance(), Key$NUM_5_getInstance(), Key$NUM_6_getInstance(), Key$NUM_7_getInstance(), Key$NUM_8_getInstance(), Key$NUM_9_getInstance(), Key$SPACE_getInstance()];
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
      case 'NUM_0':
        return Key$NUM_0_getInstance();
      case 'NUM_1':
        return Key$NUM_1_getInstance();
      case 'NUM_2':
        return Key$NUM_2_getInstance();
      case 'NUM_3':
        return Key$NUM_3_getInstance();
      case 'NUM_4':
        return Key$NUM_4_getInstance();
      case 'NUM_5':
        return Key$NUM_5_getInstance();
      case 'NUM_6':
        return Key$NUM_6_getInstance();
      case 'NUM_7':
        return Key$NUM_7_getInstance();
      case 'NUM_8':
        return Key$NUM_8_getInstance();
      case 'NUM_9':
        return Key$NUM_9_getInstance();
      case 'SPACE':
        return Key$SPACE_getInstance();
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
  function KeyFrame(value, duration, lerp) {
    if (lerp === void 0)
      lerp = getCallableRef('noLerp', function ($receiver, percent, start, end) {
        return $receiver.noLerp_9nitn7$(percent, start, end);
      }.bind(null, Lerps_getInstance()));
    this.value = value;
    this.duration = duration;
    this.lerp = lerp;
  }
  KeyFrame.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'KeyFrame',
    interfaces: []
  };
  KeyFrame.prototype.component1 = function () {
    return this.value;
  };
  KeyFrame.prototype.component2 = function () {
    return this.duration;
  };
  KeyFrame.prototype.component3 = function () {
    return this.lerp;
  };
  KeyFrame.prototype.copy_tbk1ip$ = function (value, duration, lerp) {
    return new KeyFrame(value === void 0 ? this.value : value, duration === void 0 ? this.duration : duration, lerp === void 0 ? this.lerp : lerp);
  };
  KeyFrame.prototype.toString = function () {
    return 'KeyFrame(value=' + Kotlin.toString(this.value) + (', duration=' + Kotlin.toString(this.duration)) + (', lerp=' + Kotlin.toString(this.lerp)) + ')';
  };
  KeyFrame.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.value) | 0;
    result = result * 31 + Kotlin.hashCode(this.duration) | 0;
    result = result * 31 + Kotlin.hashCode(this.lerp) | 0;
    return result;
  };
  KeyFrame.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.value, other.value) && Kotlin.equals(this.duration, other.duration) && Kotlin.equals(this.lerp, other.lerp)))));
  };
  function PlayStrategy() {
  }
  PlayStrategy.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'PlayStrategy',
    interfaces: []
  };
  function OneShotPlayStrategy() {
    this._elapsed_0 = Duration$Companion_getInstance().zero();
    this.elapsed_n4omcf$_0 = this._elapsed_0;
  }
  Object.defineProperty(OneShotPlayStrategy.prototype, 'elapsed', {
    get: function () {
      return this.elapsed_n4omcf$_0;
    }
  });
  OneShotPlayStrategy.prototype.reset = function () {
    this._elapsed_0.setFrom_evd4je$(Duration$Companion_getInstance().ZERO);
  };
  OneShotPlayStrategy.prototype.elapse_qyyj9w$ = function (duration, animLength) {
    this._elapsed_0.plusAssign_evd4je$(duration);
    this._elapsed_0.clampToMax_evd4je$(animLength);
  };
  OneShotPlayStrategy.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'OneShotPlayStrategy',
    interfaces: [PlayStrategy]
  };
  function LoopingPlayStrategy() {
    this._elapsed_0 = Duration$Companion_getInstance().zero();
    this.elapsed_z3a1p$_0 = this._elapsed_0;
  }
  Object.defineProperty(LoopingPlayStrategy.prototype, 'elapsed', {
    get: function () {
      return this.elapsed_z3a1p$_0;
    }
  });
  LoopingPlayStrategy.prototype.reset = function () {
    this._elapsed_0.setFrom_evd4je$(Duration$Companion_getInstance().ZERO);
  };
  LoopingPlayStrategy.prototype.elapse_qyyj9w$ = function (duration, animLength) {
    this._elapsed_0.plusAssign_evd4je$(duration);
    while (this._elapsed_0.compareTo_11rb$(animLength) >= 0) {
      this._elapsed_0.minusAssign_evd4je$(animLength);
    }
  };
  LoopingPlayStrategy.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'LoopingPlayStrategy',
    interfaces: [PlayStrategy]
  };
  function BouncingPlayStrategy() {
    this.elapseForward_0 = true;
    this._elapsed_0 = Duration$Companion_getInstance().zero();
    this.elapsed_5x4aws$_0 = this._elapsed_0;
  }
  Object.defineProperty(BouncingPlayStrategy.prototype, 'elapsed', {
    get: function () {
      return this.elapsed_5x4aws$_0;
    }
  });
  BouncingPlayStrategy.prototype.reset = function () {
    this._elapsed_0.setFrom_evd4je$(Duration$Companion_getInstance().ZERO);
    this.elapseForward_0 = true;
  };
  BouncingPlayStrategy.prototype.elapse_qyyj9w$ = function (duration, animLength) {
    if (this.elapseForward_0) {
      this._elapsed_0.plusAssign_evd4je$(duration);
      if (this._elapsed_0.compareTo_11rb$(animLength) >= 0) {
        var extra = this._elapsed_0.minus_evd4je$(animLength);
        this._elapsed_0.setFrom_evd4je$(animLength);
        this.elapseForward_0 = false;
        this.elapse_qyyj9w$(extra, animLength);
      }
    }
     else {
      this._elapsed_0.minusAssign_evd4je$(duration);
      if (this._elapsed_0.compareTo_11rb$(Duration$Companion_getInstance().ZERO) < 0) {
        var extra_0 = this._elapsed_0.unaryMinus();
        this._elapsed_0.setFrom_evd4je$(Duration$Companion_getInstance().ZERO);
        this.elapseForward_0 = true;
        this.elapse_qyyj9w$(extra_0, animLength);
      }
    }
  };
  BouncingPlayStrategy.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'BouncingPlayStrategy',
    interfaces: [PlayStrategy]
  };
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var checkIndexOverflow = Kotlin.kotlin.collections.checkIndexOverflow_za3lpa$;
  function Anim(keyframes, playStrategy) {
    if (playStrategy === void 0)
      playStrategy = new LoopingPlayStrategy();
    this.keyframes = keyframes;
    this.playStrategy_0 = playStrategy;
    this.value_2wskd2$_0 = this.keyframes.get_za3lpa$(0).value;
    this.duration = null;
    this.startTimes_0 = null;
    this.currFrame_0 = 0;
    if (this.keyframes.isEmpty()) {
      throw IllegalArgumentException_init('Animation must contain at least one frame');
    }
    var size = this.keyframes.size - 1 | 0;
    var list = ArrayList_init_0(size);
    for (var index = 0; index < size; index++) {
      list.add_11rb$(Duration$Companion_getInstance().zero());
    }
    this.startTimes_0 = list;
    var accum = Duration$Companion_getInstance().zero();
    var tmp$, tmp$_0;
    var index_0 = 0;
    tmp$ = take(this.keyframes, this.keyframes.size - 1 | 0).iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      var i = checkIndexOverflow((tmp$_0 = index_0, index_0 = tmp$_0 + 1 | 0, tmp$_0));
      accum.plusAssign_evd4je$(item.duration);
      this.startTimes_0.get_za3lpa$(i).setFrom_evd4je$(accum);
    }
    accum.plusAssign_evd4je$(last(this.keyframes).duration);
    this.duration = accum;
  }
  Object.defineProperty(Anim.prototype, 'value', {
    get: function () {
      return this.value_2wskd2$_0;
    },
    set: function (value) {
      this.value_2wskd2$_0 = value;
    }
  });
  Anim.prototype.reset = function () {
    this.playStrategy_0.reset();
    this.elapse_evd4je$(Duration$Companion_getInstance().ZERO);
  };
  Anim.prototype.elapse_evd4je$ = function (duration) {
    var tmp$;
    this.playStrategy_0.elapse_qyyj9w$(duration, this.duration);
    var $receiver = this.startTimes_0;
    var indexOfFirst$result;
    indexOfFirst$break: do {
      var tmp$_0;
      var index = 0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        if (item.compareTo_11rb$(this.playStrategy_0.elapsed) > 0) {
          indexOfFirst$result = index;
          break indexOfFirst$break;
        }
        index = index + 1 | 0;
      }
      indexOfFirst$result = -1;
    }
     while (false);
    this.currFrame_0 = indexOfFirst$result;
    if (this.currFrame_0 < 0)
      this.currFrame_0 = get_lastIndex(this.keyframes);
    if (this.currFrame_0 === get_lastIndex(this.keyframes)) {
      tmp$ = this.keyframes.get_za3lpa$(this.currFrame_0).value;
    }
     else {
      var start = this.keyframes.get_za3lpa$(this.currFrame_0).value;
      var end = this.keyframes.get_za3lpa$(this.currFrame_0 + 1 | 0).value;
      var percent = (this.playStrategy_0.elapsed.nanos - this.frameStart_0(this.currFrame_0).nanos) / this.keyframes.get_za3lpa$(this.currFrame_0).duration.nanos;
      tmp$ = this.keyframes.get_za3lpa$(this.currFrame_0).lerp(percent, start, end);
    }
    this.value = tmp$;
  };
  Anim.prototype.frameStart_0 = function (frame) {
    var $receiver = this.startTimes_0;
    var index = frame - 1 | 0;
    return index >= 0 && index <= get_lastIndex($receiver) ? $receiver.get_za3lpa$(index) : Duration$Companion_getInstance().ZERO;
  };
  Anim.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Anim',
    interfaces: []
  };
  function Anim_init(keyframes, playStrategy, $this) {
    if (playStrategy === void 0)
      playStrategy = new LoopingPlayStrategy();
    $this = $this || Object.create(Anim.prototype);
    Anim.call($this, listOf(keyframes.slice()), playStrategy);
    return $this;
  }
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  function Anim_init_0(frameDuration, values, playStrategy, defaultLerp, $this) {
    if (playStrategy === void 0)
      playStrategy = new LoopingPlayStrategy();
    if (defaultLerp === void 0)
      defaultLerp = getCallableRef('noLerp', function ($receiver, percent, start, end) {
        return $receiver.noLerp_9nitn7$(percent, start, end);
      }.bind(null, Lerps_getInstance()));
    $this = $this || Object.create(Anim.prototype);
    var $receiver = listOf(values.slice());
    var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var item = tmp$.next();
      destination.add_11rb$(new KeyFrame(item, frameDuration, defaultLerp));
    }
    Anim.call($this, destination, playStrategy);
    return $this;
  }
  var HALF_PI;
  function Lerps() {
    Lerps_instance = this;
  }
  Lerps.prototype.noLerp_9nitn7$ = function (percent, start, end) {
    return start;
  };
  Lerps.prototype.linear_y2kzbl$ = function (percent, start, end) {
    return start + percent * (end - start);
  };
  Lerps.prototype.easeOut_y2kzbl$ = function (percent, start, end) {
    var x = percent * HALF_PI;
    return start + end * Math_0.sin(x);
  };
  Lerps.prototype.easeIn_y2kzbl$ = function (percent, start, end) {
    var tmp$ = start + end;
    var x = percent * HALF_PI;
    return tmp$ - end * Math_0.cos(x);
  };
  Lerps.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Lerps',
    interfaces: []
  };
  var Lerps_instance = null;
  function Lerps_getInstance() {
    if (Lerps_instance === null) {
      new Lerps();
    }
    return Lerps_instance;
  }
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  function Tiles(image, tileSize) {
    this.image_0 = image;
    this.tileSize = tileSize;
    this.tiles_0 = LinkedHashMap_init();
  }
  Tiles.prototype.getTile_vux9f0$ = function (x, y) {
    var $receiver = this.tiles_0;
    var key = to(x, y);
    var tmp$;
    var value = $receiver.get_11rb$(key);
    if (value == null) {
      var answer = Image_init_0(this.image_0, new Pt2(x * this.tileSize.x, y * this.tileSize.y), this.tileSize);
      $receiver.put_xwzc9p$(key, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    return tmp$;
  };
  Tiles.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Tiles',
    interfaces: []
  };
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
        case 'Digit0':
          tmp$ = Key$NUM_0_getInstance();
          break;
        case 'Digit1':
          tmp$ = Key$NUM_1_getInstance();
          break;
        case 'Digit2':
          tmp$ = Key$NUM_2_getInstance();
          break;
        case 'Digit3':
          tmp$ = Key$NUM_3_getInstance();
          break;
        case 'Digit4':
          tmp$ = Key$NUM_4_getInstance();
          break;
        case 'Digit5':
          tmp$ = Key$NUM_5_getInstance();
          break;
        case 'Digit6':
          tmp$ = Key$NUM_6_getInstance();
          break;
        case 'Digit7':
          tmp$ = Key$NUM_7_getInstance();
          break;
        case 'Digit8':
          tmp$ = Key$NUM_8_getInstance();
          break;
        case 'Digit9':
          tmp$ = Key$NUM_9_getInstance();
          break;
        case 'Space':
          tmp$ = Key$SPACE_getInstance();
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
  ApplicationBackend$drawSurface$ObjectLiteral.prototype.draw_ky71b2$$default = function (image, params) {
    var tmp$;
    var dest = params.dest;
    var src = image.pos_8be2vx$;
    var srcSize = image.size;
    var destSize = (tmp$ = params.destSize) != null ? tmp$ : srcSize;
    this.this$ApplicationBackend.ctx_0.drawImage(image.data_8be2vx$.jsImage, src.x, src.y, srcSize.x, srcSize.y, dest.x, dest.y, destSize.x, destSize.y);
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
  function AssetLoaderBackend(root) {
    this.root_0 = root;
  }
  function AssetLoaderBackend$loadImageInto$lambda(closure$asset, closure$data) {
    return function (it) {
      closure$asset.setValue_1c3m6u$(Image_init(closure$data));
      return Unit;
    };
  }
  function AssetLoaderBackend$loadImageInto$lambda_0(closure$asset) {
    return function (f, f_0, f_1, f_2, f_3) {
      closure$asset.notifyFailure_8be2vx$();
      return Unit;
    };
  }
  AssetLoaderBackend.prototype.loadImageInto_ylngmp$ = function (asset) {
    var data = new ImageData(this.root_0 + '/' + asset.path);
    data.jsImage.onload = AssetLoaderBackend$loadImageInto$lambda(asset, data);
    data.jsImage.onerror = AssetLoaderBackend$loadImageInto$lambda_0(asset);
  };
  function AssetLoaderBackend$loadSoundInto$lambda(closure$asset, closure$sound) {
    return function (it) {
      closure$asset.setValue_1c3m6u$(closure$sound);
      println('Loaded ' + closure$asset.path);
      return Unit;
    };
  }
  function AssetLoaderBackend$loadSoundInto$lambda_0(closure$asset) {
    return function (f, f_0, f_1, f_2, f_3) {
      closure$asset.notifyFailure_8be2vx$();
      println('Failed ' + closure$asset.path);
      return Unit;
    };
  }
  AssetLoaderBackend.prototype.loadSoundInto_pecovy$ = function (asset) {
    var sound = new Sound(this.root_0 + '/' + asset.path);
    sound.jsAudio.onloadeddata = AssetLoaderBackend$loadSoundInto$lambda(asset, sound);
    sound.jsAudio.onerror = AssetLoaderBackend$loadSoundInto$lambda_0(asset);
  };
  AssetLoaderBackend.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssetLoaderBackend',
    interfaces: []
  };
  function Sound(path) {
    this.jsAudio = new Audio(path);
  }
  Sound.prototype.play = function () {
    this.jsAudio.currentTime = 0.0;
    this.jsAudio.play();
  };
  Sound.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Sound',
    interfaces: []
  };
  function ImageData(path) {
    var $receiver = new Image();
    $receiver.src = path;
    this.jsImage = $receiver;
    this._size_0 = null;
  }
  Object.defineProperty(ImageData.prototype, 'size', {
    get: function () {
      var tmp$;
      if (this._size_0 == null && this.jsImage.width > 0) {
        this._size_0 = Vec2_init_0(this.jsImage.width, this.jsImage.height);
      }
      return (tmp$ = this._size_0) != null ? tmp$ : Vec2$Companion_getInstance().ZERO;
    }
  });
  ImageData.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ImageData',
    interfaces: []
  };
  var package$bitspittle = _.bitspittle || (_.bitspittle = {});
  var package$kross2d = package$bitspittle.kross2d || (package$bitspittle.kross2d = {});
  var package$core = package$kross2d.core || (package$kross2d.core = {});
  var package$event = package$core.event || (package$core.event = {});
  package$event.ObservableEvent = ObservableEvent;
  package$event.Event = Event;
  var package$geom = package$core.geom || (package$core.geom = {});
  package$geom.ImmutableRect = ImmutableRect;
  package$geom.centerIn_exd3ih$ = centerIn;
  Object.defineProperty(Rect, 'Companion', {
    get: Rect$Companion_getInstance
  });
  package$geom.Rect_init = Rect_init;
  package$geom.Rect_init_nvqvy9$ = Rect_init_0;
  package$geom.Rect_init_7b5o5w$ = Rect_init_1;
  package$geom.Rect_init_tjonv8$ = Rect_init_2;
  package$geom.Rect_init_np3yo7$ = Rect_init_3;
  package$geom.Rect = Rect;
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
  Object.defineProperty(Asset$State, 'LOADING', {
    get: Asset$State$LOADING_getInstance
  });
  Object.defineProperty(Asset$State, 'SUCCEEDED', {
    get: Asset$State$SUCCEEDED_getInstance
  });
  Object.defineProperty(Asset$State, 'FAILED', {
    get: Asset$State$FAILED_getInstance
  });
  Asset.State = Asset$State;
  var package$assets = package$engine.assets || (package$engine.assets = {});
  package$assets.Asset = Asset;
  package$assets.AssetLoader = AssetLoader;
  var package$context = package$engine.context || (package$engine.context = {});
  package$context.DrawContext = DrawContext;
  package$context.InitContext = InitContext;
  package$context.UpdateContext = UpdateContext;
  var package$graphics_0 = package$engine.graphics || (package$engine.graphics = {});
  package$graphics_0.ImmutableDrawSurface = ImmutableDrawSurface;
  DrawSurface.DrawParams = DrawSurface$DrawParams;
  package$graphics_0.DrawSurface = DrawSurface;
  package$graphics_0.Image_init_9euhhb$ = Image_init;
  package$graphics_0.Image_init_t9bfio$ = Image_init_0;
  package$graphics_0.Image = Image_0;
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
  Object.defineProperty(Key, 'NUM_0', {
    get: Key$NUM_0_getInstance
  });
  Object.defineProperty(Key, 'NUM_1', {
    get: Key$NUM_1_getInstance
  });
  Object.defineProperty(Key, 'NUM_2', {
    get: Key$NUM_2_getInstance
  });
  Object.defineProperty(Key, 'NUM_3', {
    get: Key$NUM_3_getInstance
  });
  Object.defineProperty(Key, 'NUM_4', {
    get: Key$NUM_4_getInstance
  });
  Object.defineProperty(Key, 'NUM_5', {
    get: Key$NUM_5_getInstance
  });
  Object.defineProperty(Key, 'NUM_6', {
    get: Key$NUM_6_getInstance
  });
  Object.defineProperty(Key, 'NUM_7', {
    get: Key$NUM_7_getInstance
  });
  Object.defineProperty(Key, 'NUM_8', {
    get: Key$NUM_8_getInstance
  });
  Object.defineProperty(Key, 'NUM_9', {
    get: Key$NUM_9_getInstance
  });
  Object.defineProperty(Key, 'SPACE', {
    get: Key$SPACE_getInstance
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
  var package$extras = package$kross2d.extras || (package$kross2d.extras = {});
  var package$anim = package$extras.anim || (package$extras.anim = {});
  package$anim.KeyFrame = KeyFrame;
  package$anim.PlayStrategy = PlayStrategy;
  package$anim.OneShotPlayStrategy = OneShotPlayStrategy;
  package$anim.LoopingPlayStrategy = LoopingPlayStrategy;
  package$anim.BouncingPlayStrategy = BouncingPlayStrategy;
  package$anim.Anim_init_jwz80g$ = Anim_init;
  package$anim.Anim_init_21rvlz$ = Anim_init_0;
  package$anim.Anim = Anim;
  Object.defineProperty(package$anim, 'Lerps', {
    get: Lerps_getInstance
  });
  var package$graphics_1 = package$extras.graphics || (package$extras.graphics = {});
  package$graphics_1.Tiles = Tiles;
  package$time.nowNs_8be2vx$ = nowNs;
  package$app.AppParams = AppParams;
  package$app.ApplicationBackend = ApplicationBackend;
  package$assets.AssetLoaderBackend = AssetLoaderBackend;
  var package$audio = package$engine.audio || (package$engine.audio = {});
  package$audio.Sound = Sound;
  package$graphics_0.ImageData = ImageData;
  ApplicationBackend$drawSurface$ObjectLiteral.prototype.draw_ky71b2$ = DrawSurface.prototype.draw_ky71b2$;
  HALF_PI = math.PI / 2;
  Kotlin.defineModule('kross2d', _);
  return _;
}(typeof kross2d === 'undefined' ? {} : kross2d, kotlin);
