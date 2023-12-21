/*
 * Copyright (c) 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.jvm.hotspot.utilities.soql;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import javax.script.Invocable;

/**
 * Simple implementation of ScriptObject interface
 * with property storage backed by a Map. This class
 * can be extended to override any of the methods, in
 * particular to add "function" valued properties.
 */
public class MapScriptObject implements ScriptObject {
  // map to store the properties
  private Map map;

  public MapScriptObject() {
    this(new HashMap());
  }

  public MapScriptObject(Map map) {
    // make it synchronized
    this.map = Collections.synchronizedMap(map);
  }

  public Object[] getIds() {
    return map.keySet().toArray();
  }

  public Object get(String name) {
    if (has(name)) {
      return map.get(name);
    } else {
      return UNDEFINED;
    }
  }

  public Object get(int index) {
    if (has(index)) {
      Object key = Integer.valueOf(index);
      return map.get(key);
    } else {
      return UNDEFINED;
    }
  }

  public void put(String name, Object value) {
    map.put(name, value);
  }

  public void put(int index, Object value) {
    map.put(Integer.valueOf(index), value);
  }

  public boolean has(String name) {
    return map.containsKey(name);
  }

  public boolean has(int index) {
    return map.containsKey(Integer.valueOf(index));
  }

  public boolean delete(String name) {
    if (map.containsKey(name)) {
      map.remove(name);
      return true;
    } else {
      return false;
    }
  }

  public boolean delete(int index) {
    Object key = Integer.valueOf(index);
    if (map.containsKey(key)) {
      map.remove(key);
      return true;
    } else {
      return false;
    }
  }

  // add a function valued property that invokes given Method
  protected void putFunction(Object target, Method method) {
    putFunction(target, method, true);
  }

  // add a function valued property that invokes given Method
  protected void putFunction(Object target, Method method, boolean wrapArgs) {
    map.put(method.getName(), new MethodCallable(target, method, wrapArgs));
  }

  // add a function valued property that invokes given script function
  protected void putFunction(Object target, String name, Invocable invocable) {
    map.put(name, new InvocableCallable(target, name, invocable));
  }
}
