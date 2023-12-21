package java_basic._1122_thread.c;

public class MainState {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();

        System.out.println(test.getName() + " 状态 " + test.getState());
        test.start();
        test.setName("test");

        while (Thread.State.TERMINATED != test.getState()) {
            System.out.println(test.getName() + " 状态 " + test.getState());
            Thread.sleep(1000);
        }

        System.out.println(test.getName() + " 状态 " + test.getState());

    }
}

class Test extends Thread {

    @Override
    public void run() {

        for (int i = 0 ; ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello " + i);

            if (i == 20) {
                break;
            }
        }
    }
}
