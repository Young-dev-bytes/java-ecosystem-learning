/*
 * Copyright (c) 2018, 2019, Oracle and/or its affiliates. All rights reserved.
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
package jdk.vm.ci.hotspot;

import static jdk.vm.ci.hotspot.CompilerToVM.compilerToVM;
import static jdk.vm.ci.hotspot.HotSpotJVMCIRuntime.runtime;
import static jdk.vm.ci.hotspot.UnsafeAccess.UNSAFE;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

import jdk.vm.ci.common.JVMCIError;
import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.MetaAccessProvider;
import jdk.vm.ci.meta.ResolvedJavaField;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * Implementation of {@link HotSpotJVMCIReflection} in terms of standard JDK reflection API. This is
 * only available when running in the HotSpot heap.
 */
final class HotSpotJDKReflection extends HotSpotJVMCIReflection {

    @Override
    Object resolveObject(HotSpotObjectConstantImpl object) {
        if (object == null) {
            return null;
        }
        return ((DirectHotSpotObjectConstantImpl) object).object;
    }

    @Override
    boolean isInstance(HotSpotResolvedObjectTypeImpl holder, HotSpotObjectConstantImpl obj) {
        Class<?> javaMirror = getMirror(holder);
        Object value = resolveObject(obj);
        return javaMirror.isInstance(value);
    }

    @Override
    boolean isAssignableFrom(HotSpotResolvedObjectTypeImpl holder, HotSpotResolvedObjectTypeImpl otherType) {
        Class<?> javaMirror = getMirror(holder);
        return javaMirror.isAssignableFrom(getMirror(otherType));

    }

    @Override
    Annotation[] getAnnotations(HotSpotResolvedObjectTypeImpl holder) {
        Class<?> javaMirror = getMirror(holder);
        return javaMirror.getAnnotations();
    }

    @Override
    Annotation[] getDeclaredAnnotations(HotSpotResolvedObjectTypeImpl holder) {
        Class<?> javaMirror = getMirror(holder);
        return javaMirror.getDeclaredAnnotations();
    }

    @Override
    <T extends Annotation> T getAnnotation(HotSpotResolvedObjectTypeImpl holder, Class<T> annotationClass) {
        Class<?> javaMirror = getMirror(holder);
        return javaMirror.getAnnotation(annotationClass);
    }

    @Override
    boolean isLocalClass(HotSpotResolvedObjectTypeImpl holder) {
        Class<?> javaMirror = getMirror(holder);
        return javaMirror.isLocalClass();
    }

    @Override
    boolean isMemberClass(HotSpotResolvedObjectTypeImpl holder) {
        Class<?> javaMirror = getMirror(holder);
        return javaMirror.isMemberClass();
    }

    @Override
    HotSpotResolvedObjectType getEnclosingClass(HotSpotResolvedObjectTypeImpl holder) {
        Class<?> javaMirror = getMirror(holder);
        return (HotSpotResolvedObjectType) runtime().fromClass(javaMirror.getEnclosingClass());
    }

    @Override
    JavaConstant readFieldValue(HotSpotResolvedObjectTypeImpl holder, HotSpotResolvedJavaField field, boolean isVolatile) {
        Class<?> javaMirror = getMirror(holder);
        return readFieldValue(field, javaMirror, isVolatile);
    }

    @Override
    JavaConstant readFieldValue(HotSpotObjectConstantImpl object, HotSpotResolvedJavaField field, boolean isVolatile) {
        Object value = resolveObject(object);
        return readFieldValue(field, value, isVolatile);
    }

    @Override
    boolean equals(HotSpotObjectConstantImpl a, HotSpotObjectConstantImpl b) {
        return resolveObject(a) == resolveObject(b) && a.isCompressed() == b.isCompressed();
    }

    @Override
    JavaConstant getJavaMirror(HotSpotResolvedPrimitiveType holder) {
        return holder.mirror;
    }

    @Override
    ResolvedJavaMethod.Parameter[] getParameters(HotSpotResolvedJavaMethodImpl javaMethod) {
        java.lang.reflect.Parameter[] javaParameters = getMethod(javaMethod).getParameters();
        ResolvedJavaMethod.Parameter[] res = new ResolvedJavaMethod.Parameter[javaParameters.length];
        for (int i = 0; i < res.length; i++) {
            java.lang.reflect.Parameter src = javaParameters[i];
            String paramName = src.isNamePresent() ? src.getName() : null;
            res[i] = new ResolvedJavaMethod.Parameter(paramName, src.getModifiers(), javaMethod, i);
        }
        return res;
    }

    @Override
    Annotation[][] getParameterAnnotations(HotSpotResolvedJavaMethodImpl javaMethod) {
        return getMethod(javaMethod).getParameterAnnotations();
    }

