package com.github.thanglequoc.FruitVendingMachine.functions.test;

import org.junit.Test;

import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;
import com.github.thanglequoc.FruitVendingMachine.functions.FruitFilter;

public class FruitFilterTest {
    
    /*
    * Create functional interface implementation with anonymous static inner type
    * */
    @Test
    public void testInstatiateFruitFilter_WithInnerDeclaration() {
        FruitFilter myFilter = new FruitFilter() {
            
            @Override
            public boolean match(Fruit fruit) {
                return fruit.getWeight() > 40;
            }
        };
    }
    
    @Test
    public void testInstatiateFruitFilter_WithLambdaExpression() {
        FruitFilter myFilter = fruit -> fruit.getWeight() > 40;
    }
    
    
}
