package java_basic._1122_thread.d;

import java.util.Scanner;

public class Main_ {

    public static void main(String[] args) {

        A a = new A();
        B b = new B(a);
        new Thread(a).start();
        new Thread(b).start();
        // a.start();
        // b.start();


    }
}

class A extends Thread {

    private boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            System.out.println((int) (Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}

class B extends Thread {

    private A a;
    private Scanner scanner = new Scanner(System.in);

    public B(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("请输入指令（Q）表示退出：");
            char key = scanner.next().toUpperCase().charAt(0);
            System.out.println(key);
            if (key == 'Q') {
                a.setLoop(false);
                System.out.println("b线程退出.");
                break;
            }
        }
    }
}
