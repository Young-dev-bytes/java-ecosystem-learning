package java_basic._1022_circul;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class WhileDemo {
    public static void main(String[] args) {

        int i = 1;
        while(i <= 100){
            if(i % 3 == 0){
            System.out.println(i);
            }
            i++;
        }
    }
}
