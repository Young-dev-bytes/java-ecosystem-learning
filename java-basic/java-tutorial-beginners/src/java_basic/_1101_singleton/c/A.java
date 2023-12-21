package java_basic._1101_singleton.c;


/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 16:57
 */

public class A  {

    public  static  int value = 30;

    static {
        System.out.println("static");
    }

    public static void main(String[] args) {
        System.out.println(A.value);
    }

}
