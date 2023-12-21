package java_basic._1021_array;

import java.util.Arrays;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/21 11:38
 */
public class _02_ArrayDemo {
    public static void main(String[] args) {

        /*
        int n1 = 10;
        int n2 = n1;
        n2 = 80;
        System.out.println("n1=" + n1);
        System.out.println("n2=" + n2);

        int[] arr1 = {1,2,3};
        int[] arr2 = arr1;
        arr1[2] = 90;
        System.out.println(Arrays.toString(arr2));
         */
        int[] arr1 = {10,20,30};
        int[] arr2 = new int[3];
        arr2[0] = arr1[0];
        arr2[1] = arr1[1];
        arr2[2] = arr1[2];
        arr1[0] = 800;
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr1));



    }
}
