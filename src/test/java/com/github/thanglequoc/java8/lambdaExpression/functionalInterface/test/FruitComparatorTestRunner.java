package com.github.thanglequoc.java8.lambdaExpression.functionalInterface.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import com.github.thanglequoc.java8.common.model.Apple;
import com.github.thanglequoc.java8.common.model.Color;
import com.github.thanglequoc.java8.common.model.Durian;
import com.github.thanglequoc.java8.common.model.Fruit;
import com.github.thanglequoc.java8.lambdaExpression.functionalInterface.FruitComparator;
import com.github.thanglequoc.java8.lambdaExpression.functionalInterface.FruitFilter;

public class FruitComparatorTestRunner {

    /*
     * Create functional interface implementation with anonymous static inner type
     */
    @Test
    public void createFruitComparatorWithInnerClass() {
	FruitComparator weightComparator = new FruitComparator() {
	    @Override
	    public int compare(Fruit a, Fruit b) {
		return a.getWeight() - b.getWeight();
	    }
	};
    }

    /*
     * Create function interface with lambda expression
     */
    @Test
    public void createFruitComparatorWithLambdaExpression() {
	FruitComparator weightComparator = (a, b) -> a.getWeight() - b.getWeight();
    }

    @Test
    public void fruitFilterTestRun() {
	Fruit apple = new Apple();
	apple.setRipe(false);
	apple.setColor(Color.RED);

	Fruit durian = new Durian();
	durian.setRipe(true);
	durian.setColor(Color.YELLOW);

	List<Fruit> fruitShelf = new ArrayList<>(Arrays.asList(apple, durian));

	List<Fruit> basket = new ArrayList<>();

	for (Fruit fruit : fruitShelf) {
	    if (FruitFilter.IS_RIPE().match(fruit)) {
		basket.add(fruit);
	    }
	}

	assertTrue(CollectionUtils.isNotEmpty(basket));
    }
}
