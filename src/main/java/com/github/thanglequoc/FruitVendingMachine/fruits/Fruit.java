package com.github.thanglequoc.FruitVendingMachine.fruits;

import com.github.thanglequoc.FruitVendingMachine.fruits.common.Color;
import com.github.thanglequoc.FruitVendingMachine.fruits.common.Size;

public abstract class Fruit {
    private String name;
    private int weight;
    private Color color;
    private int nutritionalScore;
    private boolean ripe;
    private Size size;
    private long price;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public int getNutritionalScore() {
        return nutritionalScore;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNutritionalScore(int nutritionalScore) {
        this.nutritionalScore = nutritionalScore;
    }

    public Fruit(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void setRipe(boolean ripe) {
        this.ripe = ripe;
    }

    public boolean isRipe() {
        return ripe;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }
    
    public long getPrice() {
        return price;
    }
    
    protected void setPrice(long price) {
        this.price = price;
    } 
}
