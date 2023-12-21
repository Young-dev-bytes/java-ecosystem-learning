package java_basic._1109_arrays.a;

import java.util.Arrays;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/9 09:47
 */

public class ArrayT {

    public static void main(String[] args) {

        Integer[] arrInt = {9, -4, -3, 4, 2, 5, -1, 7};

        sort(arrInt, new ComparableT() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer in1 = (Integer) o1;
                Integer in2 = (Integer) o2;
                return in2 - in1;
            }
        });
    }

    private static void sort(Integer[] arrInt, ComparableT comparableT) {

        int n = arrInt.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparableT.compare(arrInt[j], arrInt[j + 1]) > 0) {
                    int temp = arrInt[j];
                    arrInt[j] = arrInt[j + 1];
                    arrInt[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arrInt));

        /*
        int n = arrInt.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrInt[j] > arrInt[j + 1]) {
                    int temp = arrInt[j];
                    arrInt[j] = arrInt[j + 1];
                    arrInt[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arrInt));*/
    }
}
