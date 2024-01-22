package com.thread.creation.threadproxy;

public class ThreadYoung extends ThreadProxy{

    @Override
    public void run() {
        System.out.println("ThreadYoung...");
    }
}
