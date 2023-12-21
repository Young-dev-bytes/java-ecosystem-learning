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

 * A read/write HeapFloatBuffer.






 */

class HeapFloatBuffer
    extends FloatBuffer
{
    // Cached array base offset
    private static final long ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(float[].class);

    // Cached array index scale
    private static final long ARRAY_INDEX_SCALE = UNSAFE.arrayIndexScale(float[].class);

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*

    protected final float[] hb;
    protected final int offset;

    */

    HeapFloatBuffer(int cap, int lim) {            // package-private

        super(-1, 0, lim, cap, new float[cap], 0);
        /*
        hb = new float[cap];
        offset = 0;
        */
        this.address = ARRAY_BASE_OFFSET;




    }

    HeapFloatBuffer(float[] buf, int off, int len) { // package-private

        super(-1, off, off + len, buf.length, buf, 0);
        /*
        hb = buf;
        offset = 0;
        */
        this.address = ARRAY_BASE_OFFSET;




    }

    protected HeapFloatBuffer(float[] buf,
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

    public FloatBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapFloatBuffer(hb,
                                        -1,
                                        0,
                                        rem,
                                        rem,
                                        pos + offset);
    }















    public FloatBuffer duplicate() {
        return new HeapFloatBuffer(hb,
                                        this.markValue(),
                                        this.position(),
                                        this.limit(),
                                        this.capacity(),
                                        offset);
    }

    public FloatBuffer asReadOnlyBuffer() {

        return new HeapFloatBufferR(hb,
                                     this.markValue(),
                                     this.position(),
                                     this.limit(),
                                     this.capacity(),
                                     offset);



    }



    protected int ix(int i) {
        return i + offset;
    }







    public float get() {
        return hb[ix(nextGetIndex())];
    }

    public float get(int i) {
        return hb[ix(checkIndex(i))];
    }







    public FloatBuffer get(float[] dst, int offset, int length) {
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

    public FloatBuffer put(float x) {

        hb[ix(nextPutIndex())] = x;
        return this;



    }

    public FloatBuffer put(int i, float x) {

        hb[ix(checkIndex(i))] = x;
        return this;



    }

    public FloatBuffer put(float[] src, int offset, int length) {

        checkBounds(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();
        System.arraycopy(src, offset, hb, ix(pos), length);
        position(pos + length);
        return this;



    }

    public FloatBuffer put(FloatBuffer src) {

            super.put(src);
        return this;



    }

    public FloatBuffer compact() {

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

