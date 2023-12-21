package java_basic._1021_array;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/21 11:38
 */
public class _01_ArrayDemo {
    public static void main(String[] args) {
        char[] chars = new char[26];



        /**
         for (int i = 0; i < chars.length; i++) {
         int temp = (int)'A' + i;
         System.out.println(temp + "-" + (char)temp);
         }
         */

        for (int i = 0; i < chars.length; i++) {
            char temp = (char)('A' + i);
            System.out.println((int)temp + "-" + temp);
        }
        /**
         * int [] ints = new int{4,-1,9,10,23}
         */
        int [] ints = {4,-1,9,10,23};
        int maxValue = ints[0];
        int maxIndex = 0;
        for (int i = 0; i < ints.length; i++) {
            // maxValue ; ints[i + 1]
            System.out.println(i);
            if(maxValue <= ints[i]){
                maxValue = ints[i];
                maxIndex = i;
            }
        }
        System.out.println("maxValue - " + maxValue);
        System.out.println("maxIndex - " + maxIndex);

    }
}
