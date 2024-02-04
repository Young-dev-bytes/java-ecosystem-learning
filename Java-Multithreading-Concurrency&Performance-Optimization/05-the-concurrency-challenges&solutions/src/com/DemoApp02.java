package com;

public class DemoApp02 {
    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementingThread.start();
        decrementingThread.start();

        incrementingThread.join();
        decrementingThread.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items.");

    }


    public static class DecrementingThread extends Thread {

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
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
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
                System.out.println("increment: " + inventoryCounter.getItems());
            }
        }
    }

    private static class InventoryCounter {

        Object lockingObject = new Object();
        private int items = 0;

        public void increment() {
            synchronized (this.lockingObject) {
                items++;
            }
        }

        public void decrement() {
            synchronized (this.lockingObject) {
                items--;
            }
        }

        public int getItems() {
            synchronized (this.lockingObject) {
                return items;
            }
        }

    }
}

class Dog {

}
