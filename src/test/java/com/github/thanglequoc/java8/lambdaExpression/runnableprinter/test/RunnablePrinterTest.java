package com.github.thanglequoc.java8.lambdaExpression.runnableprinter.test;

import java.util.stream.IntStream;

import org.junit.Test;

import com.github.thanglequoc.java8.lambdaExpression.RunablePrinter;

public class RunnablePrinterTest {
    @Test
    public void shouldPrintSomethingOut() {
	RunablePrinter printer = new RunablePrinter();
	printer.printSomething();
	IntStream.range(2, 3);
    }
}
