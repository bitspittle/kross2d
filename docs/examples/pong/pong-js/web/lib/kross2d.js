if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kross2d'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kross2d'.");
}
var kross2d = function (_, Kotlin) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Unit = Kotlin.kotlin.Unit;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var math = Kotlin.kotlin.math;
  var hashCode = Kotlin.hashCode;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var numberToInt = Kotlin.numberToInt;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var Exception = Kotlin.kotlin.Exception;
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var ensureNotNull = Kotlin.ensureNotNull;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException_init_pdl1vj$;
  var Comparable = Kotlin.kotlin.Comparable;
  var kotlin_js_internal_DoubleCompanionObject = Kotlin.kotlin.js.internal.DoubleCompanionObject;
  var get_lastIndex = Kotlin.kotlin.collections.get_lastIndex_55thoc$;
  var last = Kotlin.kotlin.collections.last_2p1efm$;
  var throwUPAE = Kotlin.throwUPAE;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var getCallableRef = Kotlin.getCallableRef;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var take = Kotlin.kotlin.collections.take_ba2ldo$;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var throwCCE = Kotlin.throwCCE;
  var split = Kotlin.kotlin.text.split_o64adg$;
  var max = Kotlin.kotlin.collections.max_lvsncp$;
  var equals = Kotlin.equals;
  var substringAfterLast = Kotlin.kotlin.text.substringAfterLast_8cymmc$;
  var substringBefore = Kotlin.kotlin.text.substringBefore_8cymmc$;
  Event.prototype = Object.create(ObservableEvent.prototype);
  Event.prototype.constructor = Event;
  Circle$Companion$EMPTY$ObjectLiteral.prototype = Object.create(ImmutableCircle.prototype);
  Circle$Companion$EMPTY$ObjectLiteral.prototype.constructor = Circle$Companion$EMPTY$ObjectLiteral;
  Circle.prototype = Object.create(ImmutableCircle.prototype);
  Circle.prototype.constructor = Circle;
  Rect$Companion$EMPTY$ObjectLiteral.prototype = Object.create(ImmutableRect.prototype);
  Rect$Companion$EMPTY$ObjectLiteral.prototype.constructor = Rect$Companion$EMPTY$ObjectLiteral;
  Rect.prototype = Object.create(ImmutableRect.prototype);
  Rect.prototype.constructor = Rect;
  Square$Companion$EMPTY$ObjectLiteral.prototype = Object.create(ImmutableSquare.prototype);
  Square$Companion$EMPTY$ObjectLiteral.prototype.constructor = Square$Companion$EMPTY$ObjectLiteral;
  Square.prototype = Object.create(ImmutableSquare.prototype);
  Square.prototype.constructor = Square;
  Pt2$Companion$ZERO$ObjectLiteral.prototype = Object.create(ImmutablePt2.prototype);
  Pt2$Companion$ZERO$ObjectLiteral.prototype.constructor = Pt2$Companion$ZERO$ObjectLiteral;
  Pt2.prototype = Object.create(ImmutablePt2.prototype);
  Pt2.prototype.constructor = Pt2;
  Vec2$Companion$ZERO$ObjectLiteral.prototype = Object.create(ImmutableVec2.prototype);
  Vec2$Companion$ZERO$ObjectLiteral.prototype.constructor = Vec2$Companion$ZERO$ObjectLiteral;
  Vec2.prototype = Object.create(ImmutableVec2.prototype);
  Vec2.prototype.constructor = Vec2;
  AlreadyDisposedException.prototype = Object.create(Exception.prototype);
  AlreadyDisposedException.prototype.constructor = AlreadyDisposedException;
  Disposer$IllegalDisposerOperation.prototype = Object.create(Exception.prototype);
  Disposer$IllegalDisposerOperation.prototype.constructor = Disposer$IllegalDisposerOperation;
  Duration$Companion$MAX$ObjectLiteral.prototype = Object.create(ImmutableDuration.prototype);
  Duration$Companion$MAX$ObjectLiteral.prototype.constructor = Duration$Companion$MAX$ObjectLiteral;
  Duration$Companion$MIN$ObjectLiteral.prototype = Object.create(ImmutableDuration.prototype);
  Duration$Companion$MIN$ObjectLiteral.prototype.constructor = Duration$Companion$MIN$ObjectLiteral;
  Duration.prototype = Object.create(ImmutableDuration.prototype);
  Duration.prototype.constructor = Duration;
  Application$StateCommand$Change.prototype = Object.create(Application$StateCommand.prototype);
  Application$StateCommand$Change.prototype.constructor = Application$StateCommand$Change;
  Application$StateCommand$Push.prototype = Object.create(Application$StateCommand.prototype);
  Application$StateCommand$Push.prototype.constructor = Application$StateCommand$Push;
  Application$StateCommand$Pop.prototype = Object.create(Application$StateCommand.prototype);
  Application$StateCommand$Pop.prototype.constructor = Application$StateCommand$Pop;
  Asset$State.prototype = Object.create(Enum.prototype);
  Asset$State.prototype.constructor = Asset$State;
  Asset.prototype = Object.create(Disposable.prototype);
  Asset.prototype.constructor = Asset;
  DrawSurface$TextParams$Anchor.prototype = Object.create(Enum.prototype);
  DrawSurface$TextParams$Anchor.prototype.constructor = DrawSurface$TextParams$Anchor;
  Font.prototype = Object.create(Disposable.prototype);
  Font.prototype.constructor = Font;
  Image_0.prototype = Object.create(Disposable.prototype);
  Image_0.prototype.constructor = Image_0;
  Screen$Transform$Scale.prototype = Object.create(Screen$Transform.prototype);
  Screen$Transform$Scale.prototype.constructor = Screen$Transform$Scale;
  Screen$Transform$Translate.prototype = Object.create(Screen$Transform.prototype);
  Screen$Transform$Translate.prototype.constructor = Screen$Transform$Translate;
  Screen$Transform$Composite.prototype = Object.create(Screen$Transform.prototype);
  Screen$Transform$Composite.prototype.constructor = Screen$Transform$Composite;
  Key.prototype = Object.create(Enum.prototype);
  Key.prototype.constructor = Key;
  Button.prototype = Object.create(Enum.prototype);
  Button.prototype.constructor = Button;
  AudioHandle.prototype = Object.create(Disposable.prototype);
  AudioHandle.prototype.constructor = AudioHandle;
  Music.prototype = Object.create(Disposable.prototype);
  Music.prototype.constructor = Music;
  SoundHandle.prototype = Object.create(Disposable.prototype);
  SoundHandle.prototype.constructor = SoundHandle;
  Sound.prototype = Object.create(Disposable.prototype);
  Sound.prototype.constructor = Sound;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  function ObservableEvent(onAdded) {
    this.onAdded_tlnqc7$_0 = onAdded;
    this.observers = ArrayList_init();
  }
  ObservableEvent.prototype.plusAssign_qlkmfe$ = function (observer) {
    this.observers.add_11rb$(observer);
    this.onAdded_tlnqc7$_0();
  };
  function ObservableEvent$plusAssign$lambda(closure$scopedObserver, this$ObservableEvent) {
    return function () {
      this$ObservableEvent.minusAssign_qlkmfe$(closure$scopedObserver.observer_8be2vx$);
      return Unit;
    };
  }
  disposable$ObjectLiteral.prototype = Object.create(Disposable.prototype);
  disposable$ObjectLiteral.prototype.constructor = disposable$ObjectLiteral;
  function disposable$ObjectLiteral(closure$block, parent) {
    this.closure$block = closure$block;
    Disposable.call(this, parent);
  }
  disposable$ObjectLiteral.prototype.onDisposed = function () {
    this.closure$block();
  };
  disposable$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Disposable]
  };
  ObservableEvent.prototype.plusAssign_fyy9ck$ = function (scopedObserver) {
    new disposable$ObjectLiteral(ObservableEvent$plusAssign$lambda(scopedObserver, this), scopedObserver.scope_8be2vx$);
    this.plusAssign_qlkmfe$(scopedObserver.observer_8be2vx$);
  };
  ObservableEvent.prototype.minusAssign_qlkmfe$ = function (observer) {
    this.observers.remove_11rb$(observer);
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
  function ScopedObserver(scope, observer) {
    this.scope_8be2vx$ = scope;
    this.observer_8be2vx$ = observer;
  }
  ScopedObserver.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ScopedObserver',
    interfaces: []
  };
  function ImmutableCircle() {
  }
  Object.defineProperty(ImmutableCircle.prototype, 'x', {
    get: function () {
      return this.center.x;
    }
  });
  Object.defineProperty(ImmutableCircle.prototype, 'y', {
    get: function () {
      return this.center.y;
    }
  });
  Object.defineProperty(ImmutableCircle.prototype, 'area', {
    get: function () {
      return math.PI * this.radius * this.radius;
    }
  });
  ImmutableCircle.prototype.toBoundingRect = function () {
    return Rect_init_1(this.center.x - this.radius, this.center.y - this.radius, 2 * this.radius, 2 * this.radius);
  };
  ImmutableCircle.prototype.intersects_kl57j9$ = function (other) {
    var radiiSum = other.radius + this.radius;
    return other.center.minus_cz6rql$(this.center).len2() < radiiSum * radiiSum;
  };
  ImmutableCircle.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ImmutableCircle)) {
      return ((tmp$ = this.center) != null ? tmp$.equals(other.center) : null) && this.radius === other.radius;
    }
    return false;
  };
  ImmutableCircle.prototype.hashCode = function () {
    return this.center.hashCode() + (31 * hashCode(this.radius) | 0) | 0;
  };
  ImmutableCircle.prototype.toString = function () {
    return 'Circle { (' + this.x + ', ' + this.y + '), ' + this.radius + ' }';
  };
  ImmutableCircle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ImmutableCircle',
    interfaces: [ImmutableShape]
  };
  function Circle(center, radius) {
    Circle$Companion_getInstance();
    ImmutableCircle.call(this);
    this.center_3c4fis$_0 = Pt2_init_1(center);
    this.radius_8cvegn$_0 = radius;
  }
  function Circle$Companion() {
    Circle$Companion_instance = this;
    this.EMPTY = new Circle$Companion$EMPTY$ObjectLiteral();
  }
  function Circle$Companion$EMPTY$ObjectLiteral() {
    ImmutableCircle.call(this);
    this.center_ng4ab8$_0 = Pt2$Companion_getInstance().ZERO;
    this.radius_sgv993$_0 = 0.0;
  }
  Object.defineProperty(Circle$Companion$EMPTY$ObjectLiteral.prototype, 'center', {
    get: function () {
      return this.center_ng4ab8$_0;
    }
  });
  Object.defineProperty(Circle$Companion$EMPTY$ObjectLiteral.prototype, 'radius', {
    get: function () {
      return this.radius_sgv993$_0;
    }
  });
  Circle$Companion$EMPTY$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ImmutableCircle]
  };
  Circle$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Circle$Companion_instance = null;
  function Circle$Companion_getInstance() {
    if (Circle$Companion_instance === null) {
      new Circle$Companion();
    }
    return Circle$Companion_instance;
  }
  Object.defineProperty(Circle.prototype, 'center', {
    get: function () {
      return this.center_3c4fis$_0;
    }
  });
  Object.defineProperty(Circle.prototype, 'radius', {
    get: function () {
      return this.radius_8cvegn$_0;
    },
    set: function (radius) {
      this.radius_8cvegn$_0 = radius;
    }
  });
  Object.defineProperty(Circle.prototype, 'x', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableCircle.prototype, 'x');
    },
    set: function (value) {
      this.center.x = value;
    }
  });
  Object.defineProperty(Circle.prototype, 'y', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableCircle.prototype, 'y');
    },
    set: function (value) {
      this.center.y = value;
    }
  });
  Circle.prototype.set_kl57j9$ = function (other) {
    this.center.set_cz6rql$(other.center);
    this.radius = other.radius;
  };
  Circle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Circle',
    interfaces: [ImmutableCircle]
  };
  function Circle_init($this) {
    $this = $this || Object.create(Circle.prototype);
    Circle.call($this, Pt2$Companion_getInstance().ZERO, 0.0);
    return $this;
  }
  function Circle_init_0(radius, $this) {
    $this = $this || Object.create(Circle.prototype);
    Circle.call($this, Pt2$Companion_getInstance().ZERO, radius);
    return $this;
  }
  function Circle_init_1(x, y, radius, $this) {
    $this = $this || Object.create(Circle.prototype);
    Circle.call($this, new Pt2(x, y), radius);
    return $this;
  }
  function Circle_init_2(x, y, radius, $this) {
    $this = $this || Object.create(Circle.prototype);
    Circle.call($this, Pt2_init_0(x, y), radius);
    return $this;
  }
  function Circle_init_3(other, $this) {
    $this = $this || Object.create(Circle.prototype);
    Circle.call($this, other.center, other.radius);
    return $this;
  }
  function ImmutableShape() {
  }
  ImmutableShape.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableShape',
    interfaces: []
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
  Object.defineProperty(ImmutableRect.prototype, 'x2', {
    get: function () {
      return this.x + this.w;
    }
  });
  Object.defineProperty(ImmutableRect.prototype, 'y2', {
    get: function () {
      return this.y + this.h;
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
  ImmutableRect.prototype.toBoundingRect = function () {
    return this;
  };
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
    interfaces: [ImmutableShape]
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
  Object.defineProperty(Rect.prototype, 'x2', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableRect.prototype, 'x2');
    },
    set: function (value) {
      this.size.x = value - this.x;
    }
  });
  Object.defineProperty(Rect.prototype, 'y2', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableRect.prototype, 'y2');
    },
    set: function (value) {
      this.size.y = value - this.y;
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
  function ImmutableSquare() {
  }
  Object.defineProperty(ImmutableSquare.prototype, 'x', {
    get: function () {
      return this.pos.x;
    }
  });
  Object.defineProperty(ImmutableSquare.prototype, 'y', {
    get: function () {
      return this.pos.y;
    }
  });
  Object.defineProperty(ImmutableSquare.prototype, 'x2', {
    get: function () {
      return this.x + this.side;
    }
  });
  Object.defineProperty(ImmutableSquare.prototype, 'y2', {
    get: function () {
      return this.y + this.side;
    }
  });
  Object.defineProperty(ImmutableSquare.prototype, 'center', {
    get: function () {
      return this.toBoundingRect().center;
    }
  });
  Object.defineProperty(ImmutableSquare.prototype, 'area', {
    get: function () {
      return this.side * this.side;
    }
  });
  ImmutableSquare.prototype.toBoundingRect = function () {
    return Rect_init_1(this.x, this.y, this.side, this.side);
  };
  ImmutableSquare.prototype.equals = function (other) {
    var tmp$;
    if (Kotlin.isType(other, ImmutableSquare)) {
      return ((tmp$ = this.pos) != null ? tmp$.equals(other.pos) : null) && this.side === other.side;
    }
    return false;
  };
  ImmutableSquare.prototype.hashCode = function () {
    return this.pos.hashCode() + (31 * hashCode(this.side) | 0) | 0;
  };
  ImmutableSquare.prototype.toString = function () {
    return 'Square { (' + this.x + ', ' + this.y + '), (' + this.side + ', ' + this.side + ') }';
  };
  ImmutableSquare.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ImmutableSquare',
    interfaces: [ImmutableShape]
  };
  function Square(pos, side) {
    Square$Companion_getInstance();
    ImmutableSquare.call(this);
    this.pos_iqqws6$_0 = Pt2_init_1(pos);
    this.side_cqqin5$_0 = side;
  }
  function Square$Companion() {
    Square$Companion_instance = this;
    this.EMPTY = new Square$Companion$EMPTY$ObjectLiteral();
  }
  function Square$Companion$EMPTY$ObjectLiteral() {
    ImmutableSquare.call(this);
    this.pos_lqf71g$_0 = Pt2$Companion_getInstance().ZERO;
    this.side_yilu9x$_0 = 0.0;
  }
  Object.defineProperty(Square$Companion$EMPTY$ObjectLiteral.prototype, 'pos', {
    get: function () {
      return this.pos_lqf71g$_0;
    }
  });
  Object.defineProperty(Square$Companion$EMPTY$ObjectLiteral.prototype, 'side', {
    get: function () {
      return this.side_yilu9x$_0;
    }
  });
  Square$Companion$EMPTY$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ImmutableSquare]
  };
  Square$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Square$Companion_instance = null;
  function Square$Companion_getInstance() {
    if (Square$Companion_instance === null) {
      new Square$Companion();
    }
    return Square$Companion_instance;
  }
  Object.defineProperty(Square.prototype, 'pos', {
    get: function () {
      return this.pos_iqqws6$_0;
    }
  });
  Object.defineProperty(Square.prototype, 'side', {
    get: function () {
      return this.side_cqqin5$_0;
    },
    set: function (side) {
      this.side_cqqin5$_0 = side;
    }
  });
  Object.defineProperty(Square.prototype, 'x', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableSquare.prototype, 'x');
    },
    set: function (value) {
      this.pos.x = value;
    }
  });
  Object.defineProperty(Square.prototype, 'y', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableSquare.prototype, 'y');
    },
    set: function (value) {
      this.pos.y = value;
    }
  });
  Object.defineProperty(Square.prototype, 'x2', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableSquare.prototype, 'x2');
    },
    set: function (value) {
      this.side = value - this.x;
    }
  });
  Object.defineProperty(Square.prototype, 'y2', {
    get: function () {
      return Kotlin.callGetter(this, ImmutableSquare.prototype, 'y2');
    },
    set: function (value) {
      this.side = value - this.y;
    }
  });
  Square.prototype.set_sabe7m$ = function (other) {
    this.pos.set_cz6rql$(other.pos);
    this.side = other.side;
  };
  Square.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Square',
    interfaces: [ImmutableSquare]
  };
  function Square_init($this) {
    $this = $this || Object.create(Square.prototype);
    Square.call($this, Pt2$Companion_getInstance().ZERO, 0.0);
    return $this;
  }
  function Square_init_0(side, $this) {
    $this = $this || Object.create(Square.prototype);
    Square.call($this, Pt2$Companion_getInstance().ZERO, side);
    return $this;
  }
  function Square_init_1(x, y, side, $this) {
    $this = $this || Object.create(Square.prototype);
    Square.call($this, new Pt2(x, y), side);
    return $this;
  }
  function Square_init_2(x, y, side, $this) {
    $this = $this || Object.create(Square.prototype);
    Square.call($this, Pt2_init_0(x, y), side);
    return $this;
  }
  function Square_init_3(other, $this) {
    $this = $this || Object.create(Square.prototype);
    Square.call($this, other.pos, other.side);
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
  function Colors() {
    Colors_instance = this;
    this.WHITE = new Color(255, 255, 255);
    this.BLACK = new Color(0, 0, 0);
    this.TRANSPARENT = new Color(0, 0, 0, 0);
    this.RED = new Color(255, 0, 0);
    this.GREEN = new Color(0, 255, 0);
    this.BLUE = new Color(0, 0, 255);
    this.YELLOW = new Color(255, 255, 0);
    this.MAGENTA = new Color(255, 0, 255);
    this.CYAN = new Color(0, 255, 255);
    this.GREY64 = new Color(64, 64, 64);
    this.GREY128 = new Color(128, 128, 128);
    this.GREY192 = new Color(192, 192, 192);
  }
  Colors.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Colors',
    interfaces: []
  };
  var Colors_instance = null;
  function Colors_getInstance() {
    if (Colors_instance === null) {
      new Colors();
    }
    return Colors_instance;
  }
  var HALF_PI;
  var QUARTER_PI;
  function max_0(a, b) {
    return a > b ? a : b;
  }
  function min(a, b) {
    return a < b ? a : b;
  }
  function clamp($receiver, min_0, max) {
    return max_0(min_0, min(max, $receiver));
  }
  function clamp_0($receiver, min, max) {
    var b = Math_0.min(max, $receiver);
    return Math_0.max(min, b);
  }
  function clamp_1($receiver, min, max) {
    var b = max.compareTo_11rb$($receiver) <= 0 ? max : $receiver;
    return min.compareTo_11rb$(b) >= 0 ? min : b;
  }
  function clamp_2($receiver, min, max) {
    var b = Math_0.min(max, $receiver);
    return Math_0.max(min, b);
  }
  function clamp_3($receiver, min, max) {
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
  Pt2.prototype.set_dleff0$ = function (x, y) {
    this.x = x;
    this.y = y;
  };
  Pt2.prototype.set_vux9f0$ = function (x, y) {
    this.x = x;
    this.y = y;
  };
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
  function AlreadyDisposedException(msg) {
    Exception_init(msg, this);
    this.name = 'AlreadyDisposedException';
  }
  AlreadyDisposedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AlreadyDisposedException',
    interfaces: [Exception]
  };
  function ImmutableDisposable() {
  }
  ImmutableDisposable.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableDisposable',
    interfaces: []
  };
  function Disposable(parent) {
    if (parent === void 0)
      parent = null;
    if (parent == null)
      Disposer_getInstance().register_lva2q1$(this);
    else
      Disposer_getInstance().register_yysbnb$(parent, this);
    this._disposed_r1irgc$_0 = false;
  }
  Object.defineProperty(Disposable.prototype, 'disposed', {
    get: function () {
      return this._disposed_r1irgc$_0;
    }
  });
  Disposable.prototype.dispose_8be2vx$ = function () {
    this._disposed_r1irgc$_0 = true;
    this.onDisposed();
  };
  Disposable.prototype.onDisposed = function () {
  };
  Disposable.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Disposable',
    interfaces: [ImmutableDisposable]
  };
  function setParent($receiver, parent) {
    Disposer_getInstance().reparent_saxzu4$(parent, $receiver);
    return $receiver;
  }
  var use = defineInlineFunction('kross2d.bitspittle.kross2d.core.memory.use_glrkcs$', wrapFunction(function () {
    var AlreadyDisposedException_init = _.bitspittle.kross2d.core.memory.AlreadyDisposedException;
    var memory = _.bitspittle.kross2d.core.memory;
    return function ($receiver, block) {
      if ($receiver.disposed) {
        throw new AlreadyDisposedException_init("Can't use disposed Disposable");
      }
      try {
        block($receiver);
      }
      finally {
        memory.Disposer.dispose_kknw4o$($receiver);
      }
    };
  }));
  var disposable = defineInlineFunction('kross2d.bitspittle.kross2d.core.memory.disposable_o14v8n$', wrapFunction(function () {
    var Disposable = _.bitspittle.kross2d.core.memory.Disposable;
    var Kind_CLASS = Kotlin.Kind.CLASS;
    disposable$ObjectLiteral.prototype = Object.create(Disposable.prototype);
    disposable$ObjectLiteral.prototype.constructor = disposable$ObjectLiteral;
    function disposable$ObjectLiteral(closure$block, parent) {
      this.closure$block = closure$block;
      Disposable.call(this, parent);
    }
    disposable$ObjectLiteral.prototype.onDisposed = function () {
      this.closure$block();
    };
    disposable$ObjectLiteral.$metadata$ = {
      kind: Kind_CLASS,
      interfaces: [Disposable]
    };
    return function (block) {
      return new disposable$ObjectLiteral(block);
    };
  }));
  var disposable_0 = defineInlineFunction('kross2d.bitspittle.kross2d.core.memory.disposable_8qc67b$', wrapFunction(function () {
    var Disposable = _.bitspittle.kross2d.core.memory.Disposable;
    var Kind_CLASS = Kotlin.Kind.CLASS;
    disposable$ObjectLiteral.prototype = Object.create(Disposable.prototype);
    disposable$ObjectLiteral.prototype.constructor = disposable$ObjectLiteral;
    function disposable$ObjectLiteral(closure$block, parent) {
      this.closure$block = closure$block;
      Disposable.call(this, parent);
    }
    disposable$ObjectLiteral.prototype.onDisposed = function () {
      this.closure$block();
    };
    disposable$ObjectLiteral.$metadata$ = {
      kind: Kind_CLASS,
      interfaces: [Disposable]
    };
    return function (parent, block) {
      return new disposable$ObjectLiteral(block, parent);
    };
  }));
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  function Disposer() {
    Disposer_instance = this;
    this.roots_0 = ArrayList_init();
    this.childrenOf_0 = LinkedHashMap_init();
    this.parentOf_0 = LinkedHashMap_init();
  }
  function Disposer$IllegalDisposerOperation(msg) {
    Exception_init(msg, this);
    this.name = 'Disposer$IllegalDisposerOperation';
  }
  Disposer$IllegalDisposerOperation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IllegalDisposerOperation',
    interfaces: [Exception]
  };
  Disposer.prototype.register_lva2q1$ = function (disposable) {
    this.verifyNewDisposable_0(disposable);
    this.roots_0.add_11rb$(disposable);
  };
  Disposer.prototype.register_yysbnb$ = function (parent, child) {
    this.verifyNewDisposable_0(child);
    this.reparent_saxzu4$(parent, child);
  };
  Disposer.prototype.verifyNewDisposable_0 = function (disposable) {
    if (disposable.disposed) {
      throw new AlreadyDisposedException("Can't register a pre-disposed disposable");
    }
    if (this.parentOf_0.get_11rb$(disposable) != null || this.roots_0.contains_11rb$(disposable)) {
      throw new Disposer$IllegalDisposerOperation('A disposable can only be registered once');
    }
  };
  Disposer.prototype.reparent_saxzu4$ = function (newParent, child) {
    var tmp$;
    if (newParent.disposed) {
      throw new AlreadyDisposedException('Tried to reparent onto a disposable that has already been disposed');
    }
    if (child.disposed) {
      throw new AlreadyDisposedException('Tried to reparent a disposable that has already been disposed');
    }
    if (this.isSelfOrDescendantOf_0(newParent, child)) {
      throw new Disposer$IllegalDisposerOperation("Can't reparent a disposable underneath itself.");
    }
    if ((tmp$ = this.parentOf_0.get_11rb$(child)) != null) {
      if (tmp$ === newParent) {
        return;
      }
    }
    this.removeFromSiblings_0(child);
    this.parentOf_0.put_xwzc9p$(child, newParent);
    var $receiver = this.childrenOf_0;
    var tmp$_0;
    var value = $receiver.get_11rb$(newParent);
    if (value == null) {
      var answer = ArrayList_init();
      $receiver.put_xwzc9p$(newParent, answer);
      tmp$_0 = answer;
    }
     else {
      tmp$_0 = value;
    }
    tmp$_0.add_11rb$(child);
  };
  Disposer.prototype.transferChildren_3edgyc$ = function (from, to) {
    var tmp$;
    if (from === to) {
      return;
    }
    if (this.isDescendantOf_0(to, from)) {
      throw new Disposer$IllegalDisposerOperation("Can't transfer to a new parent owned by the existing parent.");
    }
    var $receiver = this.childrenOf_0;
    var tmp$_0;
    var value = $receiver.get_11rb$(to);
    if (value == null) {
      var answer = ArrayList_init();
      $receiver.put_xwzc9p$(to, answer);
      tmp$_0 = answer;
    }
     else {
      tmp$_0 = value;
    }
    var newChildren = tmp$_0;
    if ((tmp$ = this.childrenOf_0.get_11rb$(from)) != null) {
      newChildren.addAll_brywnq$(tmp$);
    }
    this.childrenOf_0.remove_11rb$(from);
    var tmp$_1;
    tmp$_1 = newChildren.iterator();
    while (tmp$_1.hasNext()) {
      var element = tmp$_1.next();
      this.parentOf_0.put_xwzc9p$(element, to);
    }
  };
  Disposer.prototype.dispose_kknw4o$ = function (disposable) {
    if (disposable.disposed) {
      throw new AlreadyDisposedException('Tried to dispose a box that has already been disposed');
    }
    this.handleDisposeChildren_0(disposable);
    this.removeFromSiblings_0(disposable);
    this.handleDispose_0(disposable);
  };
  Disposer.prototype.tryDispose_kknw4o$ = function (disposable) {
    if (!disposable.disposed) {
      this.dispose_kknw4o$(disposable);
      return true;
    }
    return false;
  };
  Disposer.prototype.removeFromSiblings_0 = function (child) {
    var tmp$, tmp$_0;
    var tmp$_1;
    if ((tmp$ = this.parentOf_0.remove_11rb$(child)) != null) {
      var $receiver = ensureNotNull(this.childrenOf_0.get_11rb$(tmp$));
      $receiver.remove_11rb$(child);
      if ($receiver.isEmpty()) {
        this.childrenOf_0.remove_11rb$(tmp$);
      }
      tmp$_1 = Unit;
    }
     else
      tmp$_1 = null;
    (tmp$_0 = tmp$_1) != null ? tmp$_0 : this.roots_0.remove_11rb$(child);
  };
  function Disposer$freeRemaining$lambda(it) {
    println(it);
    return Unit;
  }
  Disposer.prototype.freeRemaining_ep0k5p$ = function (notify) {
    if (notify === void 0)
      notify = Disposer$freeRemaining$lambda;
    if (this.roots_0.isEmpty())
      return;
    var strBuilder = StringBuilder_init();
    strBuilder.append_gw00v9$('Some disposables were not cleaned up:\n');
    var tmp$;
    tmp$ = this.roots_0.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      strBuilder.append_s8itvh$(10);
      this.addIndentedNames_0(strBuilder, '', element);
    }
    notify(strBuilder.toString());
    while (!this.roots_0.isEmpty()) {
      this.dispose_kknw4o$(this.roots_0.get_za3lpa$(0));
    }
  };
  Disposer.prototype.handleDisposeChildren_0 = function (parent) {
    var siblings = this.childrenOf_0.remove_11rb$(parent);
    if (siblings != null) {
      var tmp$;
      tmp$ = siblings.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        this.parentOf_0.remove_11rb$(element);
        this.handleDisposeChildren_0(element);
        this.handleDispose_0(element);
      }
    }
  };
  Disposer.prototype.handleDispose_0 = function (disposable) {
    if (disposable.disposed) {
      throw new AlreadyDisposedException('Attempting to dispose a pre-disposed disposable');
    }
    disposable.dispose_8be2vx$();
  };
  Disposer.prototype.addIndentedNames_0 = function (strBuilder, indent, disposable) {
    var tmp$;
    strBuilder.append_gw00v9$(indent).append_gw00v9$(disposable.toString()).append_s8itvh$(10);
    var nextIndent = indent + '  ';
    if ((tmp$ = this.childrenOf_0.get_11rb$(disposable)) != null) {
      var tmp$_0;
      tmp$_0 = tmp$.iterator();
      while (tmp$_0.hasNext()) {
        var element = tmp$_0.next();
        this.addIndentedNames_0(strBuilder, nextIndent, element);
      }
    }
  };
  Disposer.prototype.isSelfOrDescendantOf_0 = function ($receiver, potentialAncestor) {
    return $receiver === potentialAncestor || this.isDescendantOf_0($receiver, potentialAncestor);
  };
  var Map = Kotlin.kotlin.collections.Map;
  Disposer.prototype.isDescendantOf_0 = function ($receiver, potentialAncestor) {
    var tmp$;
    var $receiver_0 = this.parentOf_0;
    var tmp$_0;
    var curr = (Kotlin.isType(tmp$_0 = $receiver_0, Map) ? tmp$_0 : throwCCE()).get_11rb$($receiver);
    while (curr != null) {
      if (curr === potentialAncestor) {
        return true;
      }
      var $receiver_1 = this.parentOf_0;
      var key = curr;
      var tmp$_1;
      tmp$ = (Kotlin.isType(tmp$_1 = $receiver_1, Map) ? tmp$_1 : throwCCE()).get_11rb$(key);
      if (tmp$ == null) {
        break;
      }
      curr = tmp$;
    }
    return false;
  };
  Disposer.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Disposer',
    interfaces: []
  };
  var Disposer_instance = null;
  function Disposer_getInstance() {
    if (Disposer_instance === null) {
      new Disposer();
    }
    return Disposer_instance;
  }
  function Rc(create) {
    this.create_0 = create;
    this.value_3ujlqa$_0 = null;
    this.counter_0 = 0;
  }
  Object.defineProperty(Rc.prototype, 'value', {
    get: function () {
      return this.value_3ujlqa$_0;
    },
    set: function (value) {
      this.value_3ujlqa$_0 = value;
    }
  });
  Rc.prototype.inc = function () {
    if (this.counter_0 === 0) {
      this.value = this.create_0();
    }
    this.counter_0 = this.counter_0 + 1 | 0;
  };
  Rc.prototype.dec = function () {
    if (this.counter_0 === 0) {
      throw IllegalStateException_init('Unbalanced ref counting; dec() called more than inc()');
    }
    this.counter_0 = this.counter_0 - 1 | 0;
    if (this.counter_0 === 0) {
      Disposer_getInstance().dispose_kknw4o$(ensureNotNull(this.value));
      this.value = null;
    }
  };
  Rc.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Rc',
    interfaces: []
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
  function max_1(a, b) {
    return a.nanos > b.nanos ? a : b;
  }
  function min_0(a, b) {
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
  GameState.prototype.enter_ahvl4o$ = function (ctx) {
  };
  GameState.$metadata$ = {
    kind: Kind_INTERFACE,
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
    this.stateChangeRequest_0 = new Application$StateCommand$Push(initialState);
    this.stateStack_0 = ArrayList_init();
    var keyboard = new DefaultKeyboard();
    this.backend_0.keyPressed.plusAssign_qlkmfe$(Application_init$lambda(keyboard));
    this.backend_0.keyReleased.plusAssign_qlkmfe$(Application_init$lambda_0(keyboard));
    var mouse = new DefaultMouse();
    this.backend_0.mouseMoved.plusAssign_qlkmfe$(Application_init$lambda_1(mouse));
    this.backend_0.buttonPressed.plusAssign_qlkmfe$(Application_init$lambda_2(mouse));
    this.backend_0.buttonReleased.plusAssign_qlkmfe$(Application_init$lambda_3(mouse));
    var scopes = new Application_init$ObjectLiteral();
    var app = new Application_init$ObjectLiteral_0(this);
    var timer = new DefaultTimer();
    var assetLoader = new AssetLoader(params.assetsRoot, scopes);
    var initContext = new Application_init$ObjectLiteral_1(assetLoader, this, timer, scopes);
    var updateContext = new Application_init$ObjectLiteral_2(app, assetLoader, this, keyboard, mouse, timer, scopes);
    var drawContext = new Application_init$ObjectLiteral_3(this, timer);
    var frameStart = {v: null};
    var currentState = {v: null};
    this.backend_0.runForever_o14v8n$(Application_init$lambda_4(this, frameStart, timer, scopes, currentState, initContext, updateContext, keyboard, mouse, drawContext));
    this.backend_0.onQuit_o14v8n$(Application_init$lambda_5(scopes));
  }
  function Application$StateCommand() {
  }
  function Application$StateCommand$Change(gameState) {
    Application$StateCommand.call(this);
    this.gameState = gameState;
  }
  Application$StateCommand$Change.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Change',
    interfaces: [Application$StateCommand]
  };
  function Application$StateCommand$Push(gameState) {
    Application$StateCommand.call(this);
    this.gameState = gameState;
  }
  Application$StateCommand$Push.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Push',
    interfaces: [Application$StateCommand]
  };
  function Application$StateCommand$Pop() {
    Application$StateCommand$Pop_instance = this;
    Application$StateCommand.call(this);
  }
  Application$StateCommand$Pop.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Pop',
    interfaces: [Application$StateCommand]
  };
  var Application$StateCommand$Pop_instance = null;
  function Application$StateCommand$Pop_getInstance() {
    if (Application$StateCommand$Pop_instance === null) {
      new Application$StateCommand$Pop();
    }
    return Application$StateCommand$Pop_instance;
  }
  Application$StateCommand.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'StateCommand',
    interfaces: []
  };
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
  function Application_init$lambda_1(closure$mouse) {
    return function (pos) {
      closure$mouse.pos.set_cz6rql$(pos);
      return Unit;
    };
  }
  function Application_init$lambda_2(closure$mouse) {
    return function (button) {
      closure$mouse.handleButton_l874q9$(button, true);
      return Unit;
    };
  }
  function Application_init$lambda_3(closure$mouse) {
    return function (button) {
      closure$mouse.handleButton_l874q9$(button, false);
      return Unit;
    };
  }
  disposable$ObjectLiteral_0.prototype = Object.create(Disposable.prototype);
  disposable$ObjectLiteral_0.prototype.constructor = disposable$ObjectLiteral_0;
  function disposable$ObjectLiteral_0(closure$block, parent) {
    this.closure$block = closure$block;
    Disposable.call(this, parent);
  }
  disposable$ObjectLiteral_0.prototype.onDisposed = function () {
    this.closure$block();
  };
  disposable$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Disposable]
  };
  function Application_init$ObjectLiteral() {
    this.app_2v28a9$_0 = new disposable$ObjectLiteral_0(Application_init$ObjectLiteral$app$lambda);
    this.currState_ytppip$_0 = new disposable$ObjectLiteral_0(Application_init$ObjectLiteral$currState$lambda);
  }
  Object.defineProperty(Application_init$ObjectLiteral.prototype, 'app', {
    get: function () {
      return this.app_2v28a9$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral.prototype, 'currState', {
    get: function () {
      return this.currState_ytppip$_0;
    },
    set: function (currState) {
      this.currState_ytppip$_0 = currState;
    }
  });
  function Application_init$ObjectLiteral$app$lambda() {
    return Unit;
  }
  function Application_init$ObjectLiteral$currState$lambda() {
    return Unit;
  }
  Application_init$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Scopes]
  };
  function Application_init$ObjectLiteral_0(this$Application) {
    this.this$Application = this$Application;
  }
  Application_init$ObjectLiteral_0.prototype.changeState_qsnwm5$ = function (state) {
    this.this$Application.stateChangeRequest_0 = new Application$StateCommand$Change(state);
  };
  Application_init$ObjectLiteral_0.prototype.pushState_qsnwm5$ = function (state) {
    this.this$Application.stateChangeRequest_0 = new Application$StateCommand$Push(state);
  };
  Application_init$ObjectLiteral_0.prototype.popState = function () {
    if (this.this$Application.stateStack_0.size === 1) {
      throw IllegalStateException_init('No more states can be popped. Use app.quit() or app.changeState() instead?');
    }
    this.this$Application.stateChangeRequest_0 = Application$StateCommand$Pop_getInstance();
  };
  Application_init$ObjectLiteral_0.prototype.quit = function () {
    this.this$Application.backend_0.quit();
  };
  Application_init$ObjectLiteral_0.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [ApplicationFacade]
  };
  function Application_init$ObjectLiteral_1(closure$assetLoader, this$Application, closure$timer, closure$scopes) {
    this.assetLoader_ttqb77$_0 = closure$assetLoader;
    this.screen_4j6xmw$_0 = this$Application.backend_0.screen;
    this.timer_rwuagb$_0 = closure$timer;
    this.scopes_4knafv$_0 = closure$scopes;
  }
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
  Object.defineProperty(Application_init$ObjectLiteral_1.prototype, 'timer', {
    get: function () {
      return this.timer_rwuagb$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_1.prototype, 'scopes', {
    get: function () {
      return this.scopes_4knafv$_0;
    }
  });
  Application_init$ObjectLiteral_1.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [EnterContext]
  };
  function Application_init$ObjectLiteral_2(closure$app, closure$assetLoader, this$Application, closure$keyboard, closure$mouse, closure$timer, closure$scopes) {
    this.app_2v28a9$_0 = closure$app;
    this.assetLoader_ttqb77$_0 = closure$assetLoader;
    this.screen_4j6xmw$_0 = this$Application.backend_0.screen;
    this.keyboard_jnaahp$_0 = closure$keyboard;
    this.mouse_ooz37v$_0 = closure$mouse;
    this.timer_rwuagb$_0 = closure$timer;
    this.scopes_4knafv$_0 = closure$scopes;
  }
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'app', {
    get: function () {
      return this.app_2v28a9$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'assetLoader', {
    get: function () {
      return this.assetLoader_ttqb77$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'screen', {
    get: function () {
      return this.screen_4j6xmw$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'keyboard', {
    get: function () {
      return this.keyboard_jnaahp$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'mouse', {
    get: function () {
      return this.mouse_ooz37v$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'timer', {
    get: function () {
      return this.timer_rwuagb$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_2.prototype, 'scopes', {
    get: function () {
      return this.scopes_4knafv$_0;
    }
  });
  Application_init$ObjectLiteral_2.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [UpdateContext]
  };
  function Application_init$ObjectLiteral_3(this$Application, closure$timer) {
    this.screen_4j6xmw$_0 = this$Application.backend_0.screen;
    this.timer_rwuagb$_0 = closure$timer;
  }
  Object.defineProperty(Application_init$ObjectLiteral_3.prototype, 'screen', {
    get: function () {
      return this.screen_4j6xmw$_0;
    }
  });
  Object.defineProperty(Application_init$ObjectLiteral_3.prototype, 'timer', {
    get: function () {
      return this.timer_rwuagb$_0;
    }
  });
  Application_init$ObjectLiteral_3.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [DrawContext]
  };
  function Application_init$lambda$lambda$lambda$lambda() {
    return Unit;
  }
  function Application_init$lambda_4(this$Application, closure$frameStart, closure$timer, closure$scopes, closure$currentState, closure$initContext, closure$updateContext, closure$keyboard, closure$mouse, closure$drawContext) {
    return function () {
      var tmp$;
      if ((tmp$ = this$Application.stateChangeRequest_0) != null) {
        var this$Application_0 = this$Application;
        var closure$frameStart_0 = closure$frameStart;
        var closure$timer_0 = closure$timer;
        var closure$scopes_0 = closure$scopes;
        var closure$currentState_0 = closure$currentState;
        var closure$initContext_0 = closure$initContext;
        if (Kotlin.isType(tmp$, Application$StateCommand$Change)) {
          this$Application_0.stateStack_0.removeAt_za3lpa$(get_lastIndex(this$Application_0.stateStack_0));
          this$Application_0.stateStack_0.add_11rb$(tmp$.gameState);
        }
         else if (Kotlin.isType(tmp$, Application$StateCommand$Push))
          this$Application_0.stateStack_0.add_11rb$(tmp$.gameState);
        else if (Kotlin.isType(tmp$, Application$StateCommand$Pop))
          this$Application_0.stateStack_0.removeAt_za3lpa$(get_lastIndex(this$Application_0.stateStack_0));
        else
          Kotlin.noWhenBranchMatched();
        this$Application_0.stateChangeRequest_0 = null;
        var $receiver = last(this$Application_0.stateStack_0);
        closure$frameStart_0.v = Instant$Companion_getInstance().now();
        closure$timer_0.lastFrame.setFrom_evd4je$(Duration$Companion_getInstance().ZERO);
        Disposer_getInstance().dispose_kknw4o$(closure$scopes_0.currState);
        closure$scopes_0.currState = new disposable$ObjectLiteral_0(Application_init$lambda$lambda$lambda$lambda);
        closure$currentState_0.v = $receiver;
        $receiver.enter_ahvl4o$(closure$initContext_0);
      }
      var lastFrameStart = closure$frameStart.v == null ? throwUPAE('frameStart') : closure$frameStart.v;
      closure$frameStart.v = Instant$Companion_getInstance().now();
      closure$timer.lastFrame.setFrom_evd4je$((closure$frameStart.v == null ? throwUPAE('frameStart') : closure$frameStart.v).minus_cj7vob$(lastFrameStart));
      (closure$currentState.v == null ? throwUPAE('currentState') : closure$currentState.v).update_kung05$(closure$updateContext);
      closure$keyboard.step();
      closure$mouse.step();
      (closure$currentState.v == null ? throwUPAE('currentState') : closure$currentState.v).draw_glqt06$(closure$drawContext);
      return Unit;
    };
  }
  function Application_init$lambda_5(closure$scopes) {
    return function () {
      Disposer_getInstance().dispose_kknw4o$(closure$scopes.currState);
      Disposer_getInstance().dispose_kknw4o$(closure$scopes.app);
      Disposer_getInstance().freeRemaining_ep0k5p$();
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
  function Asset(parent, path) {
    Disposable.call(this, parent);
    this.path = path;
    this.state_8ymihc$_0 = Asset$State$LOADING_getInstance();
    this._onLoaded_0 = new Event(Asset$_onLoaded$lambda(this));
    this.onLoaded = this._onLoaded_0;
    this.data_k3emdl$_0 = null;
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
    Asset$State$LOADED_instance = new Asset$State('LOADED', 1);
    Asset$State$FAILED_instance = new Asset$State('FAILED', 2);
  }
  var Asset$State$LOADING_instance;
  function Asset$State$LOADING_getInstance() {
    Asset$State_initFields();
    return Asset$State$LOADING_instance;
  }
  var Asset$State$LOADED_instance;
  function Asset$State$LOADED_getInstance() {
    Asset$State_initFields();
    return Asset$State$LOADED_instance;
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
    return [Asset$State$LOADING_getInstance(), Asset$State$LOADED_getInstance(), Asset$State$FAILED_getInstance()];
  }
  Asset$State.values = Asset$State$values;
  function Asset$State$valueOf(name) {
    switch (name) {
      case 'LOADING':
        return Asset$State$LOADING_getInstance();
      case 'LOADED':
        return Asset$State$LOADED_getInstance();
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
    set: function (value) {
      this.state_8ymihc$_0 = value;
      if (this.state_8ymihc$_0 === Asset$State$LOADED_getInstance()) {
        this.fireOnLoaded_0(ensureNotNull(this.data));
      }
      this._onLoaded_0.clear();
    }
  });
  Object.defineProperty(Asset.prototype, 'data', {
    get: function () {
      return this.data_k3emdl$_0;
    },
    set: function (data) {
      this.data_k3emdl$_0 = data;
    }
  });
  Asset.prototype.setData_pm34ol$ = function (data) {
    if (this.disposed) {
      if (data != null) {
        if (data.disposed) {
          throw new AlreadyDisposedException("Can't use disposed Disposable");
        }
        package$memory.Disposer.dispose_kknw4o$(data);
      }
      return;
    }
    this.assertLoading_0();
    if (data != null) {
      Disposer_getInstance().reparent_saxzu4$(this, data);
      this.data = data;
      this.state = Asset$State$LOADED_getInstance();
    }
     else {
      this.state = Asset$State$FAILED_getInstance();
    }
  };
  Asset.prototype.fireOnLoaded_0 = function (data) {
    this._onLoaded_0.invoke_11rb$(data);
    this._onLoaded_0.clear();
  };
  Asset.prototype.notifyFailure_8be2vx$ = function () {
    if (this.disposed) {
      return;
    }
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
      if ((tmp$ = this$Asset.data) != null) {
        this$Asset.fireOnLoaded_0(tmp$);
      }
      return Unit;
    };
  }
  Asset.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Asset',
    interfaces: [Disposable]
  };
  function AssetLoader(root, scopes) {
    this.scopes_0 = scopes;
    this.backend_0 = new AssetLoaderBackend(root);
    this.cachedFonts_0 = LinkedHashMap_init();
    this.cachedImages_0 = LinkedHashMap_init();
    this.cachedSounds_0 = LinkedHashMap_init();
    this.cachedMusic_0 = LinkedHashMap_init();
  }
  function AssetLoader$loadFont$lambda$lambda$lambda(this$AssetLoader, closure$relativePath) {
    return function () {
      this$AssetLoader.cachedFonts_0.remove_11rb$(closure$relativePath);
      return Unit;
    };
  }
  disposable$ObjectLiteral_1.prototype = Object.create(Disposable.prototype);
  disposable$ObjectLiteral_1.prototype.constructor = disposable$ObjectLiteral_1;
  function disposable$ObjectLiteral_1(closure$block, parent) {
    this.closure$block = closure$block;
    Disposable.call(this, parent);
  }
  disposable$ObjectLiteral_1.prototype.onDisposed = function () {
    this.closure$block();
  };
  disposable$ObjectLiteral_1.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Disposable]
  };
  AssetLoader.prototype.loadFont_rr7ufs$ = function (relativePath, scope) {
    if (scope === void 0)
      scope = this.scopes_0.currState;
    var $receiver = this.cachedFonts_0;
    var tmp$;
    var value = $receiver.get_11rb$(relativePath);
    if (value == null) {
      var $receiver_0 = new Asset(scope, relativePath);
      this.backend_0.loadFontInto_ux1b43$($receiver_0);
      this.cachedFonts_0.put_xwzc9p$(relativePath, $receiver_0);
      new disposable$ObjectLiteral_1(AssetLoader$loadFont$lambda$lambda$lambda(this, relativePath), $receiver_0);
      var answer = $receiver_0;
      $receiver.put_xwzc9p$(relativePath, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    return tmp$;
  };
  function AssetLoader$loadImage$lambda$lambda$lambda(this$AssetLoader, closure$relativePath) {
    return function () {
      this$AssetLoader.cachedImages_0.remove_11rb$(closure$relativePath);
      return Unit;
    };
  }
  AssetLoader.prototype.loadImage_rr7ufs$ = function (relativePath, scope) {
    if (scope === void 0)
      scope = this.scopes_0.currState;
    var $receiver = this.cachedImages_0;
    var tmp$;
    var value = $receiver.get_11rb$(relativePath);
    if (value == null) {
      var $receiver_0 = new Asset(scope, relativePath);
      this.backend_0.loadImageInto_ylngmp$($receiver_0);
      this.cachedImages_0.put_xwzc9p$(relativePath, $receiver_0);
      new disposable$ObjectLiteral_1(AssetLoader$loadImage$lambda$lambda$lambda(this, relativePath), $receiver_0);
      var answer = $receiver_0;
      $receiver.put_xwzc9p$(relativePath, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    return tmp$;
  };
  function AssetLoader$loadSound$lambda$lambda$lambda(this$AssetLoader, closure$relativePath) {
    return function () {
      this$AssetLoader.cachedSounds_0.remove_11rb$(closure$relativePath);
      return Unit;
    };
  }
  AssetLoader.prototype.loadSound_rr7ufs$ = function (relativePath, scope) {
    if (scope === void 0)
      scope = this.scopes_0.currState;
    var $receiver = this.cachedSounds_0;
    var tmp$;
    var value = $receiver.get_11rb$(relativePath);
    if (value == null) {
      var $receiver_0 = new Asset(scope, relativePath);
      this.backend_0.loadSoundInto_pecovy$($receiver_0);
      this.cachedSounds_0.put_xwzc9p$(relativePath, $receiver_0);
      new disposable$ObjectLiteral_1(AssetLoader$loadSound$lambda$lambda$lambda(this, relativePath), $receiver_0);
      var answer = $receiver_0;
      $receiver.put_xwzc9p$(relativePath, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    return tmp$;
  };
  function AssetLoader$loadMusic$lambda$lambda$lambda(this$AssetLoader, closure$relativePath) {
    return function () {
      this$AssetLoader.cachedMusic_0.remove_11rb$(closure$relativePath);
      return Unit;
    };
  }
  AssetLoader.prototype.loadMusic_rr7ufs$ = function (relativePath, scope) {
    if (scope === void 0)
      scope = this.scopes_0.currState;
    var $receiver = this.cachedMusic_0;
    var tmp$;
    var value = $receiver.get_11rb$(relativePath);
    if (value == null) {
      var $receiver_0 = new Asset(scope, relativePath);
      this.backend_0.loadMusicInto_mnccdk$($receiver_0);
      this.cachedMusic_0.put_xwzc9p$(relativePath, $receiver_0);
      new disposable$ObjectLiteral_1(AssetLoader$loadMusic$lambda$lambda$lambda(this, relativePath), $receiver_0);
      var answer = $receiver_0;
      $receiver.put_xwzc9p$(relativePath, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    return tmp$;
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
  function EnterContext() {
  }
  EnterContext.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'EnterContext',
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
  function DrawSurface$ImageParams(dest, destSize) {
    if (dest === void 0)
      dest = Pt2$Companion_getInstance().ZERO;
    if (destSize === void 0)
      destSize = null;
    this.dest = dest;
    this.destSize = destSize;
  }
  DrawSurface$ImageParams.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ImageParams',
    interfaces: []
  };
  function DrawSurface$TextParams(pt, anchor, color, spacing) {
    if (pt === void 0)
      pt = Pt2$Companion_getInstance().ZERO;
    if (anchor === void 0)
      anchor = DrawSurface$TextParams$Anchor$TOP_LEFT_getInstance();
    if (color === void 0)
      color = Colors_getInstance().WHITE;
    if (spacing === void 0)
      spacing = 0.0;
    this.pt = pt;
    this.anchor = anchor;
    this.color = color;
    this.spacing = spacing;
  }
  function DrawSurface$TextParams$Anchor(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function DrawSurface$TextParams$Anchor_initFields() {
    DrawSurface$TextParams$Anchor_initFields = function () {
    };
    DrawSurface$TextParams$Anchor$TOP_LEFT_instance = new DrawSurface$TextParams$Anchor('TOP_LEFT', 0);
    DrawSurface$TextParams$Anchor$TOP_instance = new DrawSurface$TextParams$Anchor('TOP', 1);
    DrawSurface$TextParams$Anchor$TOP_RIGHT_instance = new DrawSurface$TextParams$Anchor('TOP_RIGHT', 2);
    DrawSurface$TextParams$Anchor$LEFT_instance = new DrawSurface$TextParams$Anchor('LEFT', 3);
    DrawSurface$TextParams$Anchor$CENTER_instance = new DrawSurface$TextParams$Anchor('CENTER', 4);
    DrawSurface$TextParams$Anchor$RIGHT_instance = new DrawSurface$TextParams$Anchor('RIGHT', 5);
    DrawSurface$TextParams$Anchor$BOTTOM_LEFT_instance = new DrawSurface$TextParams$Anchor('BOTTOM_LEFT', 6);
    DrawSurface$TextParams$Anchor$BOTTOM_instance = new DrawSurface$TextParams$Anchor('BOTTOM', 7);
    DrawSurface$TextParams$Anchor$BOTTOM_RIGHT_instance = new DrawSurface$TextParams$Anchor('BOTTOM_RIGHT', 8);
  }
  var DrawSurface$TextParams$Anchor$TOP_LEFT_instance;
  function DrawSurface$TextParams$Anchor$TOP_LEFT_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$TOP_LEFT_instance;
  }
  var DrawSurface$TextParams$Anchor$TOP_instance;
  function DrawSurface$TextParams$Anchor$TOP_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$TOP_instance;
  }
  var DrawSurface$TextParams$Anchor$TOP_RIGHT_instance;
  function DrawSurface$TextParams$Anchor$TOP_RIGHT_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$TOP_RIGHT_instance;
  }
  var DrawSurface$TextParams$Anchor$LEFT_instance;
  function DrawSurface$TextParams$Anchor$LEFT_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$LEFT_instance;
  }
  var DrawSurface$TextParams$Anchor$CENTER_instance;
  function DrawSurface$TextParams$Anchor$CENTER_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$CENTER_instance;
  }
  var DrawSurface$TextParams$Anchor$RIGHT_instance;
  function DrawSurface$TextParams$Anchor$RIGHT_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$RIGHT_instance;
  }
  var DrawSurface$TextParams$Anchor$BOTTOM_LEFT_instance;
  function DrawSurface$TextParams$Anchor$BOTTOM_LEFT_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$BOTTOM_LEFT_instance;
  }
  var DrawSurface$TextParams$Anchor$BOTTOM_instance;
  function DrawSurface$TextParams$Anchor$BOTTOM_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$BOTTOM_instance;
  }
  var DrawSurface$TextParams$Anchor$BOTTOM_RIGHT_instance;
  function DrawSurface$TextParams$Anchor$BOTTOM_RIGHT_getInstance() {
    DrawSurface$TextParams$Anchor_initFields();
    return DrawSurface$TextParams$Anchor$BOTTOM_RIGHT_instance;
  }
  DrawSurface$TextParams$Anchor.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Anchor',
    interfaces: [Enum]
  };
  function DrawSurface$TextParams$Anchor$values() {
    return [DrawSurface$TextParams$Anchor$TOP_LEFT_getInstance(), DrawSurface$TextParams$Anchor$TOP_getInstance(), DrawSurface$TextParams$Anchor$TOP_RIGHT_getInstance(), DrawSurface$TextParams$Anchor$LEFT_getInstance(), DrawSurface$TextParams$Anchor$CENTER_getInstance(), DrawSurface$TextParams$Anchor$RIGHT_getInstance(), DrawSurface$TextParams$Anchor$BOTTOM_LEFT_getInstance(), DrawSurface$TextParams$Anchor$BOTTOM_getInstance(), DrawSurface$TextParams$Anchor$BOTTOM_RIGHT_getInstance()];
  }
  DrawSurface$TextParams$Anchor.values = DrawSurface$TextParams$Anchor$values;
  function DrawSurface$TextParams$Anchor$valueOf(name) {
    switch (name) {
      case 'TOP_LEFT':
        return DrawSurface$TextParams$Anchor$TOP_LEFT_getInstance();
      case 'TOP':
        return DrawSurface$TextParams$Anchor$TOP_getInstance();
      case 'TOP_RIGHT':
        return DrawSurface$TextParams$Anchor$TOP_RIGHT_getInstance();
      case 'LEFT':
        return DrawSurface$TextParams$Anchor$LEFT_getInstance();
      case 'CENTER':
        return DrawSurface$TextParams$Anchor$CENTER_getInstance();
      case 'RIGHT':
        return DrawSurface$TextParams$Anchor$RIGHT_getInstance();
      case 'BOTTOM_LEFT':
        return DrawSurface$TextParams$Anchor$BOTTOM_LEFT_getInstance();
      case 'BOTTOM':
        return DrawSurface$TextParams$Anchor$BOTTOM_getInstance();
      case 'BOTTOM_RIGHT':
        return DrawSurface$TextParams$Anchor$BOTTOM_RIGHT_getInstance();
      default:throwISE('No enum constant bitspittle.kross2d.engine.graphics.DrawSurface.TextParams.Anchor.' + name);
    }
  }
  DrawSurface$TextParams$Anchor.valueOf_61zpoe$ = DrawSurface$TextParams$Anchor$valueOf;
  DrawSurface$TextParams.prototype.toTopLeft_mck3b5$ = function (produceWidth, produceHeight) {
    var tmp$;
    switch (this.anchor.name) {
      case 'TOP_LEFT':
        tmp$ = this.pt;
        break;
      case 'TOP':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(produceWidth() / 2.0, 0.0));
        break;
      case 'TOP_RIGHT':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(produceWidth(), 0.0));
        break;
      case 'LEFT':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(0.0, produceHeight() / 2.0));
        break;
      case 'CENTER':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(produceWidth() / 2.0, produceHeight() / 2.0));
        break;
      case 'RIGHT':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(produceWidth(), produceHeight() / 2.0));
        break;
      case 'BOTTOM_LEFT':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(0.0, produceHeight()));
        break;
      case 'BOTTOM':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(produceWidth() / 2.0, produceHeight()));
        break;
      case 'BOTTOM_RIGHT':
        tmp$ = this.pt.minus_nvqvy9$(new Vec2(produceWidth(), produceHeight()));
        break;
      default:tmp$ = Kotlin.noWhenBranchMatched();
        break;
    }
    return tmp$;
  };
  DrawSurface$TextParams.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TextParams',
    interfaces: []
  };
  DrawSurface.prototype.drawImage_pq2ml$ = function (image, params, callback$default) {
    if (params === void 0)
      params = new DrawSurface$ImageParams();
    callback$default ? callback$default(image, params) : this.drawImage_pq2ml$$default(image, params);
  };
  DrawSurface.prototype.drawText_4p9ijz$ = function (font, text, params, callback$default) {
    if (params === void 0)
      params = new DrawSurface$TextParams();
    callback$default ? callback$default(font, text, params) : this.drawText_4p9ijz$$default(font, text, params);
  };
  DrawSurface.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'DrawSurface',
    interfaces: [ImmutableDrawSurface]
  };
  function Font(fontData) {
    Font$Companion_getInstance();
    Disposable.call(this);
    this.fontData_8be2vx$ = fontData;
    this.size = this.fontData_8be2vx$.size;
  }
  function Font$Companion() {
    Font$Companion_instance = this;
    this.DEFAULT_SIZE = 12.0;
  }
  Font$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Font$Companion_instance = null;
  function Font$Companion_getInstance() {
    if (Font$Companion_instance === null) {
      new Font$Companion();
    }
    return Font$Companion_instance;
  }
  Font.prototype.derive_mx4ult$ = function (fontSize) {
    return setParent(new Font(this.fontData_8be2vx$.derive_mx4ult$(fontSize)), this);
  };
  Font.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Font',
    interfaces: [Disposable]
  };
  function Image_0(data, pos, sizeOverride) {
    Disposable.call(this);
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
  Image_0.prototype.subimage_tu0i02$ = function (pos, size) {
    return setParent(new Image_0(this.data_8be2vx$, pos, size), this);
  };
  Image_0.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Image',
    interfaces: [Disposable]
  };
  function Image_init(data, $this) {
    $this = $this || Object.create(Image_0.prototype);
    Image_0.call($this, data, Pt2$Companion_getInstance().ZERO, null);
    return $this;
  }
  function ImmutableScreen() {
  }
  ImmutableScreen.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableScreen',
    interfaces: [ImmutableDrawSurface]
  };
  function Screen() {
  }
  function Screen$Transform() {
  }
  function Screen$Transform$Scale(x, y) {
    Screen$Transform.call(this);
    this.x = x;
    this.y = y;
  }
  Screen$Transform$Scale.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Scale',
    interfaces: [Screen$Transform]
  };
  function Screen$Transform$Screen$Transform$Scale_init(vec2, $this) {
    $this = $this || Object.create(Screen$Transform$Scale.prototype);
    Screen$Transform$Scale.call($this, vec2.x, vec2.y);
    return $this;
  }
  function Screen$Transform$Translate(x, y) {
    Screen$Transform.call(this);
    this.x = x;
    this.y = y;
  }
  Screen$Transform$Translate.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Translate',
    interfaces: [Screen$Transform]
  };
  function Screen$Transform$Screen$Transform$Translate_init(vec2, $this) {
    $this = $this || Object.create(Screen$Transform$Translate.prototype);
    Screen$Transform$Translate.call($this, vec2.x, vec2.y);
    return $this;
  }
  function Screen$Transform$Composite(lhs, rhs) {
    Screen$Transform.call(this);
    this.lhs = lhs;
    this.rhs = rhs;
  }
  Screen$Transform$Composite.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Composite',
    interfaces: [Screen$Transform]
  };
  Screen$Transform.prototype.plus_sgm2c5$ = function (rhs) {
    return new Screen$Transform$Composite(this, rhs);
  };
  Screen$Transform.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Transform',
    interfaces: []
  };
  Screen.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Screen',
    interfaces: [DrawSurface, ImmutableScreen]
  };
  var withTransform = defineInlineFunction('kross2d.bitspittle.kross2d.engine.graphics.withTransform_ep4yft$', function ($receiver, transform, block) {
    $receiver.pushTransform_sgm2c5$(transform);
    try {
      block($receiver);
    }
    finally {
      $receiver.popTransform();
    }
  });
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
    Key$A_instance = new Key('A', 15);
    Key$B_instance = new Key('B', 16);
    Key$C_instance = new Key('C', 17);
    Key$D_instance = new Key('D', 18);
    Key$E_instance = new Key('E', 19);
    Key$F_instance = new Key('F', 20);
    Key$G_instance = new Key('G', 21);
    Key$H_instance = new Key('H', 22);
    Key$I_instance = new Key('I', 23);
    Key$J_instance = new Key('J', 24);
    Key$K_instance = new Key('K', 25);
    Key$L_instance = new Key('L', 26);
    Key$M_instance = new Key('M', 27);
    Key$N_instance = new Key('N', 28);
    Key$O_instance = new Key('O', 29);
    Key$P_instance = new Key('P', 30);
    Key$Q_instance = new Key('Q', 31);
    Key$R_instance = new Key('R', 32);
    Key$S_instance = new Key('S', 33);
    Key$T_instance = new Key('T', 34);
    Key$U_instance = new Key('U', 35);
    Key$V_instance = new Key('V', 36);
    Key$W_instance = new Key('W', 37);
    Key$X_instance = new Key('X', 38);
    Key$Y_instance = new Key('Y', 39);
    Key$Z_instance = new Key('Z', 40);
    Key$SPACE_instance = new Key('SPACE', 41);
    Key$TAB_instance = new Key('TAB', 42);
    Key$BACKSPACE_instance = new Key('BACKSPACE', 43);
    Key$ENTER_instance = new Key('ENTER', 44);
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
  var Key$A_instance;
  function Key$A_getInstance() {
    Key_initFields();
    return Key$A_instance;
  }
  var Key$B_instance;
  function Key$B_getInstance() {
    Key_initFields();
    return Key$B_instance;
  }
  var Key$C_instance;
  function Key$C_getInstance() {
    Key_initFields();
    return Key$C_instance;
  }
  var Key$D_instance;
  function Key$D_getInstance() {
    Key_initFields();
    return Key$D_instance;
  }
  var Key$E_instance;
  function Key$E_getInstance() {
    Key_initFields();
    return Key$E_instance;
  }
  var Key$F_instance;
  function Key$F_getInstance() {
    Key_initFields();
    return Key$F_instance;
  }
  var Key$G_instance;
  function Key$G_getInstance() {
    Key_initFields();
    return Key$G_instance;
  }
  var Key$H_instance;
  function Key$H_getInstance() {
    Key_initFields();
    return Key$H_instance;
  }
  var Key$I_instance;
  function Key$I_getInstance() {
    Key_initFields();
    return Key$I_instance;
  }
  var Key$J_instance;
  function Key$J_getInstance() {
    Key_initFields();
    return Key$J_instance;
  }
  var Key$K_instance;
  function Key$K_getInstance() {
    Key_initFields();
    return Key$K_instance;
  }
  var Key$L_instance;
  function Key$L_getInstance() {
    Key_initFields();
    return Key$L_instance;
  }
  var Key$M_instance;
  function Key$M_getInstance() {
    Key_initFields();
    return Key$M_instance;
  }
  var Key$N_instance;
  function Key$N_getInstance() {
    Key_initFields();
    return Key$N_instance;
  }
  var Key$O_instance;
  function Key$O_getInstance() {
    Key_initFields();
    return Key$O_instance;
  }
  var Key$P_instance;
  function Key$P_getInstance() {
    Key_initFields();
    return Key$P_instance;
  }
  var Key$Q_instance;
  function Key$Q_getInstance() {
    Key_initFields();
    return Key$Q_instance;
  }
  var Key$R_instance;
  function Key$R_getInstance() {
    Key_initFields();
    return Key$R_instance;
  }
  var Key$S_instance;
  function Key$S_getInstance() {
    Key_initFields();
    return Key$S_instance;
  }
  var Key$T_instance;
  function Key$T_getInstance() {
    Key_initFields();
    return Key$T_instance;
  }
  var Key$U_instance;
  function Key$U_getInstance() {
    Key_initFields();
    return Key$U_instance;
  }
  var Key$V_instance;
  function Key$V_getInstance() {
    Key_initFields();
    return Key$V_instance;
  }
  var Key$W_instance;
  function Key$W_getInstance() {
    Key_initFields();
    return Key$W_instance;
  }
  var Key$X_instance;
  function Key$X_getInstance() {
    Key_initFields();
    return Key$X_instance;
  }
  var Key$Y_instance;
  function Key$Y_getInstance() {
    Key_initFields();
    return Key$Y_instance;
  }
  var Key$Z_instance;
  function Key$Z_getInstance() {
    Key_initFields();
    return Key$Z_instance;
  }
  var Key$SPACE_instance;
  function Key$SPACE_getInstance() {
    Key_initFields();
    return Key$SPACE_instance;
  }
  var Key$TAB_instance;
  function Key$TAB_getInstance() {
    Key_initFields();
    return Key$TAB_instance;
  }
  var Key$BACKSPACE_instance;
  function Key$BACKSPACE_getInstance() {
    Key_initFields();
    return Key$BACKSPACE_instance;
  }
  var Key$ENTER_instance;
  function Key$ENTER_getInstance() {
    Key_initFields();
    return Key$ENTER_instance;
  }
  Key.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Key',
    interfaces: [Enum]
  };
  function Key$values() {
    return [Key$ESC_getInstance(), Key$UP_getInstance(), Key$DOWN_getInstance(), Key$LEFT_getInstance(), Key$RIGHT_getInstance(), Key$NUM_0_getInstance(), Key$NUM_1_getInstance(), Key$NUM_2_getInstance(), Key$NUM_3_getInstance(), Key$NUM_4_getInstance(), Key$NUM_5_getInstance(), Key$NUM_6_getInstance(), Key$NUM_7_getInstance(), Key$NUM_8_getInstance(), Key$NUM_9_getInstance(), Key$A_getInstance(), Key$B_getInstance(), Key$C_getInstance(), Key$D_getInstance(), Key$E_getInstance(), Key$F_getInstance(), Key$G_getInstance(), Key$H_getInstance(), Key$I_getInstance(), Key$J_getInstance(), Key$K_getInstance(), Key$L_getInstance(), Key$M_getInstance(), Key$N_getInstance(), Key$O_getInstance(), Key$P_getInstance(), Key$Q_getInstance(), Key$R_getInstance(), Key$S_getInstance(), Key$T_getInstance(), Key$U_getInstance(), Key$V_getInstance(), Key$W_getInstance(), Key$X_getInstance(), Key$Y_getInstance(), Key$Z_getInstance(), Key$SPACE_getInstance(), Key$TAB_getInstance(), Key$BACKSPACE_getInstance(), Key$ENTER_getInstance()];
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
      case 'A':
        return Key$A_getInstance();
      case 'B':
        return Key$B_getInstance();
      case 'C':
        return Key$C_getInstance();
      case 'D':
        return Key$D_getInstance();
      case 'E':
        return Key$E_getInstance();
      case 'F':
        return Key$F_getInstance();
      case 'G':
        return Key$G_getInstance();
      case 'H':
        return Key$H_getInstance();
      case 'I':
        return Key$I_getInstance();
      case 'J':
        return Key$J_getInstance();
      case 'K':
        return Key$K_getInstance();
      case 'L':
        return Key$L_getInstance();
      case 'M':
        return Key$M_getInstance();
      case 'N':
        return Key$N_getInstance();
      case 'O':
        return Key$O_getInstance();
      case 'P':
        return Key$P_getInstance();
      case 'Q':
        return Key$Q_getInstance();
      case 'R':
        return Key$R_getInstance();
      case 'S':
        return Key$S_getInstance();
      case 'T':
        return Key$T_getInstance();
      case 'U':
        return Key$U_getInstance();
      case 'V':
        return Key$V_getInstance();
      case 'W':
        return Key$W_getInstance();
      case 'X':
        return Key$X_getInstance();
      case 'Y':
        return Key$Y_getInstance();
      case 'Z':
        return Key$Z_getInstance();
      case 'SPACE':
        return Key$SPACE_getInstance();
      case 'TAB':
        return Key$TAB_getInstance();
      case 'BACKSPACE':
        return Key$BACKSPACE_getInstance();
      case 'ENTER':
        return Key$ENTER_getInstance();
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
  function Button(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Button_initFields() {
    Button_initFields = function () {
    };
    Button$LEFT_instance = new Button('LEFT', 0);
    Button$MIDDLE_instance = new Button('MIDDLE', 1);
    Button$RIGHT_instance = new Button('RIGHT', 2);
  }
  var Button$LEFT_instance;
  function Button$LEFT_getInstance() {
    Button_initFields();
    return Button$LEFT_instance;
  }
  var Button$MIDDLE_instance;
  function Button$MIDDLE_getInstance() {
    Button_initFields();
    return Button$MIDDLE_instance;
  }
  var Button$RIGHT_instance;
  function Button$RIGHT_getInstance() {
    Button_initFields();
    return Button$RIGHT_instance;
  }
  Button.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Button',
    interfaces: [Enum]
  };
  function Button$values() {
    return [Button$LEFT_getInstance(), Button$MIDDLE_getInstance(), Button$RIGHT_getInstance()];
  }
  Button.values = Button$values;
  function Button$valueOf(name) {
    switch (name) {
      case 'LEFT':
        return Button$LEFT_getInstance();
      case 'MIDDLE':
        return Button$MIDDLE_getInstance();
      case 'RIGHT':
        return Button$RIGHT_getInstance();
      default:throwISE('No enum constant bitspittle.kross2d.engine.input.Button.' + name);
    }
  }
  Button.valueOf_61zpoe$ = Button$valueOf;
  function Mouse() {
  }
  Mouse.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Mouse',
    interfaces: []
  };
  var booleanArray = Kotlin.booleanArray;
  function DefaultMouse() {
    this.pos_j7x4d2$_0 = Pt2_init();
    var array = booleanArray(Button$values().length, false);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = false;
    }
    this.buttonsPrev_0 = array;
    var array_0 = booleanArray(Button$values().length, false);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array_0[i_0] = false;
    }
    this.buttonsCurr_0 = array_0;
  }
  Object.defineProperty(DefaultMouse.prototype, 'pos', {
    get: function () {
      return this.pos_j7x4d2$_0;
    }
  });
  DefaultMouse.prototype.handleButton_l874q9$ = function (button, isDown) {
    this.buttonsCurr_0[button.ordinal] = isDown;
  };
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  DefaultMouse.prototype.step = function () {
    var $receiver = this.buttonsCurr_0;
    arrayCopy($receiver, this.buttonsPrev_0, 0, 0, $receiver.length);
  };
  DefaultMouse.prototype.isJustPressed_mrmd4$ = function (button) {
    return !this.buttonsPrev_0[button.ordinal] && this.buttonsCurr_0[button.ordinal];
  };
  DefaultMouse.prototype.isDown_mrmd4$ = function (button) {
    return this.buttonsCurr_0[button.ordinal];
  };
  DefaultMouse.prototype.isJustReleased_mrmd4$ = function (button) {
    return this.buttonsPrev_0[button.ordinal] && !this.buttonsCurr_0[button.ordinal];
  };
  DefaultMouse.prototype.isUp_mrmd4$ = function (button) {
    return !this.buttonsCurr_0[button.ordinal];
  };
  DefaultMouse.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DefaultMouse',
    interfaces: [Mouse]
  };
  function Scopes() {
  }
  Scopes.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Scopes',
    interfaces: []
  };
  function Timer() {
  }
  Timer.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Timer',
    interfaces: []
  };
  function DefaultTimer(lastFrame) {
    if (lastFrame === void 0)
      lastFrame = Duration$Companion_getInstance().zero();
    this.lastFrame_42650y$_0 = lastFrame;
  }
  Object.defineProperty(DefaultTimer.prototype, 'lastFrame', {
    get: function () {
      return this.lastFrame_42650y$_0;
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
      var answer = this.image_0.subimage_tu0i02$(new Pt2(x * this.tileSize.x, y * this.tileSize.y), this.tileSize);
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
  function toHtmlColor($receiver) {
    return 'rgb(' + $receiver.r + ', ' + $receiver.g + ', ' + $receiver.b + ')';
  }
  var roundToInt = Kotlin.kotlin.math.roundToInt_yrwdxr$;
  function toHtmlFont($receiver) {
    return roundToInt($receiver.fontData_8be2vx$.size).toString() + 'px ' + $receiver.fontData_8be2vx$.name;
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
    var mousePos = Pt2_init();
    var handleMouseMoveEvent = ApplicationBackend_init$handleMouseMoveEvent(this, mousePos);
    var handleMouseButtonEvent = ApplicationBackend_init$handleMouseButtonEvent(this);
    this.ctx_0.canvas.onmousemove = ApplicationBackend_init$lambda_1(handleMouseMoveEvent);
    this.ctx_0.canvas.onmousedown = ApplicationBackend_init$lambda_2(handleMouseButtonEvent);
    this.ctx_0.canvas.onmouseup = ApplicationBackend_init$lambda_3(handleMouseButtonEvent);
    this.screen = new ApplicationBackend$screen$ObjectLiteral(this, params);
    this._keyPressed_0 = new Event();
    this.keyPressed = this._keyPressed_0;
    this._keyReleased_0 = new Event();
    this.keyReleased = this._keyReleased_0;
    this._mouseMoved_0 = new Event();
    this.mouseMoved = this._mouseMoved_0;
    this._buttonPressed_0 = new Event();
    this.buttonPressed = this._buttonPressed_0;
    this._buttonReleased_0 = new Event();
    this.buttonReleased = this._buttonReleased_0;
    this.frameStepHandle_0 = 0;
    this.quitBlock_qalm6v$_0 = this.quitBlock_qalm6v$_0;
  }
  Object.defineProperty(ApplicationBackend.prototype, 'quitBlock_0', {
    get: function () {
      if (this.quitBlock_qalm6v$_0 == null)
        return throwUPAE('quitBlock');
      return this.quitBlock_qalm6v$_0;
    },
    set: function (quitBlock) {
      this.quitBlock_qalm6v$_0 = quitBlock;
    }
  });
  ApplicationBackend.prototype.runForever_o14v8n$ = function (frameStep) {
    this.frameStepHandle_0 = window.setInterval(frameStep, 16);
  };
  ApplicationBackend.prototype.onQuit_o14v8n$ = function (quitBlock) {
    this.quitBlock_0 = quitBlock;
  };
  function ApplicationBackend$quit$lambda(this$ApplicationBackend) {
    return function () {
      this$ApplicationBackend.screen.clear_cqchof$(Colors_getInstance().BLACK);
      this$ApplicationBackend.quitBlock_0();
      return Unit;
    };
  }
  ApplicationBackend.prototype.quit = function () {
    window.clearInterval(this.frameStepHandle_0);
    window.setTimeout(ApplicationBackend$quit$lambda(this), 0);
  };
  function ApplicationBackend_init$handleKeyEvent(this$ApplicationBackend) {
    return function (keyEvent, isDown) {
      var tmp$;
      keyEvent.preventDefault();
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
        case 'KeyA':
          tmp$ = Key$A_getInstance();
          break;
        case 'KeyB':
          tmp$ = Key$B_getInstance();
          break;
        case 'KeyC':
          tmp$ = Key$C_getInstance();
          break;
        case 'KeyD':
          tmp$ = Key$D_getInstance();
          break;
        case 'KeyE':
          tmp$ = Key$E_getInstance();
          break;
        case 'KeyF':
          tmp$ = Key$F_getInstance();
          break;
        case 'KeyG':
          tmp$ = Key$G_getInstance();
          break;
        case 'KeyH':
          tmp$ = Key$H_getInstance();
          break;
        case 'KeyI':
          tmp$ = Key$I_getInstance();
          break;
        case 'KeyJ':
          tmp$ = Key$J_getInstance();
          break;
        case 'KeyK':
          tmp$ = Key$K_getInstance();
          break;
        case 'KeyL':
          tmp$ = Key$L_getInstance();
          break;
        case 'KeyM':
          tmp$ = Key$M_getInstance();
          break;
        case 'KeyN':
          tmp$ = Key$N_getInstance();
          break;
        case 'KeyO':
          tmp$ = Key$O_getInstance();
          break;
        case 'KeyP':
          tmp$ = Key$P_getInstance();
          break;
        case 'KeyQ':
          tmp$ = Key$Q_getInstance();
          break;
        case 'KeyR':
          tmp$ = Key$R_getInstance();
          break;
        case 'KeyS':
          tmp$ = Key$S_getInstance();
          break;
        case 'KeyT':
          tmp$ = Key$T_getInstance();
          break;
        case 'KeyU':
          tmp$ = Key$U_getInstance();
          break;
        case 'KeyV':
          tmp$ = Key$V_getInstance();
          break;
        case 'KeyW':
          tmp$ = Key$W_getInstance();
          break;
        case 'KeyX':
          tmp$ = Key$X_getInstance();
          break;
        case 'KeyY':
          tmp$ = Key$Y_getInstance();
          break;
        case 'KeyZ':
          tmp$ = Key$Z_getInstance();
          break;
        case 'Space':
          tmp$ = Key$SPACE_getInstance();
          break;
        case 'Tab':
          tmp$ = Key$TAB_getInstance();
          break;
        case 'Backspace':
          tmp$ = Key$BACKSPACE_getInstance();
          break;
        case 'Enter':
          tmp$ = Key$ENTER_getInstance();
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
  function ApplicationBackend_init$handleMouseMoveEvent(this$ApplicationBackend, closure$mousePos) {
    return function (mouseEvent) {
      var x = mouseEvent.clientX - this$ApplicationBackend.ctx_0.canvas.offsetLeft | 0;
      var y = mouseEvent.clientY - this$ApplicationBackend.ctx_0.canvas.offsetTop | 0;
      closure$mousePos.set_vux9f0$(x, y);
      this$ApplicationBackend._mouseMoved_0.invoke_11rb$(closure$mousePos);
    };
  }
  function ApplicationBackend_init$handleMouseButtonEvent(this$ApplicationBackend) {
    return function (mouseEvent, isDown) {
      var tmp$;
      switch (mouseEvent.button) {
        case 0:
          tmp$ = Button$LEFT_getInstance();
          break;
        case 1:
          tmp$ = Button$MIDDLE_getInstance();
          break;
        case 2:
          tmp$ = Button$RIGHT_getInstance();
          break;
        default:return;
      }
      var button = tmp$;
      if (isDown)
        this$ApplicationBackend._buttonPressed_0.invoke_11rb$(button);
      else
        this$ApplicationBackend._buttonReleased_0.invoke_11rb$(button);
    };
  }
  function ApplicationBackend_init$lambda_1(closure$handleMouseMoveEvent) {
    return function (it) {
      closure$handleMouseMoveEvent(it);
      return Unit;
    };
  }
  function ApplicationBackend_init$lambda_2(closure$handleMouseButtonEvent) {
    return function (it) {
      closure$handleMouseButtonEvent(it, true);
      return Unit;
    };
  }
  function ApplicationBackend_init$lambda_3(closure$handleMouseButtonEvent) {
    return function (it) {
      closure$handleMouseButtonEvent(it, false);
      return Unit;
    };
  }
  function ApplicationBackend$screen$ObjectLiteral(this$ApplicationBackend, closure$params) {
    this.this$ApplicationBackend = this$ApplicationBackend;
    var it = closure$params.canvasElement;
    this.size_sr4c9n$_0 = Vec2_init_0(it.width, it.height);
  }
  Object.defineProperty(ApplicationBackend$screen$ObjectLiteral.prototype, 'size', {
    get: function () {
      return this.size_sr4c9n$_0;
    }
  });
  ApplicationBackend$screen$ObjectLiteral.prototype.clear_cqchof$ = function (color) {
    this.this$ApplicationBackend.ctx_0.fillStyle = toHtmlColor(color);
    this.this$ApplicationBackend.ctx_0.fillRect(0.0, 0.0, this.size.x, this.size.y);
  };
  ApplicationBackend$screen$ObjectLiteral.prototype.pushTransform_sgm2c5$ = function (transform) {
    this.this$ApplicationBackend.ctx_0.save();
    this.applyTransform_0(transform);
  };
  ApplicationBackend$screen$ObjectLiteral.prototype.popTransform = function () {
    this.this$ApplicationBackend.ctx_0.restore();
  };
  ApplicationBackend$screen$ObjectLiteral.prototype.applyTransform_0 = function (transform) {
    if (Kotlin.isType(transform, Screen$Transform$Scale))
      this.this$ApplicationBackend.ctx_0.scale(transform.x, transform.y);
    else if (Kotlin.isType(transform, Screen$Transform$Translate))
      this.this$ApplicationBackend.ctx_0.translate(transform.x, transform.y);
    else if (Kotlin.isType(transform, Screen$Transform$Composite)) {
      this.applyTransform_0(transform.lhs);
      this.applyTransform_0(transform.rhs);
    }
  };
  ApplicationBackend$screen$ObjectLiteral.prototype.drawLine_hi675t$ = function (pt1, pt2, color) {
    this.this$ApplicationBackend.ctx_0.beginPath();
    this.this$ApplicationBackend.ctx_0.strokeStyle = toHtmlColor(color);
    this.this$ApplicationBackend.ctx_0.moveTo(pt1.x, pt1.y);
    this.this$ApplicationBackend.ctx_0.lineTo(pt2.x, pt2.y);
    this.this$ApplicationBackend.ctx_0.stroke();
  };
  ApplicationBackend$screen$ObjectLiteral.prototype.drawImage_pq2ml$$default = function (image, params) {
    var tmp$;
    var dest = params.dest;
    var src = image.pos_8be2vx$;
    var srcSize = image.size;
    var destSize = (tmp$ = params.destSize) != null ? tmp$ : srcSize;
    this.this$ApplicationBackend.ctx_0.drawImage(image.data_8be2vx$.jsImage, src.x, src.y, srcSize.x, srcSize.y, dest.x, dest.y, destSize.x, destSize.y);
  };
  ApplicationBackend$screen$ObjectLiteral.prototype.measureText_y107ya$ = function (font, text) {
    this.this$ApplicationBackend.ctx_0.font = toHtmlFont(font);
    return this.this$ApplicationBackend.ctx_0.measureText(text).width;
  };
  function ApplicationBackend$screen$ObjectLiteral$drawText$lambda(closure$lines, closure$font, this$) {
    return function () {
      var tmp$;
      var $receiver = closure$lines;
      var destination = ArrayList_init_0(collectionSizeOrDefault($receiver, 10));
      var tmp$_0;
      tmp$_0 = $receiver.iterator();
      while (tmp$_0.hasNext()) {
        var item = tmp$_0.next();
        var tmp$_1 = destination.add_11rb$;
        var closure$font_0 = closure$font;
        tmp$_1.call(destination, this$.measureText_y107ya$(closure$font_0, item));
      }
      return (tmp$ = max(destination)) != null ? tmp$ : 0.0;
    };
  }
  function ApplicationBackend$screen$ObjectLiteral$drawText$lambda_0(closure$lines, closure$font, closure$params) {
    return function () {
      return closure$lines.size * closure$font.size + (closure$lines.size - 1 | 0) * closure$params.spacing;
    };
  }
  ApplicationBackend$screen$ObjectLiteral.prototype.drawText_4p9ijz$$default = function (font, text, params) {
    this.this$ApplicationBackend.ctx_0.fillStyle = toHtmlColor(params.color);
    this.this$ApplicationBackend.ctx_0.font = toHtmlFont(font);
    var lines = split(text, Kotlin.charArrayOf(10));
    var startPt = params.toTopLeft_mck3b5$(ApplicationBackend$screen$ObjectLiteral$drawText$lambda(lines, font, this), ApplicationBackend$screen$ObjectLiteral$drawText$lambda_0(lines, font, params));
    var x = startPt.x;
    var y = {v: startPt.y + font.size};
    this.this$ApplicationBackend;
    var tmp$;
    tmp$ = lines.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      this.this$ApplicationBackend.ctx_0.fillText(element, x, y.v);
      y.v += params.spacing + font.size;
    }
  };
  ApplicationBackend$screen$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [Screen]
  };
  ApplicationBackend.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ApplicationBackend',
    interfaces: []
  };
  function AssetLoaderBackend(root) {
    this.root_0 = root;
  }
  function AssetLoaderBackend$loadFontInto$lambda(closure$asset, closure$data) {
    return function (it) {
      document.fonts.add(it);
      closure$asset.setData_pm34ol$(new Font(closure$data));
      return Unit;
    };
  }
  function AssetLoaderBackend$loadFontInto$lambda_0(closure$asset) {
    return function (it) {
      closure$asset.notifyFailure_8be2vx$();
      return Unit;
    };
  }
  AssetLoaderBackend.prototype.loadFontInto_ux1b43$ = function (asset) {
    var data = FontData_init_0(this.root_0 + '/' + asset.path);
    data.jsFont.loaded.then(AssetLoaderBackend$loadFontInto$lambda(asset, data)).catch(AssetLoaderBackend$loadFontInto$lambda_0(asset));
  };
  function AssetLoaderBackend$loadImageInto$lambda(closure$asset, closure$data) {
    return function (it) {
      closure$asset.setData_pm34ol$(Image_init(closure$data));
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
      closure$asset.setData_pm34ol$(closure$sound);
      return Unit;
    };
  }
  function AssetLoaderBackend$loadSoundInto$lambda_0(closure$asset) {
    return function (f, f_0, f_1, f_2, f_3) {
      closure$asset.notifyFailure_8be2vx$();
      return Unit;
    };
  }
  AssetLoaderBackend.prototype.loadSoundInto_pecovy$ = function (asset) {
    var sound = new Sound(this.root_0 + '/' + asset.path);
    sound.jsAudio.onloadeddata = AssetLoaderBackend$loadSoundInto$lambda(asset, sound);
    sound.jsAudio.onerror = AssetLoaderBackend$loadSoundInto$lambda_0(asset);
  };
  function AssetLoaderBackend$loadMusicInto$lambda(closure$asset, closure$music) {
    return function (it) {
      closure$asset.setData_pm34ol$(closure$music);
      return Unit;
    };
  }
  function AssetLoaderBackend$loadMusicInto$lambda_0(closure$asset) {
    return function (f, f_0, f_1, f_2, f_3) {
      closure$asset.notifyFailure_8be2vx$();
      return Unit;
    };
  }
  AssetLoaderBackend.prototype.loadMusicInto_mnccdk$ = function (asset) {
    var music = new Music(this.root_0 + '/' + asset.path);
    music.jsAudio.onloadeddata = AssetLoaderBackend$loadMusicInto$lambda(asset, music);
    music.jsAudio.onerror = AssetLoaderBackend$loadMusicInto$lambda_0(asset);
  };
  AssetLoaderBackend.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AssetLoaderBackend',
    interfaces: []
  };
  function AudioHandle(jsAudio) {
    Disposable.call(this);
    var tmp$;
    this.jsAudioPtr_8be2vx$ = Kotlin.isType(tmp$ = jsAudio.cloneNode(), Audio) ? tmp$ : throwCCE();
  }
  AudioHandle.prototype.play_8be2vx$ = function () {
    if (this.jsAudioPtr_8be2vx$.ended)
      return;
    this.jsAudioPtr_8be2vx$.play();
  };
  AudioHandle.prototype.stop_8be2vx$ = function () {
    this.jsAudioPtr_8be2vx$.currentTime = kotlin_js_internal_DoubleCompanionObject.MAX_VALUE;
  };
  AudioHandle.prototype.pause_8be2vx$ = function () {
    if (this.jsAudioPtr_8be2vx$.ended)
      return;
    this.jsAudioPtr_8be2vx$.pause();
  };
  AudioHandle.prototype.resume_8be2vx$ = function () {
    if (this.jsAudioPtr_8be2vx$.ended)
      return;
    this.jsAudioPtr_8be2vx$.play();
  };
  AudioHandle.prototype.onDisposed = function () {
    this.stop_8be2vx$();
    clearSource(this.jsAudioPtr_8be2vx$);
  };
  AudioHandle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'AudioHandle',
    interfaces: [Disposable]
  };
  function Music(path) {
    Disposable.call(this);
    this.jsAudio = new Audio(path);
    this.audioHandle_0 = setParent(new AudioHandle(this.jsAudio), this);
  }
  Music.prototype.play = function () {
    this.audioHandle_0.play_8be2vx$();
  };
  Music.prototype.stop = function () {
    this.audioHandle_0.stop_8be2vx$();
  };
  Music.prototype.pause = function () {
    this.audioHandle_0.pause_8be2vx$();
  };
  Music.prototype.resume = function () {
    this.audioHandle_0.resume_8be2vx$();
  };
  Music.prototype.onDisposed = function () {
    clearSource(this.jsAudio);
  };
  Music.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Music',
    interfaces: [Disposable]
  };
  function SoundHandle(audio) {
    Disposable.call(this);
    this.innerHandler_8be2vx$ = setParent(new AudioHandle(audio), this);
  }
  SoundHandle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SoundHandle',
    interfaces: [Disposable]
  };
  function Sound(path) {
    Disposable.call(this);
    this.jsAudio = new Audio(path);
    this.handles_0 = ArrayList_init();
  }
  function Sound$play$lambda$lambda(this$Sound, closure$soundHandle) {
    return function (it) {
      return this$Sound.handles_0.remove_11rb$(closure$soundHandle);
    };
  }
  Sound.prototype.play = function () {
    var $receiver = setParent(new SoundHandle(this.jsAudio), this);
    this.handles_0.add_11rb$($receiver);
    $receiver.innerHandler_8be2vx$.jsAudioPtr_8be2vx$.onended = Sound$play$lambda$lambda(this, $receiver);
    $receiver.innerHandler_8be2vx$.play_8be2vx$();
    return $receiver;
  };
  Sound.prototype.stop_8wv9oi$ = function (handle) {
    if (handle === void 0)
      handle = null;
    var tmp$;
    tmp$ = this.handles_0.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (handle == null || equals(handle, element))
        element.innerHandler_8be2vx$.stop_8be2vx$();
    }
  };
  Sound.prototype.pause_8wv9oi$ = function (handle) {
    if (handle === void 0)
      handle = null;
    var tmp$;
    tmp$ = this.handles_0.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (handle == null || equals(handle, element))
        element.innerHandler_8be2vx$.pause_8be2vx$();
    }
  };
  Sound.prototype.resume_8wv9oi$ = function (handle) {
    if (handle === void 0)
      handle = null;
    var tmp$;
    tmp$ = this.handles_0.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (handle == null || equals(handle, element))
        element.innerHandler_8be2vx$.resume_8be2vx$();
    }
  };
  Sound.prototype.onDisposed = function () {
    clearSource(this.jsAudio);
  };
  Sound.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Sound',
    interfaces: [Disposable]
  };
  function clearSource($receiver) {
    $receiver.removeAttribute('src');
  }
  function FontData(name, fontFace, size) {
    this.name = name;
    this.size = size;
    fontFace.load();
    this.jsFont = fontFace;
  }
  FontData.prototype.derive_mx4ult$ = function (fontSize) {
    return new FontData(this.name, this.jsFont, fontSize);
  };
  FontData.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FontData',
    interfaces: []
  };
  function FontData_init(fontName, path, $this) {
    $this = $this || Object.create(FontData.prototype);
    var $receiver = new FontFace(fontName, 'url(' + path + ')');
    $receiver.load();
    FontData.call($this, fontName, $receiver, Font$Companion_getInstance().DEFAULT_SIZE);
    return $this;
  }
  function FontData_init_0(path, $this) {
    $this = $this || Object.create(FontData.prototype);
    FontData_init(substringBefore(substringAfterLast(path, 47), 46), path, $this);
    return $this;
  }
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
  $$importsForInline$$.kross2d = _;
  var package$bitspittle = _.bitspittle || (_.bitspittle = {});
  var package$kross2d = package$bitspittle.kross2d || (package$bitspittle.kross2d = {});
  var package$core = package$kross2d.core || (package$kross2d.core = {});
  var package$event = package$core.event || (package$core.event = {});
  package$event.ObservableEvent = ObservableEvent;
  package$event.Event = Event;
  package$event.ScopedObserver = ScopedObserver;
  var package$geom = package$core.geom || (package$core.geom = {});
  package$geom.ImmutableCircle = ImmutableCircle;
  Object.defineProperty(Circle, 'Companion', {
    get: Circle$Companion_getInstance
  });
  package$geom.Circle_init = Circle_init;
  package$geom.Circle_init_mx4ult$ = Circle_init_0;
  package$geom.Circle_init_y2kzbl$ = Circle_init_1;
  package$geom.Circle_init_qt1dr2$ = Circle_init_2;
  package$geom.Circle_init_kl57j9$ = Circle_init_3;
  package$geom.Circle = Circle;
  package$geom.ImmutableShape = ImmutableShape;
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
  package$geom.ImmutableSquare = ImmutableSquare;
  Object.defineProperty(Square, 'Companion', {
    get: Square$Companion_getInstance
  });
  package$geom.Square_init = Square_init;
  package$geom.Square_init_mx4ult$ = Square_init_0;
  package$geom.Square_init_y2kzbl$ = Square_init_1;
  package$geom.Square_init_qt1dr2$ = Square_init_2;
  package$geom.Square_init_sabe7m$ = Square_init_3;
  package$geom.Square = Square;
  var package$graphics = package$core.graphics || (package$core.graphics = {});
  package$graphics.ImmutableColor = ImmutableColor;
  Object.defineProperty(Color, 'Companion', {
    get: Color$Companion_getInstance
  });
  package$graphics.Color_init_7b5o5w$ = Color_init;
  package$graphics.Color = Color;
  Object.defineProperty(package$graphics, 'Colors', {
    get: Colors_getInstance
  });
  var package$math = package$core.math || (package$core.math = {});
  Object.defineProperty(package$math, 'HALF_PI', {
    get: function () {
      return HALF_PI;
    }
  });
  Object.defineProperty(package$math, 'QUARTER_PI', {
    get: function () {
      return QUARTER_PI;
    }
  });
  package$math.max_8bdmd0$ = max_0;
  package$math.min_8bdmd0$ = min;
  package$math.clamp_jn2ilo$ = clamp;
  package$math.clamp_e4yvb3$ = clamp_0;
  package$math.clamp_ekzx8g$ = clamp_1;
  package$math.clamp_wj6e7o$ = clamp_2;
  package$math.clamp_nig4hr$ = clamp_3;
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
  var package$memory = package$core.memory || (package$core.memory = {});
  package$memory.AlreadyDisposedException = AlreadyDisposedException;
  package$memory.ImmutableDisposable = ImmutableDisposable;
  package$memory.Disposable = Disposable;
  package$memory.setParent_wixrfn$ = setParent;
  package$memory.use_glrkcs$ = use;
  package$memory.disposable_o14v8n$ = disposable;
  package$memory.disposable_8qc67b$ = disposable_0;
  Disposer.prototype.IllegalDisposerOperation = Disposer$IllegalDisposerOperation;
  Object.defineProperty(package$memory, 'Disposer', {
    get: Disposer_getInstance
  });
  package$memory.Rc = Rc;
  var package$time = package$core.time || (package$core.time = {});
  package$time.ImmutableDuration = ImmutableDuration;
  package$time.max_qyyj9w$ = max_1;
  package$time.min_qyyj9w$ = min_0;
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
  Object.defineProperty(Asset$State, 'LOADED', {
    get: Asset$State$LOADED_getInstance
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
  package$context.EnterContext = EnterContext;
  package$context.UpdateContext = UpdateContext;
  var package$graphics_0 = package$engine.graphics || (package$engine.graphics = {});
  package$graphics_0.ImmutableDrawSurface = ImmutableDrawSurface;
  DrawSurface.ImageParams = DrawSurface$ImageParams;
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'TOP_LEFT', {
    get: DrawSurface$TextParams$Anchor$TOP_LEFT_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'TOP', {
    get: DrawSurface$TextParams$Anchor$TOP_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'TOP_RIGHT', {
    get: DrawSurface$TextParams$Anchor$TOP_RIGHT_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'LEFT', {
    get: DrawSurface$TextParams$Anchor$LEFT_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'CENTER', {
    get: DrawSurface$TextParams$Anchor$CENTER_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'RIGHT', {
    get: DrawSurface$TextParams$Anchor$RIGHT_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'BOTTOM_LEFT', {
    get: DrawSurface$TextParams$Anchor$BOTTOM_LEFT_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'BOTTOM', {
    get: DrawSurface$TextParams$Anchor$BOTTOM_getInstance
  });
  Object.defineProperty(DrawSurface$TextParams$Anchor, 'BOTTOM_RIGHT', {
    get: DrawSurface$TextParams$Anchor$BOTTOM_RIGHT_getInstance
  });
  DrawSurface$TextParams.Anchor = DrawSurface$TextParams$Anchor;
  DrawSurface.TextParams = DrawSurface$TextParams;
  package$graphics_0.DrawSurface = DrawSurface;
  Object.defineProperty(Font, 'Companion', {
    get: Font$Companion_getInstance
  });
  package$graphics_0.Font = Font;
  package$graphics_0.Image_init_9euhhb$ = Image_init;
  package$graphics_0.Image = Image_0;
  package$graphics_0.ImmutableScreen = ImmutableScreen;
  Screen$Transform.Scale_init_nvqvy9$ = Screen$Transform$Screen$Transform$Scale_init;
  Screen$Transform.Scale = Screen$Transform$Scale;
  Screen$Transform.Translate_init_nvqvy9$ = Screen$Transform$Screen$Transform$Translate_init;
  Screen$Transform.Translate = Screen$Transform$Translate;
  Screen$Transform.Composite = Screen$Transform$Composite;
  Screen.Transform = Screen$Transform;
  package$graphics_0.Screen = Screen;
  package$graphics_0.withTransform_ep4yft$ = withTransform;
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
  Object.defineProperty(Key, 'A', {
    get: Key$A_getInstance
  });
  Object.defineProperty(Key, 'B', {
    get: Key$B_getInstance
  });
  Object.defineProperty(Key, 'C', {
    get: Key$C_getInstance
  });
  Object.defineProperty(Key, 'D', {
    get: Key$D_getInstance
  });
  Object.defineProperty(Key, 'E', {
    get: Key$E_getInstance
  });
  Object.defineProperty(Key, 'F', {
    get: Key$F_getInstance
  });
  Object.defineProperty(Key, 'G', {
    get: Key$G_getInstance
  });
  Object.defineProperty(Key, 'H', {
    get: Key$H_getInstance
  });
  Object.defineProperty(Key, 'I', {
    get: Key$I_getInstance
  });
  Object.defineProperty(Key, 'J', {
    get: Key$J_getInstance
  });
  Object.defineProperty(Key, 'K', {
    get: Key$K_getInstance
  });
  Object.defineProperty(Key, 'L', {
    get: Key$L_getInstance
  });
  Object.defineProperty(Key, 'M', {
    get: Key$M_getInstance
  });
  Object.defineProperty(Key, 'N', {
    get: Key$N_getInstance
  });
  Object.defineProperty(Key, 'O', {
    get: Key$O_getInstance
  });
  Object.defineProperty(Key, 'P', {
    get: Key$P_getInstance
  });
  Object.defineProperty(Key, 'Q', {
    get: Key$Q_getInstance
  });
  Object.defineProperty(Key, 'R', {
    get: Key$R_getInstance
  });
  Object.defineProperty(Key, 'S', {
    get: Key$S_getInstance
  });
  Object.defineProperty(Key, 'T', {
    get: Key$T_getInstance
  });
  Object.defineProperty(Key, 'U', {
    get: Key$U_getInstance
  });
  Object.defineProperty(Key, 'V', {
    get: Key$V_getInstance
  });
  Object.defineProperty(Key, 'W', {
    get: Key$W_getInstance
  });
  Object.defineProperty(Key, 'X', {
    get: Key$X_getInstance
  });
  Object.defineProperty(Key, 'Y', {
    get: Key$Y_getInstance
  });
  Object.defineProperty(Key, 'Z', {
    get: Key$Z_getInstance
  });
  Object.defineProperty(Key, 'SPACE', {
    get: Key$SPACE_getInstance
  });
  Object.defineProperty(Key, 'TAB', {
    get: Key$TAB_getInstance
  });
  Object.defineProperty(Key, 'BACKSPACE', {
    get: Key$BACKSPACE_getInstance
  });
  Object.defineProperty(Key, 'ENTER', {
    get: Key$ENTER_getInstance
  });
  var package$input = package$engine.input || (package$engine.input = {});
  package$input.Key = Key;
  package$input.Keyboard = Keyboard;
  package$input.DefaultKeyboard = DefaultKeyboard;
  Object.defineProperty(Button, 'LEFT', {
    get: Button$LEFT_getInstance
  });
  Object.defineProperty(Button, 'MIDDLE', {
    get: Button$MIDDLE_getInstance
  });
  Object.defineProperty(Button, 'RIGHT', {
    get: Button$RIGHT_getInstance
  });
  package$input.Button = Button;
  package$input.Mouse = Mouse;
  package$input.DefaultMouse = DefaultMouse;
  var package$memory_0 = package$engine.memory || (package$engine.memory = {});
  package$memory_0.Scopes = Scopes;
  var package$time_0 = package$engine.time || (package$engine.time = {});
  package$time_0.Timer = Timer;
  package$time_0.DefaultTimer = DefaultTimer;
  package$time_0.now_ydye95$ = now;
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
  package$app.toHtmlColor_vhx3sg$ = toHtmlColor;
  package$app.toHtmlFont_r12tbz$ = toHtmlFont;
  package$app.AppParams = AppParams;
  package$app.ApplicationBackend = ApplicationBackend;
  package$assets.AssetLoaderBackend = AssetLoaderBackend;
  var package$audio = package$engine.audio || (package$engine.audio = {});
  package$audio.AudioHandle = AudioHandle;
  package$audio.Music = Music;
  package$audio.SoundHandle = SoundHandle;
  package$audio.Sound = Sound;
  var package$dom = package$engine.dom || (package$engine.dom = {});
  package$dom.clearSource_tcf3u7$ = clearSource;
  package$graphics_0.FontData_init_puj7f4$ = FontData_init;
  package$graphics_0.FontData_init_61zpoe$ = FontData_init_0;
  package$graphics_0.FontData = FontData;
  package$graphics_0.ImageData = ImageData;
  Screen.prototype.drawImage_pq2ml$ = DrawSurface.prototype.drawImage_pq2ml$;
  Screen.prototype.drawText_4p9ijz$ = DrawSurface.prototype.drawText_4p9ijz$;
  ApplicationBackend$screen$ObjectLiteral.prototype.drawImage_pq2ml$ = Screen.prototype.drawImage_pq2ml$;
  ApplicationBackend$screen$ObjectLiteral.prototype.drawText_4p9ijz$ = Screen.prototype.drawText_4p9ijz$;
  HALF_PI = math.PI / 2;
  QUARTER_PI = math.PI / 4;
  Kotlin.defineModule('kross2d', _);
  return _;
}(typeof kross2d === 'undefined' ? {} : kross2d, kotlin);
