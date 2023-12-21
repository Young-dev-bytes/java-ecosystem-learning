/*
 * Copyright (c) 2009, 2020, Oracle and/or its affiliates. All rights reserved.
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

import static jdk.vm.ci.hotspot.HotSpotJVMCIRuntime.runtime;

import jdk.vm.ci.meta.Assumptions;
import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * Represents a constant non-{@code null} object reference, within the compiler and across the
 * compiler/runtime interface.
 */
abstract class HotSpotObjectConstantImpl implements HotSpotObjectConstant {

    protected final boolean compressed;

    HotSpotObjectConstantImpl(boolean compressed) {
        this.compressed = compressed;
    }

    @Override
    public JavaKind getJavaKind() {
        return JavaKind.Object;
    }

    @Override
    public boolean isCompressed() {
        return compressed;
    }

    @Override
    public abstract JavaConstant compress();

    @Override
    public abstract JavaConstant uncompress();

    @Override
    public HotSpotResolvedObjectType getType() {
        return runtime().reflection.getType(this);
    }

    @Override
    public abstract int getIdentityHashCode();

    private boolean isFullyInitializedConstantCallSite() {
        if (!runtime().getConstantCallSite().isInstance(this)) {
            return false;
        }
        // read ConstantCallSite.isFrozen as a volatile field
        HotSpotResolvedJavaField field = HotSpotMethodHandleAccessProvider.Internals.instance().constantCallSiteFrozenField;
        boolean isFrozen = readFieldValue(field, true /* volatile */).asBoolean();
        // isFrozen true implies fully-initialized
        return isFrozen;
    }

    private HotSpotObjectConstantImpl readTarget() {
        // read CallSite.target as a volatile field
        HotSpotResolvedJavaField field = HotSpotMethodHandleAccessProvider.Internals.instance().callSiteTargetField;
        return (HotSpotObjectConstantImpl) readFieldValue(field, true /* volatile */);
    }

    @Override
    public JavaConstant getCallSiteTarget(Assumptions assumptions) {
        if (runtime().getCallSite().isInstance(this)) {
            // For ConstantCallSites, we need to read "isFrozen" before reading "target"
            // isFullyInitializedConstantCallSite() reads "isFrozen"
            if (!isFullyInitializedConstantCallSite()) {
                if (assumptions == null) {
                    return null;
                }
                assumptions.record(new Assumptions.CallSiteTargetValue(this, readTarget()));
            }
            return readTarget();
        }
        return null;
    }

    @Override
    public boolean isInternedString() {
        return runtime().compilerToVm.isInternedString(this);
    }

    @Override
    public <T> T asObject(Class<T> type) {
        return runtime().reflection.asObject(this, type);
    }

    @Override
    public Object asObject(ResolvedJavaType type) {
        return runtime().reflection.asObject(this, (HotSpotResolvedJavaType) type);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isDefaultForKind() {
        return false;
    }

    @Override
    public Object asBoxedPrimitive() {
        throw new IllegalArgumentException();
    }

    @Override
    public int asInt() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean asBoolean() {
        throw new IllegalArgumentException();
    }

    @Override
    public long asLong() {
        throw new IllegalArgumentException();
    }

    @Override
    public float asFloat() {
        throw new IllegalArgumentException();
    }

    @Override
    public double asDouble() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof HotSpotObjectConstantImpl) {
            HotSpotObjectConstantImpl other = (HotSpotObjectConstantImpl) o;
            return runtime().reflection.equals(this, other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getIdentityHashCode();
    }

    @Override
    public String toValueString() {
        if (runtime().getJavaLangString().isInstance(this)) {
            return "\"" + runtime().reflection.asString(this) + "\"";
        } else {
            return runtime().reflection.formatString(this);
        }
    }

    @Override
    public String toString() {
        return (compressed ? "NarrowOop" : getJavaKind().getJavaName()) + "[" + runtime().reflection.formatString(this) + "]";
    }

    public JavaConstant readFieldValue(HotSpotResolvedJavaField field, boolean isVolatile) {
        return runtime().reflection.readFieldValue(this, field, isVolatile);
    }

    public ResolvedJavaType asJavaType() {
        return runtime().reflection.asJavaType(this);
    }
}
