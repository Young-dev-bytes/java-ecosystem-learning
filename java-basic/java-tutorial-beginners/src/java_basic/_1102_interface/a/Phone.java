package java_basic._1102_interface.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 10:21
 */

public class Phone implements UsbInterface {

    private static final int n1 = 20;

    @Override
    public void start() {
        System.out.println("手机开始工作");
    }

    @Override
    public void stop() {
        System.out.println("手机停止工作");
    }

    @Override
    public void m2() {
        UsbInterface.super.m2();
    }

    public void call() {
        System.out.println("call...");
    }

}
