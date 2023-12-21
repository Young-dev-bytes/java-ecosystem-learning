/*
 * Copyright (c) 2011, 2012, Oracle and/or its affiliates. All rights reserved.
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

package sun.java2d;

import java.awt.image.DataBuffer;
import java.nio.*;

public final class DataBufferNIOInt extends DataBuffer {

    /** The default data bank. */
    IntBuffer data;

    /** All data banks */
    IntBuffer bankdata[];

    /**
     * Constructs an integer-based {@code DataBuffer} with a single bank
     * and the specified size.
     *
     * @param size The size of the {@code DataBuffer}.
     */
    public DataBufferNIOInt(int size) {
        super(TYPE_INT,size);
        //+++gdb how to get sizeof(int) in java? Using 4 for now.
        data = getBufferOfSize(size * 4).asIntBuffer();
        bankdata = new IntBuffer[1];
        bankdata[0] = data;
    }

    /**
     * Returns the default (first) IntBuffer in {@code DataBuffer}.
     *
     * @return The first IntBuffer.
     */
    public IntBuffer getBuffer() {
        return data;
    }

    /**
     * Returns the Buffer for the specified bank.
     *
     * @param bank The bank whose Buffer you want to get.
     * @return The Buffer for the specified bank.
     */
    public IntBuffer getBuffer(int bank) {
        return bankdata[bank];
    }

    /**
     * Returns the default (first) int data array in {@code DataBuffer}.
     *
     * @return The first integer data array.
     */
    public int[] getData() {
        return data.array();
    }

    /**
     * Returns the data array for the specified bank.
     *
     * @param bank The bank whose data array you want to get.
     * @return The data array for the specified bank.
     */
    public int[] getData(int bank) {
        return bankdata[bank].array();
    }

    /**
     * Returns the data arrays for all banks.
     * @return All of the data arrays.
     */
    public int[][] getBankData() {
        // Unsupported.
        return null;
    }

    /**
     * Returns the requested data array element from the first (default) bank.
     *
     * @param i The data array element you want to get.
     * @return The requested data array element as an integer.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int i) {
        return data.get(i+offset);
    }

    /**
     * Returns the requested data array element from the specified bank.
     *
     * @param bank The bank from which you want to get a data array element.
     * @param i The data array element you want to get.
     * @return The requested data array element as an integer.
     * @see #setElem(int, int)
     * @see #setElem(int, int, int)
     */
    public int getElem(int bank, int i) {
        return bankdata[bank].get(i+offsets[bank]);
    }

    /**
     * Sets the requested data array element in the first (default) bank
     * to the specified value.
     *
     * @param i The data array element you want to set.
     * @param val The integer value to which you want to set the data array element.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int i, int val) {
        data.put(i+offset, val);
    }

    /**
     * Sets the requested data array element in the specified bank
     * to the integer value {@code i}.
     * @param bank The bank in which you want to set the data array element.
     * @param i The data array element you want to set.
     * @param val The integer value to which you want to set the specified data array element.
     * @see #getElem(int)
     * @see #getElem(int, int)
     */
    public void setElem(int bank, int i, int val) {
        bankdata[bank].put(i+offsets[bank], val);
    }

    ByteBuffer getBufferOfSize(int size)
    {
        ByteBuffer buffer = ByteBuffer.allocateDirect(size);
        buffer.order(ByteOrder.nativeOrder());
        return buffer;
    }
}
