package com.github.thanglequoc.java8.lambdaExpression;

import java.util.Comparator;

import com.github.thanglequoc.java8.common.model.Apple;
import com.github.thanglequoc.java8.common.model.DummyInterface;

public class MyDummyComparator {
    DummyInterface<Apple> dummy = (a, b) -> false;
}
