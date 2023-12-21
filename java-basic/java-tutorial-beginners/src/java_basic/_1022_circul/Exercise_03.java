package java_basic._1022_circul;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class Exercise_03 {
    public static void main(String[] args) {


        int i = 1;
        while (i <= 4){
            i++;
            if(i == 2){
                continue;
            }

            System.out.println("i=" + i);
        }
    }
}
