package com.github.thanglequoc.FruitVendingMachine.fruits;

import com.github.thanglequoc.FruitVendingMachine.fruits.common.Color;

public class Orange extends Fruit {
    private static final String NAME = "Orange";

    public Orange() {
        super(NAME);
        this.setColor(Color.ORANGE);
        setPrice(7000);
    }

}
