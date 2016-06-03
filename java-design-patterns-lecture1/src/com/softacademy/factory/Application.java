package com.softacademy.factory;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);
        String carName = menu.getCarName();

        CarFactory carFactory = new CarFactory();
        Car car = carFactory.createCar(carName);

        car.drive();
    }

}
