package com.thread.creation.example;

public class Main {

    public static void main(String[] args) {

        ThreadJack threadJack = new ThreadJack();
        threadJack.start();
        System.out.println("main - " + Thread.currentThread().getName());


        RunnableJack runnableJack = new RunnableJack();
        new Thread(runnableJack).start();
        System.out.println("main2 - " + Thread.currentThread().getName());

        new Thread(() -> System.out.println("runnable")).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        });





    }
}

class ThreadJack extends Thread {
    @Override
    public void run() {
        System.out.println("ThreadJack execute..." + Thread.currentThread().getName());
    }
}

class RunnableJack implements Runnable{
    @Override
    public void run() {
        System.out.println("RunnableJack execute..." + Thread.currentThread().getName());

    }
}
