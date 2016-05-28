package com.snippets.synchronization;

public class Ventsi extends Thread {
    private RestingRooms restingRooms;

    public Ventsi(RestingRooms restingRooms) {
        this.restingRooms = restingRooms;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("Ventsi is writing line of code " + i);
                Thread.sleep(1000);
            }
            System.out.println("Ventsi goes to massage chamber");
            restingRooms.goToMassageChamber("Ventsi");
        } catch (InterruptedException e) {
            System.out.println("Ventsi goes back home");
        }
        System.out.println("Ventsi goes back home");
    }
}
