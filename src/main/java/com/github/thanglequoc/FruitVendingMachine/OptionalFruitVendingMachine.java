package com.github.thanglequoc.FruitVendingMachine;

import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;

public class OptionalFruitVendingMachine {

    public static final int MAX_MACHINE_SLOTS = 10;

    private long balance;

    private Fruit[] fruits;

    public OptionalFruitVendingMachine() {
        fruits = new Fruit[MAX_MACHINE_SLOTS];
        balance = 0;
    }

    public void setFruit(int i, Fruit fruit) {
        if (i >= MAX_MACHINE_SLOTS)
            throw new IllegalArgumentException("Illegal Fruit Slot");
        fruits[i] = fruit;
    }

    public Fruit getFruit(int i) {
        if (i >= MAX_MACHINE_SLOTS)
            throw new IllegalArgumentException("Illegal Fruit Slot");
        Fruit selectedFruit = fruits[i];
        if (balance < selectedFruit.getPrice() || selectedFruit == null) {
            return null;
        }

        fruits[i] = null;
        balance -= selectedFruit.getPrice();
        return selectedFruit;
    }

    public Fruit[] getFruits() {
        return fruits;
    }

    public void addBalance(long balance) {
        this.balance += balance;
    }

}
