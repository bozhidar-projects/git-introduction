package com.softacademy.designpatterns.observer;

import java.util.HashSet;
import java.util.Set;

public class Button {

    private Set<Observer> observers;

    public Button() {
        observers = new HashSet<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void click() {
        for (Observer observer : observers) {
            observer.onButtonClicked();
        }
    }

}
