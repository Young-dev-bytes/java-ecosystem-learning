package com.thread.creation.example.threadproxy;

public class Demo {

    public static void main(String[] args) {
        // new ThreadYoung().start();
        new ThreadProxy(new RunnableYoung()).start();
    }
}
