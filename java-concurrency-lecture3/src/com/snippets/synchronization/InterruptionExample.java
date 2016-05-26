package com.snippets.synchronization;

public class InterruptionExample {

    public static void main(String[] args) {
        RestingRooms restingRooms = new RestingRooms();
        Emil emil = new Emil(restingRooms);
        Denitsa denitsa = new Denitsa(emil);

        Ventsi ventsi = new Ventsi(restingRooms);

        emil.start();
        denitsa.start();
        ventsi.start();

        try {
            emil.join();
            denitsa.join();
            ventsi.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
