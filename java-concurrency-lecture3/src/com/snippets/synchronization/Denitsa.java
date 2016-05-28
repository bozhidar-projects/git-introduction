package com.snippets.synchronization;

import java.util.Random;

public class Denitsa extends Thread {

    private Emil emil;
    private Random random;

    public Denitsa(Emil emil) {
        this.emil = emil;
        random = new Random();
    }

    @Override
    public void run() {
        System.out.println("Denitsa wakes up");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Calling Emil back from work");
        emil.interrupt();
    }
}
