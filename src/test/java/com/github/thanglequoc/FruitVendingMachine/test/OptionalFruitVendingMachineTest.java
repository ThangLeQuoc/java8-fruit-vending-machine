package com.github.thanglequoc.FruitVendingMachine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.github.thanglequoc.FruitVendingMachine.FruitOutOfStockException;
import com.github.thanglequoc.FruitVendingMachine.NotEnoughBalanceException;
import com.github.thanglequoc.FruitVendingMachine.OptionalFruitVendingMachine;
import com.github.thanglequoc.FruitVendingMachine.fruits.Banana;
import com.github.thanglequoc.FruitVendingMachine.fruits.Durian;
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
    public void testAddBalance() {
        long balance = 0;
        assertEquals(balance, fruitVendingMachine.getCurrentBalance());
        
        balance += 15000;
        fruitVendingMachine.addBalance(balance);
        assertEquals(balance, fruitVendingMachine.getCurrentBalance());
    }
    
    @Test
    public void testBuyFruit() {
        int index = 0;
        Fruit orange = new Orange();
        fruitVendingMachine.setFruit(index, orange);
        fruitVendingMachine.addBalance(100000000);
        Fruit result = fruitVendingMachine.buyFruit(0);
        assertNotNull(result);
    }
    
    @Test(expected = FruitOutOfStockException.class)
    public void testBuyFruit_ShouldThrowFruitOutOfStockException() {
        
        fruitVendingMachine.addBalance(10000);
        fruitVendingMachine.buyFruit(0);
    }
    
    @Test(expected = NotEnoughBalanceException.class)
    public void testBuyFruit_ShouldThrowNotEnoughBalanceException() {
        
        assertEquals(0, fruitVendingMachine.getCurrentBalance());
        fruitVendingMachine.addBalance(1000);
        
        Fruit durian = new Durian();
        fruitVendingMachine.setFruit(0, durian);
        
        Fruit result = fruitVendingMachine.buyFruit(0);
    }
    
    @Test
    public void testSetFruit() {
        int i = 0 ;
        fruitVendingMachine.addBalance(1000000);
        Fruit orange = new Orange();
        fruitVendingMachine.setFruit(i, orange);
        
        Fruit result = fruitVendingMachine.buyFruit(i);
        assertEquals(orange, result);
        
        i = OptionalFruitVendingMachine.MAX_MACHINE_SLOTS-1;
        Fruit banana = new Banana();
        fruitVendingMachine.setFruit(i, banana);
        
        result = fruitVendingMachine.buyFruit(i);
        assertEquals(banana, result);
    }
}
