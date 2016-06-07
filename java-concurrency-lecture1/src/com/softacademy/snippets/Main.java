package com.softacademy.snippets;

public class Main {
    public static void main(String[] args) {
        WorkerThread worker1 = new WorkerThread(1);
        worker1.setDaemon(true);

        ManagerThread managerThread = new ManagerThread(worker1);
        managerThread.setDaemon(true);

        Secretary secretary = new Secretary();
        Thread secretaryThread = new Thread(secretary);

        worker1.start();
        secretaryThread.start();
        managerThread.start();

        try {
            worker1.join();
            managerThread.join();
            secretaryThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Hello, Threads");
    }
}
