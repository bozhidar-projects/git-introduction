package com.softacademy.designpatterns.singleton;

public class Petyr {

    private static Petyr instance;

    private Petyr() {

    }

    public static Petyr newInstance() {
        if (instance == null) {
            instance = new Petyr();
        }
        return instance;
    }
}
