package java_basic._1109_collection.arrayList.b;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/10 15:26
 */

public class Demo01 {

    public static void main(String[] args) {


        // 创建一个 ArrayList 实例
        MyArrayList<String> myList = new MyArrayList<>();

        // 添加元素到列表
        myList.add("Apple");
        myList.add("Banana");
        myList.add("Orange");

        // 调用继承自 AbstractList 的 size() 方法
        System.out.println("Size of the list: " + myList.size());

        // 调用继承自 AbstractList 的 get(int index) 方法
        System.out.println("Element at index 1: " + myList.get(1));

        // 调用继承自 AbstractList 的 remove(int index) 方法
        myList.remove(0);

        // 打印剩余的列表元素
        System.out.println("Remaining elements: " + myList);
    }


    // 自定义的 ArrayList 类继承自 AbstractList 类
    static class MyArrayList<E> extends AbstractList<E> {
        private final ArrayList<E> list = new ArrayList<>();

        @Override
        public E get(int index) {
            return list.get(index);
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public E remove(int index) {
            return list.remove(index);
        }

        @Override
        public void add(int index, E element) {
            list.add(index, element);
        }

        // 其他方法需要在这里实现
    }

}
