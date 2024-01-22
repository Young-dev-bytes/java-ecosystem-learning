package com.thread.creation.example.threadproxy;

public class ThreadProxy implements Runnable{

    private Runnable target;

    @Override
    public void run() {
        if(target != null) target.run();
    }

    public ThreadProxy() {
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();
    }

    public void start0(){
        run();
    }
}
