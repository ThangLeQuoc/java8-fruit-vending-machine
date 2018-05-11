# Lambda Expression
## Concepts and Attributes:
### Concepts
Lambda Expression can be understood as a concise representation of an anonymous function that **can be passed around**.
It doesn't have name, but has parameters, a body, a return type, and possibly exceptions that can be thrown.
### Attributes
* _**Anonymous**_ : it doesn't have an explicit name like a method normally, less to write and care about.

* _**Function**_: A Lambda isn't associated with a particular class like method is. But like a method, a lambda has parameters, a body
and return type

* _**Passed Around**_: A Lambda Expression can be passed as argument to a method or stored in a variable.

* _**Concise**_: You don't need to write a lot of boilerplate code like you do for anonymous classes.

### Syntax:
> A Lambda Expression is composed of parameters, an arrow and body
![Imgur](https://i.imgur.com/7AGY5w2.png)

Two types of implements:

`(parameters) -> expression`

`(parameters) -> {statements;}`

| Use case                   | Examples of Lambda           |
| ---------------------------|:-------------:|
| A boolean expression       | right-aligned |
| Creating objects           | centered      |
| Consuming objects          | are neat      |
| Select/Extract from object | are neat      |
| Compare two values         | are neat      |
| Compare two objects        | are neat      |

### Functional Interface:
> A functional interface is an interface that contains one any only one abstract method.

Functional interface treat a function like an object, which allow us to pass the verbs (functions) around the program rather than nouns (objects).

*Best Practice*: Annotate functional interface with `@FunctionalInterface`, the compiler will trigger an error in response to any attempt to break the predefined structure of a functional interface.

**Why functional interface only provide one abstract method ?**
>Since a lambda function can only provide the implementation for ONE method, so it is mandatory for functional interface to have ONLY ONE abstract method.


This  declaration will raise error since there are more than one abstract methods declared:
```
@FunctionalInterface
public interface DummyInterface<T> {
    public boolean isGreater(T a, T b);
    public void doSomeWork();
}
```

Let's create a `FruitFilter` functional interface, with one abstract method to check if the fruit match the condition wanted:

`FruitFilter`
```
@FunctionalInterface
public interface FruitFilter {
    public boolean match(Fruit fruit);
}
```

#### Instantiate Functional Interface
##### Traditional Inner Class

http://www.baeldung.com/java-8-functional-interfaces
```

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
```
##### With Lambda Expression
```
/*
* Create function interface with lambda expression
* */
@Test
public void testInstatiateFruitFilter_WithLambdaExpression() {
    FruitFilter myFilter = fruit -> fruit.getWeight() > 40;
}
```

### The Fruit Vending Machine Application
We'll create a simple Fruit Vending Machine. A fruit vending machine store a list of _fruits_ and have
method to _filter_ specific kind of fruits that customer needs.

Let's abstract it out, we need some `fruit` first. Let's create `Fruit` abstract class and some concrete fruit implementation `Apple`, `Orange`, `Banana`, etc.

`Fruit.java`
```
public abstract class Fruit {
    private String name;
    private int weight;
    private Color color;
    private int nutritionalScore;
    private boolean ripe;
    private Size size;
}
```

`Apple.java`
```
public class Apple extends Fruit {
    private static final String NAME = "Apple";

    public Apple() {
        super(NAME);
        this.setColor(Color.RED);
    }
}
```
We need a `FruitFilter` act as a functional interface. Each `FruitFilter` implement will provide
filter instruction for the fruit vending machine.

`FruitFilter.java`
```
@FunctionalInterface
public interface FruitFilter {
    public boolean match(Fruit fruit);

    static FruitFilter IS_RIPE() {
        return (fruit) -> fruit.isRipe();
    }

    static FruitFilter WITH_COLOR(Color color) {
        return (fruit) -> color.equals(fruit.getColor());
    }

    static FruitFilter WITH_SIZE(Size size) {
        return (fruit) -> size.equals(fruit.getSize());
    }

    static FruitFilter IS_NUTRIOUS() {
        return (fruit) -> fruit.getNutritionalScore() > 30;
    }

}
```
To keep thing clean, `FruitFilter` make use of **Java 8 Interface static method** to create some commonly used filter.

We have fruits, and filter instructions, it's time to build a fruit vending machine and hook everything up.
`FruitVendingMachine.java`
```
public class FruitVendingMachine {

    List<Fruit> fruits;

    public FruitVendingMachine() {
        fruits = new ArrayList<>();
    }

    public List<Fruit> filterFruit(FruitFilter filter) {
        if (CollectionUtils.isEmpty(fruits) || filter == null) {
            return new ArrayList<>();
        }
        return fruits.stream()
                     .filter(fruit -> filter.match(fruit))
                     .collect(Collectors.toList());
    }

    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }
}
```

It's time for a test run:
```
    @Test
    public void testFilter_ShouldFilterFruitWithSmallSize() {
        fruitVendingMachine.setFruits(shipSomeFruits());

        List<Fruit> actualFruits = fruitVendingMachine.filterFruit(FruitFilter.WITH_SIZE(Size.SMALL));

        assertEquals(2, actualFruits.size());
        List<String> expectedFruitNames = Arrays.asList("Banana", "Rambutan");
        List<String> actualFruitNames = actualFruits.stream().map(fruit -> fruit.getName())
                .collect(Collectors.toList());
        assertTrue(actualFruitNames.containsAll(expectedFruitNames));
    }

    @Test
    public void testFilter_ShouldFilterRipeFruit() {
        fruitVendingMachine.setFruits(shipSomeFruits());

        List<Fruit> ripeFruit = fruitVendingMachine.filterFruit(FruitFilter.IS_RIPE());

        List<String> expectedRipeFruitNames = Arrays.asList("Apple", "Banana", "Papaya");
        List<String> actualRipeFruitNames = ripeFruit.stream().map(Fruit::getName).collect(Collectors.toList());
        assertTrue(actualRipeFruitNames.containsAll(expectedRipeFruitNames));
    }
```
### Usage Best Practice
http://www.baeldung.com/java-8-lambda-expressions-tips


>Reference Sources
https://www.codeproject.com/Articles/780806/Lambda-Expressions-in-Java
http://tutorials.jenkov.com/java/lambda-expressions.html
https://medium.freecodecamp.org/learn-these-4-things-and-working-with-lambda-expressions-b0ab36e0fffc
http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html