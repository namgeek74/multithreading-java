package com.dsa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Main {
    public static final int MAX_PASSWORD = 9999;
    public static int num;

    public static void main(String[] args) throws InterruptedException {
        // Random random = new Random();
        // Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
        // List<Thread> threads = new ArrayList<>();
        // threads.add(new AscendingHackerThread(vault));
        // threads.add(new DescendingHackerThread(vault));
        // threads.add(new PoliceThread());
        // for (Thread thread :
        //         threads) {
        //     thread.start();
        // }
        // DataRaceExample.main();
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(3);
        System.out.println("hash code: " + set.hashCode());
        System.out.println("Set: " + set);

        Map<String, String> map = new HashMap();
        map.put("nam", "Nam");
        map.put("nam1", "Nam 1");
        map.put("nam", "Nam 234");
        System.out.println("Map: " + map);
    }

    private static int sum(int a, int b) {
        // int num2;
        // System.out.println("Default local reference int: " + num2);
        int s = a + b;
        return s;
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_PASSWORD; i++) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(this.getName() + " guessed the password " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = MAX_PASSWORD; i >= 0; i--) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(this.getName() + " guessed the password " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
