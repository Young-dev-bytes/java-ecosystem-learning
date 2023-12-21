package java_basic._1122_thread.c;

public class Main_ {
    public static void main(String[] args) throws InterruptedException {

        T t = new T();

        t.setDaemon(true);
        t.start();

        for (int i = 1; i <= 5; i++) {
            Thread.sleep(1000);
            System.out.println("hi " + i);
        }
    }
}


class T extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello " + i);
        }
    }
}
