/*
 * Copyright (c) 1997, 2011, Oracle and/or its affiliates. All rights reserved.
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

package java.awt.image;


/**
 * The {@code Kernel} class defines a matrix that describes how a
 * specified pixel and its surrounding pixels affect the value
 * computed for the pixel's position in the output image of a filtering
 * operation.  The X origin and Y origin indicate the kernel matrix element
 * that corresponds to the pixel position for which an output value is
 * being computed.
 *
 * @see ConvolveOp
 */
public class Kernel implements Cloneable {
    private int  width;
    private int  height;
    private int  xOrigin;
    private int  yOrigin;
    private float data[];

    private static native void initIDs();
    static {
        ColorModel.loadLibraries();
        initIDs();
    }

    /**
     * Constructs a {@code Kernel} object from an array of floats.
     * The first {@code width}*{@code height} elements of
     * the {@code data} array are copied.
     * If the length of the {@code data} array is less
     * than width*height, an {@code IllegalArgumentException} is thrown.
     * The X origin is (width-1)/2 and the Y origin is (height-1)/2.
     * @param width         width of the kernel
     * @param height        height of the kernel
     * @param data          kernel data in row major order
     * @throws IllegalArgumentException if the length of {@code data}
     *         is less than the product of {@code width} and
     *         {@code height}
     */
    public Kernel(int width, int height, float data[]) {
        this.width  = width;
        this.height = height;
        this.xOrigin  = (width-1)>>1;
        this.yOrigin  = (height-1)>>1;
        int len = width*height;
        if (data.length < len) {
            throw new IllegalArgumentException("Data array too small "+
                                               "(is "+data.length+
                                               " and should be "+len);
        }
        this.data = new float[len];
        System.arraycopy(data, 0, this.data, 0, len);

    }

    /**
     * Returns the X origin of this {@code Kernel}.
     * @return the X origin.
     */
    public final int getXOrigin(){
        return xOrigin;
    }

    /**
     * Returns the Y origin of this {@code Kernel}.
     * @return the Y origin.
     */
    public final int getYOrigin() {
        return yOrigin;
    }

    /**
     * Returns the width of this {@code Kernel}.
     * @return the width of this {@code Kernel}.
     */
    public final int getWidth() {
        return width;
    }

    /**
     * Returns the height of this {@code Kernel}.
     * @return the height of this {@code Kernel}.
     */
    public final int getHeight() {
        return height;
    }

    /**
     * Returns the kernel data in row major order.
     * The {@code data} array is returned.  If {@code data}
     * is {@code null}, a new array is allocated.
     * @param data  if non-null, contains the returned kernel data
     * @return the {@code data} array containing the kernel data
     *         in row major order or, if {@code data} is
     *         {@code null}, a newly allocated array containing
     *         the kernel data in row major order
     * @throws IllegalArgumentException if {@code data} is less
     *         than the size of this {@code Kernel}
     */
    public final float[] getKernelData(float[] data) {
        if (data == null) {
            data = new float[this.data.length];
        }
        else if (data.length < this.data.length) {
            throw new IllegalArgumentException("Data array too small "+
                                               "(should be "+this.data.length+
                                               " but is "+
                                               data.length+" )");
        }
        System.arraycopy(this.data, 0, data, 0, this.data.length);

        return data;
    }

    /**
     * Clones this object.
     * @return a clone of this object.
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }
}
