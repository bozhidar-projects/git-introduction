package com.snippets.synchronization;

import java.util.Random;

public class Writer extends Thread {
    private SharedData sharedData;
    private Random random;

    public Writer(SharedData sharedData) {
        this.sharedData = sharedData;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            int newData = random.nextInt(1000);
            int dataIndex = random.nextInt(15);

            sharedData.writeData(dataIndex, newData);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
