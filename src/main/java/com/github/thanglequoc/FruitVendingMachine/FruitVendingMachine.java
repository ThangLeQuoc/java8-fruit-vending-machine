package com.github.thanglequoc.FruitVendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.github.thanglequoc.FoodVendingMachine.money.Banknote;
import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;
import com.github.thanglequoc.FruitVendingMachine.functions.FruitFilter;

public class FruitVendingMachine {

    private List<Fruit> fruits;
    
    private long balance;

    public FruitVendingMachine() {
        fruits = new ArrayList<>();
    }

    public List<Fruit> filterFruit(FruitFilter filter) {
        if (CollectionUtils.isEmpty(fruits) || filter == null) {
            return new ArrayList<>();
        }
        return fruits.stream().filter(fruit -> filter.match(fruit)).collect(Collectors.toList());
    }

    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }
    
    public void addBalance(Banknote bankNote) {
        this.balance += bankNote.getAmount();
    }
}
