package java_basic._1102_interface.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 10:18
 */

public class Camera implements UsbInterface {

    @Override
    public void start() {
        System.out.println("相机开始工作");
    }

    @Override
    public void stop() {
        System.out.println("相机停止工作");
    }


    @Override
    public void m2() {
        UsbInterface.super.m2();
    }
}
