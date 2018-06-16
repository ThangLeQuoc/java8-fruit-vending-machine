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

| Use case                   | Examples of Lambda                                       |
| ---------------------------|:--------------------------------------------------------:|
| A boolean expression       | (List<String> list) -> list.isEmpty()                    |
| Creating objects           | () -> new Apple()                                        |
| Consuming objects          | (Fruit a) -> { System.out.print(a); }                    |
| Select/Extract from object | (Fruit a) -> a.getName()                                 |
| Compare two values         | (int x, int y) -> x>y                                    |
| Compare two objects        | (Fruit a, Fruit b) -> a.getWeight() > b.getWeight()      |

### :large_blue_diamond: Functional Interface:
> A functional interface is an interface that contains one any only one abstract method.

Functional interface treat a function like an object, which allow us to pass the verbs (functions) around the program rather than nouns (objects).

*Best Practice*: Annotate functional interface with `@FunctionalInterface`, the compiler will trigger an error in response to any attempt to break the predefined structure of a functional interface.

**Why functional interface only provide one abstract method ?**
>Since a lambda function can only provide the implementation for ONE method, so it is mandatory for functional interface to have ONLY ONE abstract method.


This  declaration will raise error since there are more than one abstract methods declared:
```
@FunctionalInterface
public interface FruitFilter {
    public boolean match(Fruit fruit);
    public void washFruit(Fruit fruit);
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

### The Fruit Vending Machine Application :convenience_store: :apple: :watermelon: :banana:
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

We got some fruits ready, let's build the fruit vending machine. The fruit vending machine day one is simple, it stores a list of fruit, a couple of useful methods
to fill in, get all the fruit, and most important, the _filter_ method to filter out the specific kind of fruits that customer wanted.

```
public class FruitVendingMachine {

    List<Fruit> fruits;

    public FruitVendingMachine() {
        fruits = new ArrayList<>();
    }

