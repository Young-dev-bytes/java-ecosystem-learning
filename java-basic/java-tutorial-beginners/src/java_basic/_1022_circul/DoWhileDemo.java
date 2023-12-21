package java_basic._1022_circul;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/22 14:18
 */

public class DoWhileDemo {
    public static void main(String[] args) {

        int i = 1;
        int count = 0;
        do{

           if(i % 5 == 0 && i % 3 != 0){
               System.out.println(i);
               count++;
           }
           i++;

        }while (i <= 200);
        System.out.println(count);
    }
}
