/*
 * Copyright (c) 2000, 2016, Oracle and/or its affiliates. All rights reserved.
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

// -- This file was mechanically generated: Do not edit! -- //

package java.nio;

/**

 * A read/write HeapDoubleBuffer.






 */

class HeapDoubleBuffer
    extends DoubleBuffer
{
    // Cached array base offset
    private static final long ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(double[].class);

    // Cached array index scale
    private static final long ARRAY_INDEX_SCALE = UNSAFE.arrayIndexScale(double[].class);

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*

    protected final double[] hb;
    protected final int offset;

    */

    HeapDoubleBuffer(int cap, int lim) {            // package-private

        super(-1, 0, lim, cap, new double[cap], 0);
        /*
        hb = new double[cap];
        offset = 0;
        */
        this.address = ARRAY_BASE_OFFSET;




    }

    HeapDoubleBuffer(double[] buf, int off, int len) { // package-private

        super(-1, off, off + len, buf.length, buf, 0);
        /*
        hb = buf;
        offset = 0;
        */
        this.address = ARRAY_BASE_OFFSET;




    }

    protected HeapDoubleBuffer(double[] buf,
                                   int mark, int pos, int lim, int cap,
                                   int off)
    {

        super(mark, pos, lim, cap, buf, off);
        /*
        hb = buf;
        offset = off;
        */
        this.address = ARRAY_BASE_OFFSET + off * ARRAY_INDEX_SCALE;




    }

    public DoubleBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapDoubleBuffer(hb,
                                        -1,
                                        0,
                                        rem,
                                        rem,
                                        pos + offset);
    }















    public DoubleBuffer duplicate() {
        return new HeapDoubleBuffer(hb,
                                        this.markValue(),
                                        this.position(),
                                        this.limit(),
                                        this.capacity(),
                                        offset);
    }

    public DoubleBuffer asReadOnlyBuffer() {

        return new HeapDoubleBufferR(hb,
                                     this.markValue(),
                                     this.position(),
                                     this.limit(),
                                     this.capacity(),
                                     offset);



    }



    protected int ix(int i) {
        return i + offset;
    }







    public double get() {
        return hb[ix(nextGetIndex())];
    }

    public double get(int i) {
        return hb[ix(checkIndex(i))];
    }







    public DoubleBuffer get(double[] dst, int offset, int length) {
        checkBounds(offset, length, dst.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferUnderflowException();
        System.arraycopy(hb, ix(pos), dst, offset, length);
        position(pos + length);
        return this;
    }

    public boolean isDirect() {
        return false;
    }



    public boolean isReadOnly() {
        return false;
    }

    public DoubleBuffer put(double x) {

        hb[ix(nextPutIndex())] = x;
        return this;



    }

    public DoubleBuffer put(int i, double x) {

        hb[ix(checkIndex(i))] = x;
        return this;



    }

    public DoubleBuffer put(double[] src, int offset, int length) {

        checkBounds(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();
        System.arraycopy(src, offset, hb, ix(pos), length);
        position(pos + length);
        return this;



    }

    public DoubleBuffer put(DoubleBuffer src) {

            super.put(src);
        return this;



    }

    public DoubleBuffer compact() {

        int pos = position();
        int lim = limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        System.arraycopy(hb, ix(pos), hb, ix(0), rem);
        position(rem);
        limit(capacity());
        discardMark();
        return this;



    }




















































































































































































































































































































































































    public ByteOrder order() {
        return ByteOrder.nativeOrder();
    }







}

