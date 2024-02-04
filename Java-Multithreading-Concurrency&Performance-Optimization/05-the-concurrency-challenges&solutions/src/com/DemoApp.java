package com;

public class DemoApp {
    public static void main(String[] args) throws InterruptedException {

        // InventoryCounter inventoryCounter = new InventoryCounter();
        // IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        // DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        IncrementingThread incrementingThread = new IncrementingThread(new InventoryCounter());
        DecrementingThread decrementingThread = new DecrementingThread(new InventoryCounter());


        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        // System.out.println("We currently have " + inventoryCounter.getItems() + " items.");

    }


    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                inventoryCounter.decrement();
                System.out.println("decrement: " + inventoryCounter.getItems());
            }
        }
    }

    public static class IncrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                inventoryCounter.increment();
                System.out.println("increment: " + inventoryCounter.getItems());
            }
        }
    }

    private static class InventoryCounter {


        private int items = 0;

        public synchronized void increment() {
            System.out.println(Thread.currentThread().getName());
            items++;
        }

        public synchronized void decrement() {
            System.out.println(Thread.currentThread().getName());

            items--;
        }

        public synchronized int getItems() {
            System.out.println(Thread.currentThread().getName());
            return items;
        }

    }
}
