package java_basic._1122_thread.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/22 14:09
 */

public class Run_ {

    public static void main(String[] args) {

        CatT catT = new CatT();
        Thread thread = new Thread(catT);

        thread.start();

        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("main:" + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();

    }
}

class Animal {
}

// 线程类
class Tiger extends Animal implements Runnable {

    @Override
    public void run() {
        System.out.println("tiger: " + Thread.currentThread().getName());
        System.out.println("老虎嗷嗷叫...");
    }
}

// 线程代理类，模拟了一个极简的Thread类
class ThreadProxy implements Runnable {

    private Runnable target;

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    /*public ThreadProxy() {
        this.target = new CatT();
    }*/


    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();
    }

    public void start0() {
        run();
    }
}


@SuppressWarnings("all")
class CatT implements Runnable {

    private int times;

    @Override
    public void run() {
        while (true) {

            System.out.println("hello - " + (++times) + " 线程名称 - " + Thread.currentThread().getName());
            if (times == 40) {
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
