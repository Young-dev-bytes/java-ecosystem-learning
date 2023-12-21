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

 * A read/write HeapIntBuffer.






 */

class HeapIntBuffer
    extends IntBuffer
{
    // Cached array base offset
    private static final long ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(int[].class);

    // Cached array index scale
    private static final long ARRAY_INDEX_SCALE = UNSAFE.arrayIndexScale(int[].class);

    // For speed these fields are actually declared in X-Buffer;
    // these declarations are here as documentation
    /*

    protected final int[] hb;
    protected final int offset;

    */

    HeapIntBuffer(int cap, int lim) {            // package-private

        super(-1, 0, lim, cap, new int[cap], 0);
        /*
        hb = new int[cap];
        offset = 0;
        */
        this.address = ARRAY_BASE_OFFSET;




    }

    HeapIntBuffer(int[] buf, int off, int len) { // package-private

        super(-1, off, off + len, buf.length, buf, 0);
        /*
        hb = buf;
        offset = 0;
        */
        this.address = ARRAY_BASE_OFFSET;




    }

    protected HeapIntBuffer(int[] buf,
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

    public IntBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        return new HeapIntBuffer(hb,
                                        -1,
                                        0,
                                        rem,
                                        rem,
                                        pos + offset);
    }















    public IntBuffer duplicate() {
        return new HeapIntBuffer(hb,
                                        this.markValue(),
                                        this.position(),
                                        this.limit(),
                                        this.capacity(),
                                        offset);
    }

    public IntBuffer asReadOnlyBuffer() {

        return new HeapIntBufferR(hb,
                                     this.markValue(),
                                     this.position(),
                                     this.limit(),
                                     this.capacity(),
                                     offset);



    }



    protected int ix(int i) {
        return i + offset;
    }







    public int get() {
        return hb[ix(nextGetIndex())];
    }

    public int get(int i) {
        return hb[ix(checkIndex(i))];
    }







    public IntBuffer get(int[] dst, int offset, int length) {
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

    public IntBuffer put(int x) {

        hb[ix(nextPutIndex())] = x;
        return this;



    }

    public IntBuffer put(int i, int x) {

        hb[ix(checkIndex(i))] = x;
        return this;



    }

    public IntBuffer put(int[] src, int offset, int length) {

        checkBounds(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();
        System.arraycopy(src, offset, hb, ix(pos), length);
        position(pos + length);
        return this;



    }

    public IntBuffer put(IntBuffer src) {

            super.put(src);
        return this;



    }

    public IntBuffer compact() {

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

