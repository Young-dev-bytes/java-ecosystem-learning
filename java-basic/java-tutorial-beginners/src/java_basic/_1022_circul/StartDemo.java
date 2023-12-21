package java_basic._1022_circul;


/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class StartDemo {
    public static void main(String[] args) {


        int totalLevel = 20;
        for (int i = 1; i <= totalLevel; i++) {

            for (int k = 1; k <= totalLevel - i; k++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++){

                if(j == 1 || j == 2 * i - 1 || i == totalLevel){
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
