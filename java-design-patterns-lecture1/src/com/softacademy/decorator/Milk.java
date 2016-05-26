package com.softacademy.decorator;

public class Milk extends CoffeeDecoration {

    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getPrice() {
        return coffee.getPrice() + 0.20;
    }

}
