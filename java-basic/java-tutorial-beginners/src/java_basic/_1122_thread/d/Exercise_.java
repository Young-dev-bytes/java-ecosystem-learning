package java_basic._1122_thread.d;

public class Exercise_ {
    public static void main(String[] args) {

        T t = new T();
        Thread t1 = new Thread(t);
        t1.setName("t1");
        Thread t2 = new Thread(t);
        t2.setName("t2");

        t1.start();
        t2.start();


    }
}


class T implements Runnable {

    private int money = 10000;

    @Override
    public void run() {

        while (true) {

            synchronized (this) {
                if (money < 1000) {
                    System.out.println("余额不足");
                    break;
                }

                money -= 1000;
                System.out.println(Thread.currentThread().getName() + " 取出了1000， 当前余额为=" + money);

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
