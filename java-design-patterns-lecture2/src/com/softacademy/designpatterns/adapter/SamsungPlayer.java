package com.softacademy.designpatterns.adapter;

public class SamsungPlayer implements MediaPlayer {

    @Override
    public void playMusic() {
        System.out.println("Plays the samsung player");
    }

}
