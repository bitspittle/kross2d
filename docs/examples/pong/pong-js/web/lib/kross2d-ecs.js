if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kross2d-ecs'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kross2d-ecs'.");
}
this['kross2d-ecs'] = function (_, Kotlin) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var Unit = Kotlin.kotlin.Unit;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var ensureNotNull = Kotlin.ensureNotNull;
  var defineInlineFunction = Kotlin.defineInlineFunction;
  var wrapFunction = Kotlin.wrapFunction;
  var listOf = Kotlin.kotlin.collections.listOf_i5x0yv$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var plus = Kotlin.kotlin.collections.plus_qloxvw$;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var IllegalArgumentException = Kotlin.kotlin.IllegalArgumentException;
  var IllegalStateException_init = Kotlin.kotlin.IllegalStateException_init_pdl1vj$;
  var IllegalStateException = Kotlin.kotlin.IllegalStateException;
  var asSequence = Kotlin.kotlin.collections.asSequence_7wnvza$;
  var filter = Kotlin.kotlin.sequences.filter_euau3h$;
  var map = Kotlin.kotlin.sequences.map_z5avom$;
  var sequenceOf = Kotlin.kotlin.sequences.sequenceOf_i5x0yv$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var throwCCE = Kotlin.throwCCE;
  var get_lastIndex = Kotlin.kotlin.collections.get_lastIndex_55thoc$;
  EntityAlreadyDeletedException.prototype = Object.create(IllegalArgumentException.prototype);
  EntityAlreadyDeletedException.prototype.constructor = EntityAlreadyDeletedException;
  ProcessingWorldException.prototype = Object.create(IllegalStateException.prototype);
  ProcessingWorldException.prototype.constructor = ProcessingWorldException;
  function Component() {
  }
  Component.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Component',
    interfaces: []
  };
  function DrawSystem(family) {
    this.family = family;
  }
  DrawSystem.prototype.beforeDraws_nri52c$ = function (world, ctx) {
  };
  DrawSystem.prototype.afterDraws_nri52c$ = function (world, ctx) {
  };
  DrawSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DrawSystem',
    interfaces: []
  };
  function Entity(id, world) {
    this.id_8be2vx$ = id;
    this.world = world;
    this.deleted_8be2vx$ = false;
  }
  Entity.prototype.add_qbdilf$ = function (component) {
    this.world.addComponentFor_pu8j9t$(this, component);
  };
  Entity.prototype.find_kwubw2$ = function (componentClass) {
    return this.world.findComponentFor_jxz1vn$(this, componentClass);
  };
  Entity.prototype.get_kwubw2$ = function (componentClass) {
    return ensureNotNull(this.find_kwubw2$(componentClass));
  };
  Entity.prototype.find_bbwjd1$ = defineInlineFunction('kross2d-ecs.bitspittle.kross2d.extras.ecs.Entity.find_bbwjd1$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    return function (C_0, isC) {
      return this.find_kwubw2$(getKClass(C_0));
    };
  }));
  Entity.prototype.get_bbwjd1$ = defineInlineFunction('kross2d-ecs.bitspittle.kross2d.extras.ecs.Entity.get_bbwjd1$', wrapFunction(function () {
    var ensureNotNull = Kotlin.ensureNotNull;
    var getKClass = Kotlin.getKClass;
    return function (C_0, isC) {
      return ensureNotNull(this.find_kwubw2$(getKClass(C_0)));
    };
  }));
  Entity.prototype.remove_kwubw2$ = function (componentClass) {
    this.world.removeComponentFor_5a62ey$(this, componentClass);
  };
  Entity.prototype.remove_bbwjd1$ = defineInlineFunction('kross2d-ecs.bitspittle.kross2d.extras.ecs.Entity.remove_bbwjd1$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    return function (C_0, isC) {
      this.remove_kwubw2$(getKClass(C_0));
    };
  }));
  Entity.prototype.has_kwubw2$ = function (componentClass) {
    return this.find_kwubw2$(componentClass) != null;
  };
  Entity.prototype.has_bbwjd1$ = defineInlineFunction('kross2d-ecs.bitspittle.kross2d.extras.ecs.Entity.has_bbwjd1$', wrapFunction(function () {
    var getKClass = Kotlin.getKClass;
    return function (C_0, isC) {
      return this.has_kwubw2$(getKClass(C_0));
    };
  }));
  Entity.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Entity',
    interfaces: []
  };
  function Family() {
    Family$Companion_getInstance();
  }
  function Family$Companion() {
    Family$Companion_instance = this;
  }
  var Collection = Kotlin.kotlin.collections.Collection;
  function Family$Companion$all$lambda(entity, components) {
    var all$result;
    all$break: do {
      var tmp$;
      if (Kotlin.isType(components, Collection) && components.isEmpty()) {
        all$result = true;
        break all$break;
      }
      tmp$ = components.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (!entity.has_kwubw2$(element)) {
          all$result = false;
          break all$break;
        }
      }
      all$result = true;
    }
     while (false);
    return all$result;
  }
  Family$Companion.prototype.all_ntvadq$ = function (members) {
    return new SingleFamily(listOf(members.slice()), Family$Companion$all$lambda);
  };
  function Family$Companion$any$lambda(entity, components) {
    var any$result;
    any$break: do {
      var tmp$;
      if (Kotlin.isType(components, Collection) && components.isEmpty()) {
        any$result = false;
        break any$break;
      }
      tmp$ = components.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (entity.has_kwubw2$(element)) {
          any$result = true;
          break any$break;
        }
      }
      any$result = false;
    }
     while (false);
    return any$result;
  }
  Family$Companion.prototype.any_ntvadq$ = function (members) {
    return new SingleFamily(listOf(members.slice()), Family$Companion$any$lambda);
  };
  var checkCountOverflow = Kotlin.kotlin.collections.checkCountOverflow_za3lpa$;
  function Family$Companion$one$lambda(entity, components) {
    var count$result;
    count$break: do {
      var tmp$;
      if (Kotlin.isType(components, Collection) && components.isEmpty()) {
        count$result = 0;
        break count$break;
      }
      var count = 0;
      tmp$ = components.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (entity.has_kwubw2$(element))
          checkCountOverflow((count = count + 1 | 0, count));
      }
      count$result = count;
    }
     while (false);
    return count$result === 1;
  }
  Family$Companion.prototype.one_ntvadq$ = function (members) {
    return new SingleFamily(listOf(members.slice()), Family$Companion$one$lambda);
  };
  function Family$Companion$none$lambda(entity, components) {
    var none$result;
    none$break: do {
      var tmp$;
      if (Kotlin.isType(components, Collection) && components.isEmpty()) {
        none$result = true;
        break none$break;
      }
      tmp$ = components.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (entity.has_kwubw2$(element)) {
          none$result = false;
          break none$break;
        }
      }
      none$result = true;
    }
     while (false);
    return none$result;
  }
  Family$Companion.prototype.none_ntvadq$ = function (members) {
    return new SingleFamily(listOf(members.slice()), Family$Companion$none$lambda);
  };
  Family$Companion.prototype.custom_69la8y$ = function (members, entityMatches) {
    return new SingleFamily(listOf(members.slice()), entityMatches);
  };
  Family$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Family$Companion_instance = null;
  function Family$Companion_getInstance() {
    if (Family$Companion_instance === null) {
      new Family$Companion();
    }
    return Family$Companion_instance;
  }
  Family.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Family',
    interfaces: []
  };
  function SingleFamily(members, entityMatches) {
    this.members_0 = members;
    this.entityMatches_0 = entityMatches;
  }
  SingleFamily.prototype.matches_5mschp$ = function (entity) {
    return this.entityMatches_0(entity, this.members_0);
  };
  SingleFamily.prototype.plus_5wk62m$ = function (other) {
    return new FamilyGroup(listOf([this, other]));
  };
  SingleFamily.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SingleFamily',
    interfaces: [Family]
  };
  function FamilyGroup(families) {
    this.families_0 = families;
  }
  FamilyGroup.prototype.matches_5mschp$ = function (entity) {
    var $receiver = this.families_0;
    var all$result;
    all$break: do {
      var tmp$;
      if (Kotlin.isType($receiver, Collection) && $receiver.isEmpty()) {
        all$result = true;
        break all$break;
      }
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        if (!element.matches_5mschp$(entity)) {
          all$result = false;
          break all$break;
        }
      }
      all$result = true;
    }
     while (false);
    return all$result;
  };
  FamilyGroup.prototype.plus_5wk62m$ = function (other) {
    return new FamilyGroup(plus(this.families_0, other));
  };
  FamilyGroup.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'FamilyGroup',
    interfaces: [Family]
  };
  function UpdateSystem(family) {
    this.family = family;
  }
  UpdateSystem.prototype.beforeUpdates_5hzedl$ = function (world, ctx) {
  };
  UpdateSystem.prototype.afterUpdates_5hzedl$ = function (world, ctx) {
  };
  UpdateSystem.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'UpdateSystem',
    interfaces: []
  };
  function EntityAlreadyDeletedException() {
    IllegalArgumentException_init('Entity already deleted', this);
    this.name = 'EntityAlreadyDeletedException';
  }
  EntityAlreadyDeletedException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EntityAlreadyDeletedException',
    interfaces: [IllegalArgumentException]
  };
  function ProcessingWorldException() {
    IllegalStateException_init('The current action is not supported when processing the world', this);
    this.name = 'ProcessingWorldException';
  }
  ProcessingWorldException.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ProcessingWorldException',
    interfaces: [IllegalStateException]
  };
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var LinkedHashMap_init = Kotlin.kotlin.collections.LinkedHashMap_init_q3lmfv$;
  function World() {
    this.facade_0 = new World$facade$ObjectLiteral(this);
    this.updateSystems_0 = ArrayList_init();
    this.drawSystems_0 = ArrayList_init();
    this.entities_0 = ArrayList_init();
    this.entitiesToAdd_0 = ArrayList_init();
    this.entitiesToDelete_0 = ArrayList_init();
    this.components_0 = LinkedHashMap_init();
    this.componentsToAdd_0 = ArrayList_init();
    this.componentsToRemove_0 = ArrayList_init();
    this.processing_0 = false;
  }
  function World$ImmutableFacade() {
  }
  World$ImmutableFacade.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'ImmutableFacade',
    interfaces: []
  };
  function World$Facade() {
  }
  World$Facade.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'Facade',
    interfaces: [World$ImmutableFacade]
  };
  World.prototype.createEntity = function () {
    var newEntity = new Entity(this.entities_0.size + this.entitiesToAdd_0.size | 0, this);
    if (this.processing_0)
      this.entitiesToAdd_0.add_11rb$(newEntity);
    else
      this.entities_0.add_11rb$(newEntity);
    return newEntity;
  };
  World.prototype.deleteEntity_5mschp$ = function (entity) {
    if (entity.deleted_8be2vx$)
      throw new EntityAlreadyDeletedException();
    if (this.processing_0) {
      this.entitiesToDelete_0.add_11rb$(entity);
      return;
    }
    this.handleDelete_0(entity);
  };
  World.prototype.addSystem_46cjsi$ = function (updateSystem) {
    this.assertNotProcessing_0();
    this.updateSystems_0.add_11rb$(updateSystem);
  };
  World.prototype.removeSystem_46cjsi$ = function (updateSystem) {
    this.assertNotProcessing_0();
    this.updateSystems_0.remove_11rb$(updateSystem);
  };
  World.prototype.addSystem_uk5q8j$ = function (drawSystem) {
    this.assertNotProcessing_0();
    this.drawSystems_0.add_11rb$(drawSystem);
  };
  World.prototype.removeSystem_uk5q8j$ = function (drawSystem) {
    this.assertNotProcessing_0();
    this.drawSystems_0.remove_11rb$(drawSystem);
  };
  function World$update$lambda(this$World, closure$ctx) {
    return function () {
      var $receiver = this$World.updateSystems_0;
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var this$World_0 = this$World;
        var closure$ctx_0 = closure$ctx;
        element.beforeUpdates_5hzedl$(this$World_0.facade_0, closure$ctx_0);
        var tmp$_0;
        tmp$_0 = this$World_0.query_5wk62m$(element.family).iterator();
        while (tmp$_0.hasNext()) {
          var element_0 = tmp$_0.next();
          if (element.family.matches_5mschp$(element_0)) {
            element.update_uy1jsw$(this$World_0.facade_0, closure$ctx_0, element_0);
          }
        }
        element.afterUpdates_5hzedl$(this$World_0.facade_0, closure$ctx_0);
      }
      return Unit;
    };
  }
  World.prototype.update_kung05$ = function (ctx) {
    this.process_0(World$update$lambda(this, ctx));
  };
  function World$draw$lambda(this$World, closure$ctx) {
    return function () {
      var $receiver = this$World.drawSystems_0;
      var tmp$;
      tmp$ = $receiver.iterator();
      while (tmp$.hasNext()) {
        var element = tmp$.next();
        var this$World_0 = this$World;
        var closure$ctx_0 = closure$ctx;
        element.beforeDraws_nri52c$(this$World_0.facade_0, closure$ctx_0);
        var tmp$_0;
        tmp$_0 = this$World_0.query_5wk62m$(element.family).iterator();
        while (tmp$_0.hasNext()) {
          var element_0 = tmp$_0.next();
          if (element.family.matches_5mschp$(element_0)) {
            element.draw_bg386z$(this$World_0.facade_0, closure$ctx_0, element_0);
          }
        }
        element.afterDraws_nri52c$(this$World_0.facade_0, closure$ctx_0);
      }
      return Unit;
    };
  }
  World.prototype.draw_glqt06$ = function (ctx) {
    this.process_0(World$draw$lambda(this, ctx));
  };
  World.prototype.process_0 = function (block) {
    this.assertNotProcessing_0();
    this.processing_0 = true;
    try {
      block();
    }
    finally {
      this.processing_0 = false;
      this.handlePostProcess_0();
    }
  };
  World.prototype.assertNotProcessing_0 = function () {
    if (this.processing_0)
      throw new ProcessingWorldException();
  };
  World.prototype.handlePostProcess_0 = function () {
    var tmp$;
    tmp$ = this.entitiesToAdd_0.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      this.entities_0.add_11rb$(element);
    }
    this.entitiesToAdd_0.clear();
    var tmp$_0;
    tmp$_0 = this.componentsToAdd_0.iterator();
    while (tmp$_0.hasNext()) {
      var element_0 = tmp$_0.next();
      this.handleAddComponent_0(element_0.first, element_0.second);
    }
    this.componentsToAdd_0.clear();
    var tmp$_1;
    tmp$_1 = this.componentsToRemove_0.iterator();
    while (tmp$_1.hasNext()) {
      var element_1 = tmp$_1.next();
      this.handleRemoveComponent_0(element_1.first, element_1.second);
    }
    this.componentsToRemove_0.clear();
    var tmp$_2;
    tmp$_2 = this.entitiesToDelete_0.iterator();
    while (tmp$_2.hasNext()) {
      var element_2 = tmp$_2.next();
      this.handleDelete_0(element_2);
    }
    this.entitiesToDelete_0.clear();
  };
  function World$query$lambda(closure$family) {
    return function (it) {
      return closure$family.matches_5mschp$(it);
    };
  }
  World.prototype.query_5wk62m$ = function (family) {
    return filter(asSequence(this.entities_0), World$query$lambda(family));
  };
  function World$query$lambda_0(this$World) {
    return function (id) {
      return this$World.entities_0.get_za3lpa$(id);
    };
  }
  World.prototype.query_qvsabv$ = function (componentClass) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    return (tmp$_2 = (tmp$_1 = (tmp$_0 = (tmp$ = this.components_0.get_11rb$(componentClass)) != null ? tmp$.keys : null) != null ? asSequence(tmp$_0) : null) != null ? map(tmp$_1, World$query$lambda_0(this)) : null) != null ? tmp$_2 : sequenceOf([]);
  };
  World.prototype.addComponentFor_pu8j9t$ = function (entity, component) {
    if (entity.deleted_8be2vx$)
      throw new EntityAlreadyDeletedException();
    if (this.processing_0) {
      this.componentsToAdd_0.add_11rb$(to(entity, component));
      return;
    }
    this.handleAddComponent_0(entity, component);
  };
  World.prototype.findComponentFor_jxz1vn$ = function (entity, componentClass) {
    var tmp$, tmp$_0;
    if (entity.deleted_8be2vx$)
      throw new EntityAlreadyDeletedException();
    return (tmp$_0 = (tmp$ = this.components_0.get_11rb$(componentClass)) != null ? tmp$.get_11rb$(entity.id_8be2vx$) : null) == null || Kotlin.isType(tmp$_0, Component) ? tmp$_0 : throwCCE();
  };
  World.prototype.removeComponentFor_5a62ey$ = function (entity, componentClass) {
    if (entity.deleted_8be2vx$)
      throw new EntityAlreadyDeletedException();
    if (this.processing_0) {
      this.componentsToRemove_0.add_11rb$(to(entity, componentClass));
      return;
    }
    this.handleRemoveComponent_0(entity, componentClass);
  };
  World.prototype.handleAddComponent_0 = function (entity, component) {
    var $receiver = this.components_0;
    var key = Kotlin.getKClassFromExpression(component);
    var tmp$;
    var value = $receiver.get_11rb$(key);
    if (value == null) {
      var answer = LinkedHashMap_init();
      $receiver.put_xwzc9p$(key, answer);
      tmp$ = answer;
    }
     else {
      tmp$ = value;
    }
    var $receiver_0 = tmp$;
    var key_0 = entity.id_8be2vx$;
    $receiver_0.put_xwzc9p$(key_0, component);
  };
  World.prototype.handleRemoveComponent_0 = function (entity, componentClass) {
    var tmp$;
    (tmp$ = this.components_0.get_11rb$(componentClass)) != null ? tmp$.remove_11rb$(entity.id_8be2vx$) : null;
  };
  World.prototype.handleDelete_0 = function (entity) {
    var tmp$;
    tmp$ = this.components_0.values.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      element.remove_11rb$(entity.id_8be2vx$);
    }
    var lastIndex = get_lastIndex(this.entities_0);
    if (entity.id_8be2vx$ !== lastIndex) {
      var tmp$_0 = this.entities_0;
      var tmp$_1 = entity.id_8be2vx$;
      var $receiver = this.entities_0.get_za3lpa$(lastIndex);
      this.entities_0.set_wxm5ur$(lastIndex, this.entities_0.get_za3lpa$(entity.id_8be2vx$));
      tmp$_0.set_wxm5ur$(tmp$_1, $receiver);
    }
    this.entities_0.removeAt_za3lpa$(lastIndex);
    entity.deleted_8be2vx$ = true;
  };
  function World$facade$ObjectLiteral(this$World) {
    this.this$World = this$World;
  }
  World$facade$ObjectLiteral.prototype.createEntity = function () {
    return this.this$World.createEntity();
  };
  World$facade$ObjectLiteral.prototype.deleteEntity_5mschp$ = function (entity) {
    this.this$World.deleteEntity_5mschp$(entity);
  };
  World$facade$ObjectLiteral.prototype.query_5wk62m$ = function (family) {
    return this.this$World.query_5wk62m$(family);
  };
  World$facade$ObjectLiteral.prototype.query_qvsabv$ = function (componentClass) {
    return this.this$World.query_qvsabv$(componentClass);
  };
  World$facade$ObjectLiteral.$metadata$ = {
    kind: Kind_CLASS,
    interfaces: [World$Facade]
  };
  World.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'World',
    interfaces: []
  };
  var package$bitspittle = _.bitspittle || (_.bitspittle = {});
  var package$kross2d = package$bitspittle.kross2d || (package$bitspittle.kross2d = {});
  var package$extras = package$kross2d.extras || (package$kross2d.extras = {});
  var package$ecs = package$extras.ecs || (package$extras.ecs = {});
  package$ecs.Component = Component;
  package$ecs.DrawSystem = DrawSystem;
  $$importsForInline$$['kross2d-ecs'] = _;
  package$ecs.Entity = Entity;
  Object.defineProperty(Family, 'Companion', {
    get: Family$Companion_getInstance
  });
  package$ecs.Family = Family;
  package$ecs.SingleFamily = SingleFamily;
  package$ecs.FamilyGroup = FamilyGroup;
  package$ecs.UpdateSystem = UpdateSystem;
  package$ecs.EntityAlreadyDeletedException = EntityAlreadyDeletedException;
  package$ecs.ProcessingWorldException = ProcessingWorldException;
  World.ImmutableFacade = World$ImmutableFacade;
  World.Facade = World$Facade;
  package$ecs.World = World;
  Kotlin.defineModule('kross2d-ecs', _);
  return _;
}(typeof this['kross2d-ecs'] === 'undefined' ? {} : this['kross2d-ecs'], kotlin);
