package com.github.thanglequoc.FruitVendingMachine.fruits;

import com.github.thanglequoc.FruitVendingMachine.fruits.common.Color;

public class Banana extends Fruit {
    private static final String NAME = "Banana";

    public Banana() {
        super(NAME);
        this.setColor(Color.YELLOW);
    }

}
