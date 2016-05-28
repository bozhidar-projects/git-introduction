package com.snippets.synchronization;

public class Emil extends Thread {
    private RestingRooms restingRooms;

    public Emil(RestingRooms restingRooms) {
        this.restingRooms = restingRooms;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Emil is writing line of code: " + i);
                Thread.sleep(1000);
            }

            System.out.println("Emil goes to the massage chamber");
            restingRooms.goToMassageChamber("Emil");
        } catch (InterruptedException e) {
            System.out.println("Emil is going back to home");
        }
    }
}
