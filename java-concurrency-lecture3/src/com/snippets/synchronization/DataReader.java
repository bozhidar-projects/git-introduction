package com.snippets.synchronization;

import java.util.Random;

public class DataReader extends Thread {
    private SharedData sharedData;
    private Random random;

    public DataReader(SharedData sharedData) {
        this.sharedData = sharedData;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            int randomInt = random.nextInt(15);
            int data = sharedData.readData(randomInt);
            System.out.println(data);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
