package java_basic._1022_circul;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class Exercise_02 {
    public static void main(String[] args) {


        int sum = 0;
        int n = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
            if(sum > 20){
                n = i;
                break;
            }
        }
        System.out.println(n);
        System.out.println(sum);
    }
}
