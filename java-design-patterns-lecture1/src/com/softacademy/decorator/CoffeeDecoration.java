package com.softacademy.decorator;

public abstract class CoffeeDecoration implements Coffee {
    protected Coffee coffee;

    public CoffeeDecoration(Coffee coffee) {
        this.coffee = coffee;
    }

    public Coffee getDecoratedObject() {
        return coffee;
    }
}
