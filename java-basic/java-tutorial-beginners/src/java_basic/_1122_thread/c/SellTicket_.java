package java_basic._1122_thread.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/22 15:47
 */

public class SellTicket_ {
    public static void main(String[] args) {

        SellTicket sellTicket = new SellTicket();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
    }
}


class SellTicket extends Thread {

    // 三个线程共享三个对象，所以这里要设置静态的，全局的
    public int ticketNum = 1000;
    private boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            sell();
        }
    }

    public synchronized void sell() {
        if (ticketNum <= 0) {
            System.out.println("售票结束...");
            loop = false;
            return;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票 " + " 剩余票数=" + (--ticketNum));
    }
}