    public List<Fruit> filterFruit(FilterStrategy filterStrategy) {
        if (CollectionUtils.isEmpty(fruits)) {
            return new ArrayList<>();
        }

        if (FilterStrategy.IS_RIPE.equals(filterStrategy)) {
            return fruits.stream().filter(fruit -> fruit.isRipe()).collect(Collectors.toList());
        }
        if (FilterStrategy.WITH_RED_COLOR.equals(filterStrategy)) {
            return fruits.stream().filter(fruit -> Color.RED.equals(fruit.getColor())).collect(Collectors.toList());
        }
        return fruits;
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

The fruit vending machine run well enough, but sometimes later, the client **require more filter strategy**, and **each time a new filter strategy is added, the vending machine have to provide implementation for that strategy**, and the vending machine must be rebuilt again, which made the machine becomes hard to extends. We cannot provide a custom filter strategy at runtime.
This kind of design violate the **Open/Closed** principle in SOLID software design.

> The vending machine only do one simple thing. It filters fruit.

It's time to change the filter strategy, this time we'll provide the filter instruction to the machine. The vending machine now only do one simple thing, **it filters fruit base on the instruction the client provided**. We'll abstract the filter as an interface, and provide implementation for it.

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

`FruitVendingMachineTest.java`
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

> We inject the fruit filter implementation into the vending machine, the fruit vending machine doesn't care what the filter do. It's doesn't have
to worry if a new filter is added. All the thing we need to do, is to provide the _filter instruction_ for the machine.
With more filters coming, the application is very easy to extends.
```
@Test
public void testFilter_ShouldRunWithVariousNewFilters() {
    // given
    fruitVendingMachine.setFruits(shipSomeFruits());

    /* The client want fruit that meet the standard ISO-9001 for fruit. In more detail, a  fruit meet this standard
    * must ripe and has a minimal nutritional score of 100 */

    FruitFilter meetISO9001 = fruit -> fruit.isRipe() && fruit.getNutritionalScore() > 100;
    List<Fruit> iso9001fruits = fruitVendingMachine.filterFruit(meetISO9001);
}
```

 We've just apply the **Strategy Design Pattern** by encapsulate and provide the _filter_ behaviors to the machine, thus applied various OOP design rules within our implementation

 - Open/Closed Principle: No modification on VendingMachine class is required if we add more filters. The application is extensible
 - Dependency Injection: The VendingMachine depend on the abstraction _FruitFilter_ injected in.

### :rocket: Lambda Usage Best Practice
* Prefer standard functional interface
>Functional interfaces, which are gathered in the java.util.function package, satisfy most developers’ needs in providing target types for lambda expressions and method references. Each of these interfaces is general and abstract, making them easy to adapt to almost any lambda expression.

For example, `FruitFilter` functional interface can be removed. We can just use `java.util.function.Predicate` instead

```
@Test
public void createLambdaWithPredicate() {
    Predicate<Fruit> isNutrious = fruit -> fruit.getNutritionalScore() > 50;
    boolean check = isNutrious.test(apple);
}
```

* Use `@FunctionalInterface` annotation

The compiler will check if there is any strange thing that break the predefined structure of a functional interface.

So use this

```
@FunctionalInterface
public interface FruitFilter {
    public boolean match(Fruit fruit);
}
```
insteads of just
```
public interface FruitFilter {
    public boolean match(Fruit fruit);
```
* Instantiate functional interface with Lambda Expression:
Use Lambda Expression to instatiate interface will help reduce the amount of boilerplate code. Make the code easier to read and review

`FruitFilter myFilter = fruit -> fruit.getWeight() > 40;`

* Keep Lambda Expression short and self-explantory
>If possible, use one line constructions instead of a large block of code. Remember **lambdas should be an expression**, **not a narrative**. Despite its concise syntax, lambdas should precisely express the functionality they provide.

* Avoid blocks of code in Lambda's body

In an ideal situation, lambdas should be written in one line of code. With this approach, the lambda is a self-explanatory construction, which declares what action should be executed with what data.
> If you have a large block of code, the lambda's functionality is not immediately clear.

Do
```
public void testInstatiateFruitFilter_CleanWay() {
    char character = 'A';
    FruitFilter myFilter = fruit -> startWithChar(character, fruit.getName());
}

private boolean startWithChar(char character, String str) {
    /* Perform a mysterious nice long regex validation */
    /* Do a meaty validation check, is the char a number ? */
    return true;
}
```
Insteads of
```
@Test
public void testInstatiateFruitFilter_DirtyWay() {
    char character = 'A';
    FruitFilter startWithCharFilter = fruit -> {
        /* Do a meaty validation check, is the char a number ? */
        /* Perform a mysterious nice long regex validation */
        return false;
    };
}
```

* Avoid specifying parameter types

The compiler is able to resolve the type of lambda parameters with the help of [Type Reference](https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html). Adding a type to parameter is optional and can be omitted.

Do `fruit -> fruit.getWeight() > 30` and avoid ` Fruit fruit -> fruit.getWeight() > 30`

* Avoid parentheses around single parameter
Parentheses only required when function accept **more than one parameters** or **no parameter at all**, so parentheses can be removed for case with only one parameter

Do `fruit -> fruit.isRipe()` insteads of `(fruit) -> fruit.isRipe()`

* Avoid `return` statement and braces `{}`

_Braces_ and _return_ statement are optional in one-line lambda bodies. Better remove this for clarity and conciseness.

Do `fruit -> fruit.isRipe()` insteads of `fruit -> { return fruit.isRipe(); }`

>Reference Sources

http://www.baeldung.com/java-8-lambda-expressions-tips
https://www.codeproject.com/Articles/780806/Lambda-Expressions-in-Java
http://tutorials.jenkov.com/java/lambda-expressions.html
https://medium.freecodecamp.org/learn-these-4-things-and-working-with-lambda-expressions-b0ab36e0fffc
http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
http://www.baeldung.com/java-8-functional-interfaces

# Optional
The machine physical menu appearance looks like this:
<pre>
[Apple]-[Orange]-[Banana]-[Rambutan]  | Select Fruit Index [__]
  [1]      [2]       [3]       [4]     |      [Gimme Fruit]
</pre>

Basically, user will choose an index represent the fruit he wants to buy, and hit the shiny
[Gimme Fruit] button.

```
 public Fruit getFruit(int i) {
        if (i >= MAX_MACHINE_SLOTS) throw new IllegalArgumentException("Illegal Fruit Slot");
        if (balance )
        return fruits[i];
    }
```
> What if the fruit user selected is out of stock ?
> What if the current balance is not enough to make a purchase ?

Well just use `Null` to indicate a missing object.

```
 public Fruit buyFruit(int i) {
    if (i >= MAX_MACHINE_SLOTS)
        throw new IllegalArgumentException("Illegal Fruit Slot");
    Fruit selectedFruit = fruits[i];
    if (balance < selectedFruit.getPrice() || selectedFruit == null) {
        return null;
    }

    fruits[i] = null;
    balance -= selectedFruit.getPrice();
    return selectedFruit;
}
```


>A common (bad) practice is to return the null reference to indicate the absence of an object

>"I call it my billion-dollar mistake. It was the invention of the null reference in 1965. I couldn't resist the temptation to put in a null reference, simply because it was so easy to implement." - Tony Hoare

>The main point of `Optional` is to provide a means for a function returning a value to indicate the absence of a return value

Refactor the Optional Fruit Vending Machine

`OptionalFruitVendingMachine.java`
```
public Fruit buyFruit(int i) {
    if (i >= MAX_MACHINE_SLOTS)
        throw new IllegalArgumentException("Illegal Fruit Slot");

    Optional<Fruit> selectedFruit = fruits[i];
    Fruit fruit = selectedFruit.orElseThrow(FruitOutOfStockException::new);
    if (balance < fruit.getPrice()) {
        throw new NotEnoughBalanceException();
    }
    balance -= fruit.getPrice();
    fruits[i] = Optional.empty();
    return fruit;
}
```

