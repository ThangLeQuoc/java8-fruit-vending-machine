package com.github.thanglequoc.java8.common.model;

@FunctionalInterface
public interface DummyInterface<T> {
    public boolean isGreater(T a, T b);
    public void doSomework();
}


