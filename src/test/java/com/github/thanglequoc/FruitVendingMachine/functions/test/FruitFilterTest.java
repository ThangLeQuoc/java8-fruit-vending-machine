package com.github.thanglequoc.FruitVendingMachine.functions.test;

import java.util.function.Predicate;

import org.junit.Test;

import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;
import com.github.thanglequoc.FruitVendingMachine.functions.FruitFilter;

public class FruitFilterTest {

    /*
     * Create functional interface implementation with anonymous static inner type
     */
    @Test
    public void testInstatiateFruitFilter_WithInnerDeclaration() {
        FruitFilter myFilter = new FruitFilter() {

            @Override
            public boolean match(Fruit fruit) {
                return fruit.getWeight() > 40;
            }
        };
    }
    
    /*
    * Create function interface with lambda expression
    * */
    @Test
    public void testInstatiateFruitFilter_WithLambdaExpression() {
        FruitFilter myFilter = fruit -> fruit.getWeight() > 40;
    }

    @Test
    public void testInstatiateFruitFilter_CleanWay() {
        char character = 'A';
        FruitFilter myFilter = fruit -> startWithChar(character, fruit.getName());
    }
    
    @Test
    public void testInstatiateFruitFilter_DirtyWay() {
        char character = 'A';
        FruitFilter startWithCharFilter = fruit -> {
            /* Do a meaty validation check, is the char a number ? */
            /* Perform a mysterious nice long regex validation */
            return false;
        };
    }
    

    private boolean startWithChar(char character, String str) {
        /* Perform a mysterious nice long regex validation */
        return true;
    }

}