    @Override
    Type[] getGenericParameterTypes(HotSpotResolvedJavaMethodImpl javaMethod) {
        return getMethod(javaMethod).getGenericParameterTypes();
    }

    @Override
    Annotation[] getFieldAnnotations(HotSpotResolvedJavaFieldImpl javaField) {
        return getField(javaField).getAnnotations();
    }

    @Override
    Annotation[] getMethodAnnotations(HotSpotResolvedJavaMethodImpl javaMethod) {
        return getMethod(javaMethod).getAnnotations();
    }

    @Override
    Annotation[] getMethodDeclaredAnnotations(HotSpotResolvedJavaMethodImpl javaMethod) {
        return getMethod(javaMethod).getDeclaredAnnotations();
    }

    @Override
    Annotation[] getFieldDeclaredAnnotations(HotSpotResolvedJavaFieldImpl javaField) {
        return getField(javaField).getDeclaredAnnotations();
    }

    @Override
    <T extends Annotation> T getMethodAnnotation(HotSpotResolvedJavaMethodImpl javaMethod, Class<T> annotationClass) {
        return getMethod(javaMethod).getAnnotation(annotationClass);
    }

    @Override
    <T extends Annotation> T getFieldAnnotation(HotSpotResolvedJavaFieldImpl javaField, Class<T> annotationClass) {
        return getField(javaField).getAnnotation(annotationClass);
    }

    @Override
    HotSpotResolvedObjectTypeImpl getType(HotSpotObjectConstantImpl object) {
        Object value = resolveObject(object);
        Class<?> theClass = value.getClass();
        return (HotSpotResolvedObjectTypeImpl) runtime().fromClass(theClass);
    }

