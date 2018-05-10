package com.github.thanglequoc.java8.lambdaExpression.functionalInterface;

import com.github.thanglequoc.java8.common.model.Fruit;

@FunctionalInterface
public interface FruitFilter {
	public boolean match(Fruit fruit);
	
	static FruitFilter IS_RIPE() {
		return (fruit) -> fruit.isRipe();
	}
}
