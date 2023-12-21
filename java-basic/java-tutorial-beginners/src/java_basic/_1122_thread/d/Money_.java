package java_basic._1122_thread.d;

public class Money_ {
    public static void main(String[] args) {

        GetMoney getMoney = new GetMoney();
        Thread thread01 = new Thread(getMoney);
        Thread thread02 = new Thread(getMoney);

        thread01.setName("Mary");
        thread02.setName("Jack");

        thread01.start();
        thread02.start();

    }
}


class GetMoney extends Thread {

    private int count = 50;

    @Override
    public void run() {
        while (true) {
            if (count <= 0) {
                System.out.println("余额等于0或者小于0");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "取出1000，剩余：" + (count -= 1000));
        }
    }
}
