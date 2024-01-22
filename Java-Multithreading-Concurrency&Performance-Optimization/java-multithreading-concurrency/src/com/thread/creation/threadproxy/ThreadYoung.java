package com.thread.creation.example.threadproxy;

public class ThreadYoung extends ThreadProxy{

    @Override
    public void run() {
        System.out.println("ThreadYoung...");
    }
}
