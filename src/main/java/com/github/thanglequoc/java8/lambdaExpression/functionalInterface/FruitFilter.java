package com.github.thanglequoc.java8.lambdaExpression.functionalInterface;

import java.awt.Color;

import com.github.thanglequoc.java8.common.model.Fruit;
import com.github.thanglequoc.java8.common.model.Size;

@FunctionalInterface
public interface FruitFilter {
    public boolean match(Fruit fruit);

    static FruitFilter IS_RIPE() {
        return (fruit) -> fruit.isRipe();
    }

    static FruitFilter WITH_COLOR(Color color) {
        return (fruit) -> color.equals(fruit.getColor());
    }

    static FruitFilter WITH_SIZE(Size size) {
        return (fruit) -> size.equals(fruit.getSize());
    }

    static FruitFilter IS_NUTRIOUS() {
        return (fruit) -> fruit.getNutritionalScore() > 30;
    }

}
