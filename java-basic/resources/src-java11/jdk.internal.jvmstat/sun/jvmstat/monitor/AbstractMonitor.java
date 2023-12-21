/*
 * Copyright (c) 2004, 2010, Oracle and/or its affiliates. All rights reserved.
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
 *
 *
 */

package sun.jvmstat.monitor;

/**
 * The base class for Instrumentation Monitoring Objects. This base class
 * provides implementations of the {@link Monitor} methods that are common
 * to all classes implementing the Monitor interface..
 *
 * @author Brian Doherty
 * @since 1.5
 */
public abstract class AbstractMonitor implements Monitor  {
    protected String name;
    protected Units units;
    protected Variability variability;
    protected int vectorLength;
    protected boolean supported;

    /**
     * Create a vector instrumentation monitoring object with the given
     * name and attributes.
     *
     * @param name the name to assign to this instrumentation object.
     * @param units the units of measure attribute
     * @param variability the variability attribute
     * @param supported support level indicator
     * @param vectorLength the length of the vector, or 0 if not a vector type.
     */
    protected AbstractMonitor(String name, Units units, Variability variability,
                              boolean supported, int vectorLength) {
        this.name = name;
        this.units = units;
        this.variability = variability;
        this.vectorLength = vectorLength;
        this.supported = supported;
    }

    /**
     * Create a scalar instrumentation monitoring object with the given
     * name and attributes.
     *
     * @param name the name to assign to this instrumentation object.
     * @param units the units of measure attribute
     * @param variability the variability attribute
     * @param supported support level indicator
     */
    protected AbstractMonitor(String name, Units units, Variability variability,
                              boolean supported) {
        this(name, units, variability, supported, 0);
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public String getBaseName() {
        int baseIndex = name.lastIndexOf('.') + 1;
        return name.substring(baseIndex);
    }

    /**
     * {@inheritDoc}
     */
    public Units getUnits() {
        return units;
    }

    /**
     * {@inheritDoc}
     */
    public Variability getVariability() {
        return variability;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isVector() {
        return vectorLength > 0;
    }

    /**
     * {@inheritDoc}
     */
    public int getVectorLength() {
        return vectorLength;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupported() {
        return supported;
    }

    /**
     * {@inheritDoc}
     */
    public abstract Object getValue();
}
