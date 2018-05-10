package com.github.thanglequoc.java8.lambdaExpression.functionalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.github.thanglequoc.java8.common.model.Fruit;

public class FruitVendingMachine {

    List<Fruit> fruits;

    public FruitVendingMachine() {
        fruits = new ArrayList<>();
    }

    public List<Fruit> filterFruit(FruitFilter filter) {
        if (CollectionUtils.isEmpty(fruits) || filter == null) {
            return new ArrayList<>();
        }
        return fruits.stream().filter(fruit -> filter.match(fruit)).collect(Collectors.toList());
    }

    public void showFruits() {
        System.out.println("Quantity: " + fruits.size());
        if (CollectionUtils.isNotEmpty(fruits)) {
            fruits.stream().forEach(fruit -> {
                System.out.println("Name: " + fruit.getName());
            });
        }
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
}
