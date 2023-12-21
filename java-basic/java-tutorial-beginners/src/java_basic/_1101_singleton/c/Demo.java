package java_basic._1101_singleton.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 15:44
 */

public class Demo {
    public static void main(String[] args) {
        StaticBlockSingleton instance = StaticBlockSingleton.getInstance();
        StaticBlockSingleton instance1 = StaticBlockSingleton.getInstance();
        System.out.println(instance == instance1);

    }
}
