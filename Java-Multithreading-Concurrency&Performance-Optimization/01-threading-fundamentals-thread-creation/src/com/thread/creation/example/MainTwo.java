package com.thread.creation.example;

public class MainTwo {

    public static void main(String[] args)  {

        Thread thread = new Thread(()->{
            System.out.println("We are now in thread: " + Thread.currentThread().getName());
            throw new RuntimeException("International Exception");
        });

        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler((t, e) -> System.out.println("A critical error happened in thread: " + t.getName() + ", the error is " + e.getMessage()));

        thread.start();


    }
}


