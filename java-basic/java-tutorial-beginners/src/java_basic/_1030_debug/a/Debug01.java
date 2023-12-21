package java_basic._1030_debug.a;

import java.util.Arrays;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/30 11:21
 */

public class Debug01 {

    public static void main(String[] args) {

        int arr[] = {1, -1, 10, -20, 100};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("结束.....");
    }




}