    @Override
    String asString(HotSpotObjectConstantImpl object) {
        Object value = resolveObject(object);
        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    @Override
    ResolvedJavaType asJavaType(HotSpotObjectConstantImpl object) {
        Object value = resolveObject(object);
        if (value instanceof Class) {
            Class<?> javaClass = (Class<?>) value;
            return runtime().fromClass(javaClass);
        }
        if (value instanceof ResolvedJavaType) {
            return (ResolvedJavaType) value;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    <T> T asObject(HotSpotObjectConstantImpl object, Class<T> type) {
        Object value = resolveObject(object);
        if (type.isInstance(value)) {
            return (T) value;
        }
        return null;
    }

    @Override
    Object asObject(HotSpotObjectConstantImpl object, HotSpotResolvedJavaType type) {
        Object value = resolveObject(object);
        if (getMirror(type).isInstance(value)) {
            return value;
        }
        return null;
    }

    @Override
    String formatString(HotSpotObjectConstantImpl object) {
        return JavaKind.Object.format(resolveObject(object));
    }

    @Override
    Integer getLength(HotSpotObjectConstantImpl arrayObject) {
        Object object = resolveObject(arrayObject);
        if (object.getClass().isArray()) {
            return Array.getLength(object);
        }
        return null;
    }

    @Override
    JavaConstant readArrayElement(HotSpotObjectConstantImpl arrayObject, int index) {
        Object a = resolveObject(arrayObject);
        if (!a.getClass().isArray() || index < 0 || index >= Array.getLength(a)) {
            return null;
        }
        if (a instanceof Object[]) {
            Object element = ((Object[]) a)[index];
            return forObject(element);
        } else {
            if (a instanceof int[]) {
                return JavaConstant.forInt(((int[]) a)[index]);
            } else if (a instanceof char[]) {
                return JavaConstant.forChar(((char[]) a)[index]);
            } else if (a instanceof byte[]) {
                return JavaConstant.forByte(((byte[]) a)[index]);
            } else if (a instanceof long[]) {
                return JavaConstant.forLong(((long[]) a)[index]);
            } else if (a instanceof short[]) {
                return JavaConstant.forShort(((short[]) a)[index]);
            } else if (a instanceof float[]) {
                return JavaConstant.forFloat(((float[]) a)[index]);
            } else if (a instanceof double[]) {
                return JavaConstant.forDouble(((double[]) a)[index]);
            } else if (a instanceof boolean[]) {
                return JavaConstant.forBoolean(((boolean[]) a)[index]);
            } else {
                throw new JVMCIError("Should not reach here");
            }
        }
    }

    @Override
    JavaConstant unboxPrimitive(HotSpotObjectConstantImpl source) {
        return JavaConstant.forBoxedPrimitive(resolveObject(source));
    }

    @Override
    JavaConstant forObject(Object value) {
        if (value == null) {
            return JavaConstant.NULL_POINTER;
        }
        return forNonNullObject(value);
    }

    private static HotSpotObjectConstantImpl forNonNullObject(Object value) {
        return DirectHotSpotObjectConstantImpl.forNonNullObject(value, false);
    }

    @Override
    JavaConstant boxPrimitive(JavaConstant source) {
        return forNonNullObject(source.asBoxedPrimitive());
    }

    @Override
    int getInt(HotSpotObjectConstantImpl object, long displacement) {
        return UNSAFE.getInt((resolveObject(object)), displacement);
    }

    @Override
    byte getByte(HotSpotObjectConstantImpl object, long displacement) {
        return UNSAFE.getByte(resolveObject(object), displacement);
    }

    @Override
    short getShort(HotSpotObjectConstantImpl object, long displacement) {
        return UNSAFE.getShort(resolveObject(object), displacement);
    }

    @Override
    long getLong(HotSpotObjectConstantImpl object, long displacement) {
        return UNSAFE.getLong(resolveObject(object), displacement);
    }

    @Override
    void checkRead(HotSpotObjectConstantImpl constant, JavaKind kind, long displacement, HotSpotResolvedObjectType type) {
        checkRead(kind, displacement, type, resolveObject(constant));
    }

    /**
     * Offset of injected {@code java.lang.Class::oop_size} field. No need to make {@code volatile}
     * as initialization is idempotent.
     */
    private long oopSizeOffset;

    private static int computeOopSizeOffset(HotSpotJVMCIRuntime runtime) {
        MetaAccessProvider metaAccess = runtime.getHostJVMCIBackend().getMetaAccess();
        ResolvedJavaType staticType = metaAccess.lookupJavaType(Class.class);
        for (ResolvedJavaField f : staticType.getInstanceFields(false)) {
            if (f.getName().equals("oop_size")) {
                int offset = f.getOffset();
                assert offset != 0 : "not expecting offset of java.lang.Class::oop_size to be 0";
                return offset;
            }
        }
        throw new JVMCIError("Could not find injected java.lang.Class::oop_size field");
    }

    long oopSizeOffset() {
        if (oopSizeOffset == 0) {
            oopSizeOffset = computeOopSizeOffset(runtime());
        }
        return oopSizeOffset;
    }

    private boolean checkRead(JavaKind kind, long displacement, HotSpotResolvedObjectType type, Object object) {
        if (type.isArray()) {
            ResolvedJavaType componentType = type.getComponentType();
            JavaKind componentKind = componentType.getJavaKind();
            final int headerSize = runtime().getArrayBaseOffset(componentKind);
            int sizeOfElement = runtime().getArrayIndexScale(componentKind);
            int length = Array.getLength(object);
            long arrayEnd = headerSize + (sizeOfElement * length);
            boolean aligned = ((displacement - headerSize) % sizeOfElement) == 0;
            if (displacement < 0 || displacement > (arrayEnd - sizeOfElement) || (kind == JavaKind.Object && !aligned)) {
                int index = (int) ((displacement - headerSize) / sizeOfElement);
                throw new IllegalArgumentException("Unsafe array access: reading element of kind " + kind +
                                " at offset " + displacement + " (index ~ " + index + ") in " +
                                type.toJavaName() + " object of length " + length);
            }
        } else if (kind != JavaKind.Object) {
            long size;
            if (object instanceof Class) {
                int wordSize = runtime().getHostJVMCIBackend().getCodeCache().getTarget().wordSize;
                size = UNSAFE.getInt(object, oopSizeOffset()) * wordSize;
            } else {
                size = Math.abs(type.instanceSize());
            }
            int bytesToRead = kind.getByteCount();
            if (displacement + bytesToRead > size || displacement < 0) {
                throw new IllegalArgumentException("Unsafe access: reading " + bytesToRead + " bytes at offset " + displacement + " in " +
                                type.toJavaName() + " object of size " + size);
            }
        } else {
            ResolvedJavaField field = null;
            if (object instanceof Class) {
                // Read of a static field
                HotSpotResolvedJavaType hotSpotResolvedJavaType = runtime().fromClass((Class<?>) object);
                if (hotSpotResolvedJavaType instanceof HotSpotResolvedObjectTypeImpl) {
                    HotSpotResolvedObjectTypeImpl staticFieldsHolder = (HotSpotResolvedObjectTypeImpl) hotSpotResolvedJavaType;
                    field = staticFieldsHolder.findStaticFieldWithOffset(displacement, JavaKind.Object);
                }
            }
            if (field == null) {
                field = type.findInstanceFieldWithOffset(displacement, JavaKind.Object);
            }
            if (field == null) {
                throw new IllegalArgumentException("Unsafe object access: field not found for read of kind Object" +
                                " at offset " + displacement + " in " + type.toJavaName() + " object");
            }
            if (field.getJavaKind() != JavaKind.Object) {
                throw new IllegalArgumentException("Unsafe object access: field " + field.format("%H.%n:%T") + " not of expected kind Object" +
                                " at offset " + displacement + " in " + type.toJavaName() + " object");
            }
        }
        return true;
    }

    JavaConstant readFieldValue(HotSpotResolvedJavaField field, Object obj, boolean isVolatile) {
        assert obj != null;
        assert !field.isStatic() || obj instanceof Class;
        long displacement = field.getOffset();
        if (obj instanceof Class && field.getName().equals("componentType")) {
            Class<?> clazz = (Class<?>) obj;
            if (!clazz.isArray()) {
                // Class.componentType for non-array classes can transiently contain an int[] that's
                // used for locking so always return null to mimic Class.getComponentType()
                return JavaConstant.NULL_POINTER;
            }
        }

        assert checkRead(field.getJavaKind(), displacement,
                        (HotSpotResolvedObjectType) runtime().getHostJVMCIBackend().getMetaAccess().lookupJavaType(field.isStatic() ? (Class<?>) obj : obj.getClass()),
                        obj);
        JavaKind kind = field.getJavaKind();
        switch (kind) {
            case Boolean:
                return JavaConstant.forBoolean(isVolatile ? UNSAFE.getBooleanVolatile(obj, displacement) : UNSAFE.getBoolean(obj, displacement));
            case Byte:
                return JavaConstant.forByte(isVolatile ? UNSAFE.getByteVolatile(obj, displacement) : UNSAFE.getByte(obj, displacement));
            case Char:
                return JavaConstant.forChar(isVolatile ? UNSAFE.getCharVolatile(obj, displacement) : UNSAFE.getChar(obj, displacement));
            case Short:
                return JavaConstant.forShort(isVolatile ? UNSAFE.getShortVolatile(obj, displacement) : UNSAFE.getShort(obj, displacement));
            case Int:
                return JavaConstant.forInt(isVolatile ? UNSAFE.getIntVolatile(obj, displacement) : UNSAFE.getInt(obj, displacement));
            case Long:
                return JavaConstant.forLong(isVolatile ? UNSAFE.getLongVolatile(obj, displacement) : UNSAFE.getLong(obj, displacement));
            case Float:
                return JavaConstant.forFloat(isVolatile ? UNSAFE.getFloatVolatile(obj, displacement) : UNSAFE.getFloat(obj, displacement));
            case Double:
                return JavaConstant.forDouble(isVolatile ? UNSAFE.getDoubleVolatile(obj, displacement) : UNSAFE.getDouble(obj, displacement));
            case Object:
                return forObject(isVolatile ? UNSAFE.getObjectVolatile(obj, displacement) : UNSAFE.getObject(obj, displacement));
            default:
                throw new IllegalArgumentException("Unsupported kind: " + kind);

        }
    }

    /**
     * Gets a {@link Method} object corresponding to {@code method}. This method guarantees the same
     * {@link Method} object is returned if called twice on the same {@code method} value.
     */
    private static Executable getMethod(HotSpotResolvedJavaMethodImpl method) {
        assert !method.isClassInitializer() : method;
        if (method.toJavaCache == null) {
            synchronized (method) {
                if (method.toJavaCache == null) {
                    method.toJavaCache = compilerToVM().asReflectionExecutable(method);
                }
            }
        }
        return method.toJavaCache;
    }

    /**
     * Gets a {@link Field} object corresponding to {@code field}. This method guarantees the same
     * {@link Field} object is returned if called twice on the same {@code field} value. This is
     * required to ensure the results of {@link HotSpotResolvedJavaFieldImpl#getAnnotations()} and
     * {@link HotSpotResolvedJavaFieldImpl#getAnnotation(Class)} are stable (i.e., for a given field
     * {@code f} and annotation class {@code a}, the same object is returned for each call to
     * {@code f.getAnnotation(a)}).
     */
    private static Field getField(HotSpotResolvedJavaFieldImpl field) {
        HotSpotResolvedObjectTypeImpl declaringClass = field.getDeclaringClass();
        synchronized (declaringClass) {
            HashMap<HotSpotResolvedJavaFieldImpl, Field> cache = declaringClass.reflectionFieldCache;
            if (cache == null) {
                cache = new HashMap<>();
                declaringClass.reflectionFieldCache = cache;
            }
            Field reflect = cache.get(field);
            if (reflect == null) {
                reflect = compilerToVM().asReflectionField(field.getDeclaringClass(), field.getIndex());
                cache.put(field, reflect);
            }
            return reflect;
        }
    }

    Class<?> getMirror(HotSpotResolvedObjectTypeImpl holder) {
        return (Class<?>) resolveObject((HotSpotObjectConstantImpl) holder.getJavaMirror());
    }

    Class<?> getMirror(HotSpotResolvedJavaType type) {
        assert type != null;
        if (type instanceof HotSpotResolvedPrimitiveType) {
            return (Class<?>) resolveObject(((HotSpotResolvedPrimitiveType) type).mirror);
        } else {
            return getMirror((HotSpotResolvedObjectTypeImpl) type);
        }
    }
}

