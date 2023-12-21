package java_basic._1122_thread.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/22 15:47
 */

public class SellTicket_ {
    public static void main(String[] args) {
        /*
        SellTicket sellTicket1 = new SellTicket();
        SellTicket sellTicket2 = new SellTicket();
        SellTicket sellTicket3 = new SellTicket();

        // sellTicket1.start();
        // sellTicket2.start();
        // sellTicket3.start();

        Thread thread1 = new Thread(sellTicket1);
        Thread thread2 = new Thread(sellTicket2);
        Thread thread3 = new Thread(sellTicket3);

        thread1.start();
        thread2.start();
        thread3.start();
        */

        SellTicket1 sellTicket1 = new SellTicket1();



        new Thread(sellTicket1).start();
        new Thread(sellTicket1).start();
        new Thread(sellTicket1).start();



    }
}


class SellTicket extends Thread {

    // 三个线程共享三个对象，所以这里要设置静态的，全局的
    public static int ticketNum = 10;

    @Override
    public void run() {

        while (true) {

            if (ticketNum <= 0) {
                System.out.println("售票结束...");
                break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票 " + " 剩余票数=" + (--ticketNum));
        }
    }

}

class SellTicket1 implements Runnable {

    // 三个线程共享一个对象，这个可以不用这个静态的
    public int ticketNum = 10;

    @Override
    public void run() {

        while (true) {

            if (ticketNum <= 0) {
                System.out.println("售票结束...");
                break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票 " + " 剩余票数=" + (--ticketNum));
        }
    }

}
