package com.github.thanglequoc.java8.lambdaExpression;

public class RunablePrinter {
    
    Runnable r1 = () -> {
	System.out.println("Hello world 1");
    };
    Runnable r2 = new Runnable() {
	@Override
	public void run() {
	    System.out.println("Hello world 2");
	}
    };

    public void printSomething() {
	r1.run();
	r2.run();
    }
}
