package com.github.thanglequoc.FruitVendingMachine;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;

public class OptionalFruitVendingMachine {

    public static final int MAX_MACHINE_SLOTS = 10;

    private long balance;

    private Optional<Fruit>[] fruits;

    public OptionalFruitVendingMachine() {
        fruits = new Optional[MAX_MACHINE_SLOTS];
        for (int i = 0; i < fruits.length; i++) {
            fruits[i] = Optional.empty();
        }
        balance = 0;
    }

    public void setFruit(int i, Fruit fruit) {
        if (i >= MAX_MACHINE_SLOTS)
            throw new IllegalArgumentException("Illegal Fruit Slot");
        fruits[i] = Optional.of(fruit);
    }

    public Fruit buyFruit(int i) {
        if (i >= MAX_MACHINE_SLOTS)
            throw new IllegalArgumentException("Illegal Fruit Slot");
        
        Optional<Fruit> selectedFruit = fruits[i];
        Fruit fruit = selectedFruit.orElseThrow(FruitOutOfStockException::new);
        if (balance < fruit.getPrice()) {
            throw new NotEnoughBalanceException();
        }
        balance -= fruit.getPrice();
        fruits[i] = Optional.empty();
        return fruit;
    }
    

    public void addBalance(long balance) {
        this.balance += balance;
    }
    
    public long getCurrentBalance() {
        return this.balance;
    }

}
