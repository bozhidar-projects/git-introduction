package com.softacademy.designpatterns.observer;

public class MyButtonObserver implements Observer {

    public void printMessage() {
        System.out.println("Button is clicked");
    }

    @Override
    public void onButtonClicked() {
        printMessage();
    }

}
