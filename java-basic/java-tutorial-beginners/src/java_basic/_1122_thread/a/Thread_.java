package java_basic._1122_thread.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/22 11:08
 */

public class Thread_ {
    public static void main(String[] args) {

        Cat cat = new Cat();
        cat.run();
        // cat.start();

        System.out.println("main : " + Thread.currentThread().getName());

        for (int i = 0; i < 40; i++) {
            System.out.println("main - " + (i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Cat extends Thread {

    int times;

    @Override
    public void run() {

        while (true) {
            System.out.println("hello - " + (++times) + " 线程名称 - " + Thread.currentThread().getName());

            if(times == 40) {
                break;

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
