package com.github.thanglequoc.java8.common.model;

public class Apple extends Fruit {
    private static final String NAME = "Apple";

    public Apple() {
        super(NAME);
        this.setColor(Color.RED);
    }
}
