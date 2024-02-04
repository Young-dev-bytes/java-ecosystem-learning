package com.thread.join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoApp01 {
    public static void main(String[] args) throws InterruptedException {

        List<Long> inputNums = Arrays.asList(23245488888L, 343L, 354L, 89L, 465L, 23L, 243L, 556L);
        List<FactorialThread> factorialThreads = new ArrayList<>();

        for (Long inputNum : inputNums) {
            factorialThreads.add(new FactorialThread(inputNum));
        }

        for (FactorialThread factorialThread : factorialThreads) {
            factorialThread.setDaemon(true);
            factorialThread.start();
        }

        for (FactorialThread factorialThread : factorialThreads) {
            // factorialThread.join();
            factorialThread.join(2000);
        }

        // Thread.sleep(2);

        for (int i = 0; i < inputNums.size(); i++) {
            FactorialThread factorialThread = factorialThreads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNums.get(i) + " is " + factorialThread.getResult() + "\n");
            } else {
                System.out.println("The calculation for " + inputNums.get(i) + " is still in progress \n");
                // factorialThread.interrupt();
            }
        }
    }

    public static class FactorialThread extends Thread {

        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;

            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
                /*if(Thread.currentThread().isInterrupted()) {
                    return BigInteger.ZERO;
                }*/
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}


