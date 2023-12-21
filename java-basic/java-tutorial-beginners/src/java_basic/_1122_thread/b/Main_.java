package java_basic._1122_thread.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/22 15:18
 */

public class Main_ {

    public static void main(String[] args) {

        T3 t3 = new T3();
        Thread thread = new Thread();



    }
}


class T1 implements Runnable {
    @Override
    public void run() {
        System.out.println("t1");
    }
}

class T2 implements Runnable {

    @Override
    public void run() {
        System.out.println("t2");
    }
}

class T3 extends Thread {
    @Override
    public void run() {
        System.out.println("t3");
    }
}
