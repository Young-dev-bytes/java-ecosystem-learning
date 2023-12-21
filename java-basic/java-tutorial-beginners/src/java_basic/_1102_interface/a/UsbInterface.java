package java_basic._1102_interface.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 10:17
 */

public interface UsbInterface {

    int n1 = 10;

    void start();

    public abstract void stop();

    public static void m1() {
        System.out.println("m1");
        System.out.println(n1);
        // n1 = 20;
    }

    default public void m2() {
        System.out.println("m2");
    }
}
