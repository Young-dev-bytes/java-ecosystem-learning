package java_basic._1022_circul;


/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class MulForDemoExercise_01 {
    public static void main(String[] args) {


        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + (j * i) + "\t");
            }
            System.out.println();
        }



    }
}
