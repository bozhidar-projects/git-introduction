package com.softacademy.decorator;

public class Application {

    public static void main(String[] args) {
        Coffee coffee = new Java();
        CoffeeDecoration coffeeDecoration = new Milk(coffee);
        coffeeDecoration = new Milk(coffeeDecoration);
        coffeeDecoration = new Biscuit(coffeeDecoration);

        System.out.println(coffeeDecoration.getPrice());
    }

}
