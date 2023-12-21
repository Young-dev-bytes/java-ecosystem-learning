package java_basic._1101_singleton.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 10:39
 */

public class Test {
    public static void main(String[] args) {

        // Singleton01 singleton01 = new Singleton01();
        Singleton01 instance = Singleton01.getInstance();
        // Singleton01 instance1 = Singleton01.getInstance();

        System.out.println(instance);
        // System.out.println(instance1.hashCode());



    }
}
