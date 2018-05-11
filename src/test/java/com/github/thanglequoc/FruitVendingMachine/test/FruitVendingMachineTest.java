package com.github.thanglequoc.FruitVendingMachine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.github.thanglequoc.FruitVendingMachine.FruitVendingMachine;
import com.github.thanglequoc.FruitVendingMachine.fruits.Apple;
import com.github.thanglequoc.FruitVendingMachine.fruits.Banana;
import com.github.thanglequoc.FruitVendingMachine.fruits.Durian;
import com.github.thanglequoc.FruitVendingMachine.fruits.Fruit;
import com.github.thanglequoc.FruitVendingMachine.fruits.Orange;
import com.github.thanglequoc.FruitVendingMachine.fruits.Papaya;
import com.github.thanglequoc.FruitVendingMachine.fruits.Rambutan;
import com.github.thanglequoc.FruitVendingMachine.fruits.Sapodillia;
import com.github.thanglequoc.FruitVendingMachine.fruits.common.Color;
import com.github.thanglequoc.FruitVendingMachine.fruits.common.Size;
import com.github.thanglequoc.FruitVendingMachine.functions.FruitFilter;

public class FruitVendingMachineTest {

    private FruitVendingMachine fruitVendingMachine;

    @Before
    public void setUp() {
        fruitVendingMachine = new FruitVendingMachine();
    }

    @Test
    public void testLoadFruits() {
        fruitVendingMachine.setFruits(shipSomeFruits());
        assertEquals(7, fruitVendingMachine.getFruits().size());
    }

    @Test
    public void testFilter_ShouldFilterFruitWithSmallSize() {
        // given
        fruitVendingMachine.setFruits(shipSomeFruits());

        // when
        List<Fruit> actualFruits = fruitVendingMachine.filterFruit(FruitFilter.WITH_SIZE(Size.SMALL));

        // then
        assertEquals(2, actualFruits.size());
        List<String> expectedFruitNames = Arrays.asList("Banana", "Rambutan");
        List<String> actualFruitNames = actualFruits.stream().map(fruit -> fruit.getName())
                .collect(Collectors.toList());
        assertTrue(actualFruitNames.containsAll(expectedFruitNames));
    }

    @Test
    public void testFilter_ShouldFilterRipeFruit() {
        // given
        fruitVendingMachine.setFruits(shipSomeFruits());

        // when
        List<Fruit> ripeFruit = fruitVendingMachine.filterFruit(FruitFilter.IS_RIPE());
        
        List<String> expectedRipeFruitNames = Arrays.asList("Apple", "Banana", "Papaya");
        List<String> actualRipeFruitNames = ripeFruit.stream().map(Fruit::getName).collect(Collectors.toList());
        assertTrue(actualRipeFruitNames.containsAll(expectedRipeFruitNames));
    }

    @Test
    public void testFilter_ShouldFilterYellowColorFruit() {
        // given
        fruitVendingMachine.setFruits(shipSomeFruits());

        // when
        List<Fruit> yellowColorFruit = fruitVendingMachine.filterFruit(FruitFilter.WITH_COLOR(Color.YELLOW));
    }

    private List<Fruit> shipSomeFruits() {
        List<Fruit> fruits = new ArrayList<>();

        Fruit apple = new Apple();
        apple.setSize(Size.MEDIUM);
        apple.setNutritionalScore(40);
        apple.setRipe(true);
        apple.setWeight(30);
        fruits.add(apple);

        Fruit banana = new Banana();
        banana.setSize(Size.SMALL);
        banana.setNutritionalScore(20);
        banana.setWeight(10);
        banana.setRipe(true);
        fruits.add(banana);

        Fruit durian = new Durian();
        durian.setSize(Size.LARGE);
        durian.setNutritionalScore(200);
        durian.setRipe(false);
        durian.setWeight(300);
        fruits.add(durian);

        Fruit orange = new Orange();
        orange.setSize(Size.MEDIUM);
        orange.setNutritionalScore(20);
        orange.setRipe(false);
        orange.setWeight(40);
        fruits.add(orange);

        Fruit papaya = new Papaya();
        papaya.setSize(Size.LARGE);
        papaya.setNutritionalScore(70);
        papaya.setRipe(true);
        fruits.add(papaya);

        Fruit rambutan = new Rambutan();
        rambutan.setSize(Size.SMALL);
        rambutan.setNutritionalScore(10);
        rambutan.setRipe(true);
        rambutan.setWeight(15);
        fruits.add(rambutan);

        Fruit sapodillia = new Sapodillia();
        sapodillia.setSize(Size.MEDIUM);
        sapodillia.setNutritionalScore(50);
        sapodillia.setRipe(false);
        sapodillia.setWeight(60);
        fruits.add(sapodillia);

        return fruits;
    }

}
