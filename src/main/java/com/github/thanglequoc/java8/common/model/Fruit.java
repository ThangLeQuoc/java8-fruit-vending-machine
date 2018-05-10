package com.github.thanglequoc.java8.common.model;

public abstract class Fruit {
    private String name;
    private int weight;
    private String color;
    private int nutritionalScore;
    
    public String getName() {
	return name;
    }
    
    public abstract void setName(String name);
    
    
    public int getWeight() {
	return weight;
    }
    
    public abstract void setWeight(int weight);
    
    public abstract void setColor(String color);
    
    
    public String getColor() {
	return color;
    }
    
    public abstract void setNutritionalScore(int nutritionalScore);
    
    public int getNutritionalScore() {
	return nutritionalScore;
    }
}
