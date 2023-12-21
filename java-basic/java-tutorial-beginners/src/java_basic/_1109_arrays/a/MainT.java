package java_basic._1109_arrays.a;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/9 13:34
 */

public class MainT {
    public static void main(String[] args) {

        /*
        Integer[] arrInt = {9, -4, -3, 4, 2, 5, -1, 7};

        Integer[] integers = Arrays.copyOf(arrInt, 10);
        System.out.println(Arrays.toString(integers));
        Arrays.fill(integers, 99);
        System.out.println(Arrays.toString(integers));*/

        List lists = Arrays.asList(9, -4, -3, 4, 2, 5, -1, 7);
        System.out.println("asList=" + lists);
        System.out.println(lists.getClass());



     }

    private static void extracted() {

        Integer[] arrInt = {9, -4, -3, 4, 2, 5, -1, 7};
        int n = arrInt.length;
        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {
                if (arrInt[j] < arrInt[j + 1]) {
                    int temp = arrInt[j];
                    arrInt[j] = arrInt[j + 1];
                    arrInt[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arrInt));
        Arrays.sort(arrInt, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
    }
}
