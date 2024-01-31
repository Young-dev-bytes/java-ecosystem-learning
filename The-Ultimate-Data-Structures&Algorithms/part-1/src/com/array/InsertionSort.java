package com.array;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {38, 65, 97, 76, 13, 27, 49};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void insertSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    // array[j+1]=tmp;
                    break;
                }
            }
            array[j + 1] = tmp;
        }

    }
}
