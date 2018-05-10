package com.github.thanglequoc.FruitVendingMachine.functions;

import java.util.Comparator;

import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;

@FunctionalInterface
public interface FruitComparator extends Comparator<Fruit> {
    public int compare(Fruit a, Fruit b);
}
