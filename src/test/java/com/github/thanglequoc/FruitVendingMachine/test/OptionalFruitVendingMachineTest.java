package com.github.thanglequoc.FruitVendingMachine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.github.thanglequoc.FruitVendingMachine.OptionalFruitVendingMachine;
import com.github.thanglequoc.FruitVendingMachine.fruits.Banana;
import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;
import com.github.thanglequoc.FruitVendingMachine.fruits.Orange;

public class OptionalFruitVendingMachineTest {
    private OptionalFruitVendingMachine fruitVendingMachine;
    
    @Before
    public void setUp() {
        fruitVendingMachine = new OptionalFruitVendingMachine();
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetFruit_AttemptToSetFruitWithOverIndex() {
        int illegalIndex = OptionalFruitVendingMachine.MAX_MACHINE_SLOTS ;
        Fruit orange = new Orange();
        
        try {
            fruitVendingMachine.setFruit(illegalIndex, orange);            
        } catch (IllegalArgumentException e) {
            assertEquals("Illegal Fruit Slot", e.getMessage());
            throw e;
        }
    }
    
    @Test
    public void testGetFruit() {
        int index = 0;
        Fruit orange = new Orange();
        fruitVendingMachine.setFruit(index, orange);
        fruitVendingMachine.addBalance(10000);
        Fruit result = fruitVendingMachine.getFruit(0);
        assertNotNull(result);
    }
    
    @Test
    public void testSetFruit() {
        int i = 0 ;
        Fruit orange = new Orange();
        fruitVendingMachine.setFruit(i, orange);
        
        Fruit result = fruitVendingMachine.getFruit(i);
        assertEquals(orange, result);
        
        i = OptionalFruitVendingMachine.MAX_MACHINE_SLOTS-1;
        Fruit banana = new Banana();
        fruitVendingMachine.setFruit(i, banana);
        
        result = fruitVendingMachine.getFruit(i);
        assertEquals(banana, result);
    }
}
