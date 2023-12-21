package java_basic._1022_circul;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class Exercise_01 {
    public static void main(String[] args) {

        int i = 1;
        int count = 0;
        int sum = 0;
        for(; i <= 100; i++){
            if(i%9 == 0){
                System.out.println(i);
                 // <code></code>System.out.printf("");
                sum+=i;
                count++;
            }
        }
        System.out.println(count);
        System.out.println(sum);

        for (int j = 0, k = 5; j <= 5; j++, k--) {
            System.out.println( j + "+" + k + "=" + (j+k));
        }


    }
}
