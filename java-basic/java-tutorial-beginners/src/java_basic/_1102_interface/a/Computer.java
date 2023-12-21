package java_basic._1102_interface.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 10:22
 */

public class Computer {

    void work(UsbInterface[] usbArr) {
        for (int i = 0; i < usbArr.length; i++) {
            usbArr[i].start();
            usbArr[i].stop();
            if(usbArr[i] instanceof Phone) {
                ((Phone)usbArr[i]).call();
            }
        }
    }
}
