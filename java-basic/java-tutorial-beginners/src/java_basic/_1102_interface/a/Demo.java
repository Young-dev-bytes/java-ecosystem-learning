package java_basic._1102_interface.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 10:23
 */

public class Demo {
    public static void main(String[] args) {

        Computer computer = new Computer();
        Phone phone = new Phone();
        Camera camera = new Camera();
        // computer.work(phone);
        // computer.work(camera);

        UsbInterface[] usbArr = new UsbInterface[]{phone, camera};
        computer.work(usbArr);

    }
}
