package com.github.thanglequoc.FruitVendingMachine.functions;


import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;
import com.github.thanglequoc.FruitVendingMachine.fruits.common.Color;
import com.github.thanglequoc.FruitVendingMachine.fruits.common.Size;

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
