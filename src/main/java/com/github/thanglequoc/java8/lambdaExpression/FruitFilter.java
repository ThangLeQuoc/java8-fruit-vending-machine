package com.github.thanglequoc.java8.lambdaExpression;

import com.github.thanglequoc.java8.common.model.Apple;

@FunctionalInterface
public interface FruitFilter {
    public boolean isFruitEquals(Apple a, Apple b);
}
