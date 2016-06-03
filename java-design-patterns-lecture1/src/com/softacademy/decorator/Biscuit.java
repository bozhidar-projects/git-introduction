package com.softacademy.decorator;

public class Biscuit extends CoffeeDecoration {

    public Biscuit(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getPrice() {
        return coffee.getPrice() + 0.75;
    }

}
