package programming.FP08;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class FP08Threads {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread().getId() + ":" + i);

                }

            }

        };

        /*Runnable runnable2 = () -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getId() + ":" + i);

            }
        };*/

        Runnable runnable2 = () -> {
            // IntStream.range(0,10000).forEach(System.out::println);
            IntStream.range(0,10000).forEach(FP08Threads::getIntConsumer);
        };

        Thread thread = new Thread(runnable2);
        thread.start();

        Thread thread1 = new Thread(runnable2);
        thread1.start();

        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

    private static void getIntConsumer(int i) {
        System.out.println(Thread.currentThread().getId() + ":" + i);
    }
}
