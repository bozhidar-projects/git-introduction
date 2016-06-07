package com.softacademy.designpatterns.adapter;

public class SonyPlayer implements CDPlayer {

    @Override
    public void play() {
        System.out.println("Plays the Sony Player");
    }

}
