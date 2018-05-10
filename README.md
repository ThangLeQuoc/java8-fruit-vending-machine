# Lambda Expression
## Concepts and Attributes:
### Concept:

### Syntax:

### Where and how to use Lambda:

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



### Method Reference:

>Reference Sources
https://www.codeproject.com/Articles/780806/Lambda-Expressions-in-Java
http://tutorials.jenkov.com/java/lambda-expressions.html
https://medium.freecodecamp.org/learn-these-4-things-and-working-with-lambda-expressions-b0ab36e0fffc
http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html