package com.softacademy.snippets;

public class RockLaunch {

    public static void main(String[] args) {

        for (int i = 10; i >= 1; i--) {
            System.out.println(i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Lift off !!!");
    }

}
