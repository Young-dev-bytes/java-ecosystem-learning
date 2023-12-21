package java_basic._1109_collection.arrayList.d;

import java.util.Iterator;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/23 11:09
 */

public class DynamicArray<E> implements Iterable<E>{

    private int DEFAULT_CAPACITY = 8;
    private Object[] elementData;
    private int size;



    public void add(int index, E e) {
        rangeCheckForAdd(index);
        checkAndGrow();
        System.arraycopy(elementData, index,
                elementData, index + 1, size - index);
        elementData[index] = e;
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public Object get(int index) {
        rangeCheckForAdd(index);
        return elementData[index];
    }

    /*public boolean remove(int index) {


        return true;
    }*/


    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new RuntimeException("illegal index value: [" + index + "]");
        }
    }

    private void checkAndGrow() {

        if (size == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else if (size == DEFAULT_CAPACITY) {
            DEFAULT_CAPACITY += DEFAULT_CAPACITY >> 1;
            Object[] newElementData = new Object[DEFAULT_CAPACITY];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
