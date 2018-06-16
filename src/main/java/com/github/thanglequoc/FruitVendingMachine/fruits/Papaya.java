package com.github.thanglequoc.FruitVendingMachine.fruits;

import com.github.thanglequoc.FruitVendingMachine.fruits.common.Color;

public class Papaya extends Fruit {

    private static final String NAME = "Papaya";

    public Papaya() {
        super(NAME);
        this.setColor(Color.YELLOW);
        setPrice(8000);
    }

}
