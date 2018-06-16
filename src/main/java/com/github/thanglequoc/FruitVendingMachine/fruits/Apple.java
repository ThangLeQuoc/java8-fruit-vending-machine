package com.github.thanglequoc.FruitVendingMachine.fruits;

import com.github.thanglequoc.FruitVendingMachine.fruits.common.Color;

public class Apple extends Fruit {
    private static final String NAME = "Apple";

    public Apple() {
        super(NAME);
        this.setColor(Color.RED);
        setPrice(2500);
    }
}
