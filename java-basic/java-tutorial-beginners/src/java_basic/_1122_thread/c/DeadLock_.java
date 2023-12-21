package java_basic._1122_thread.c;

public class DeadLock_ {
    public static void main(String[] args) {

        DeadLock a = new DeadLock(true);
        a.setName("A");
        DeadLock b = new DeadLock(false);
        b.setName("B");

        a.start();
        b.start();


    }
}

class DeadLock extends Thread{

    static Object o1 = new Object();
    static Object o2 = new Object();

    boolean flag;

    public DeadLock(boolean flag) {
        this.flag = flag;

    }

    @Override
    public void run() {

        if(flag) {
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + " 进入1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " 进入2");
                }
            }

        }else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + " 进入3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " 进入4");
                }
            }
        }

    }
}
