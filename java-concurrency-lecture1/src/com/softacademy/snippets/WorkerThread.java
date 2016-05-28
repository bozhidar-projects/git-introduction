package com.softacademy.snippets;

public class WorkerThread extends Thread {
    private int id;

    public WorkerThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 30; i++) {
                System.out.println("Hello " + i + " from thread " + id);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Worker ended");
        }
    }
}
