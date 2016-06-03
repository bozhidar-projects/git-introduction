package com.softacademy.factory;

public class CarFactory {

    public Car createCar(String carName) {
        switch (carName) {
        case CarNames.BMW:
            return new BMW();
        case CarNames.VOLVO:
            return new Volvo();
        case CarNames.FERRARI:
            return new Ferrari();
        }

        throw new IllegalArgumentException("Unknown Car");
    }

}
