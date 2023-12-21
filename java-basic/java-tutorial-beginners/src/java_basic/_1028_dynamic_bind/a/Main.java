package java_basic._1028_dynamic_bind.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 15:37
 */

public class Main {
    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.sum());//40 -> 30
        System.out.println(a.sum1());//30 -> 20
    }
}
