package com.softacademy.designpatterns.adapter;

public class SamsungCoolPlayer implements MediaPlayer {

    @Override
    public void playMusic() {
        System.out.println("Samsung Cool Player");
    }

}
