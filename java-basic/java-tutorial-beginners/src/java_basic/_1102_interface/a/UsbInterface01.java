package java_basic._1102_interface.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 10:17
 */

public interface UsbInterface01 {

    public static final int n1 = 10;

    void start();

    void stop();

    void m3();

    public  static  void m1() {
        System.out.println("m1");
    }

    default public void m2() {
        System.out.println("m2");
    }
}
