package com.softacademy.snippets;

public class ManagerThread extends Thread {

    private WorkerThread worker;

    public ManagerThread(WorkerThread worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("I'm the boss");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        worker.interrupt();
    }
}
