package com.github.thanglequoc.java8.lambdaExpression.functionalInterface;

import com.github.thanglequoc.java8.common.model.Fruit;

@FunctionalInterface
public interface FruitComparator {
    public int compare(Fruit a, Fruit b);
}
