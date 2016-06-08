package com.softacademy.designpatterns.singleton;

public class SingletonExample {

    public static void main(String[] args) {
        Petyr petyr = Petyr.newInstance();
        Petyr petyr2 = Petyr.newInstance();

        System.out.println(petyr == petyr2);
    }

}
