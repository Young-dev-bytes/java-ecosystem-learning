package com.thread;

public class Main01 {
    public static void main(String[] args) {

        Thread thread = new Thread(new BlockingTask());
        thread.start();
        thread.interrupt();

    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            // do things
            try {
                Thread.sleep(50000);
            } catch (InterruptedException interruptedException) {
                // interruptedException.printStackTrace();
                System.out.println("existing blocking thread...");
            }
        }
    }
}
