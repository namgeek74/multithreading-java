package com.dsa;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code that will run a new thread
                System.out.println("We are now in thread: " + Thread.currentThread().getName());

            }
        });

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");

        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

    }
}
