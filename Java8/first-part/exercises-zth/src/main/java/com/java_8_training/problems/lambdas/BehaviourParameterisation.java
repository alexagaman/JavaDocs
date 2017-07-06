package com.java_8_training.problems.lambdas;

import com.java_8_training.problems.lambdas.model.Apple;
import javafx.print.Printer;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public class BehaviourParameterisation {


    //TODO: 1. Implement the method prettyPrintOnlyWeightApple which prints to the console something like 'An apple of 60 grams'

    //TODO: 2. Declare a new method prettyPrintApple which takes different formatters as parameter.
    // The formatter should be an interface that has a method which accepts an apple and returns a string from it.
    // Use the COllections.sort() method as an example

    //TODO: can you refactor prettyPrintOnlyWeightApple to use it?
    //TODO: can you refactor prettyPrintHeavyLightApple to use it?
    //TODO: can you make prettyPrintApple generic (i.e. can work with any type not just Apple)?

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
                new Apple(120, "red"));

        System.out.println("bad way\n");
        prettyPrintHeavyLightApple(inventory);
        prettyPrintOnlyWeightApple(inventory, new AppleWeightPrint());
        //print apple color
        System.out.println("good way\n");
        prettyPrintApple(inventory, new AppleColorPrint());
        //print apple weight
        prettyPrintApple(inventory, new AppleWeightPrint());
    }

    public static void prettyPrintHeavyLightApple(List<Apple> inventory) {
        Objects.requireNonNull(inventory, "Inventory must not be null");
        for (Apple apple : inventory) {
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            String output = "A " + characteristic + " " + apple.getColor() + " apple";
            System.out.println(output);
        }
    }

    public static void  prettyPrintApple(List<Apple> inventory, ApplePrinter printer){
        for (Apple apple : inventory) {
            printer.print(apple);
        }
    }
    /**
     * Prints all the weights from the inventory one by one
     *
     * @param inventory
     */
    public static void prettyPrintOnlyWeightApple(List<Apple> inventory,ApplePrinter printer) {
        Objects.requireNonNull(inventory, "Inventory must not be null");
        for (Apple apple : inventory) {
           printer.print(apple);
        }
    }
}



@FunctionalInterface
interface ApplePrinter<T>{
    void print(T a);
    default void test(){

    }
}

class AppleColorPrint implements  ApplePrinter<Apple>{

    @Override
    public void print(Apple a) {
        System.out.println("A " + a.getColor() + " apple");
    }
}
class AppleWeightPrint implements  ApplePrinter<Apple>{

    @Override
    public void print(Apple a) {
        System.out.println("An apple of " + a.getWeight());
    }
}

class customPrinter implements ApplePrinter{

    @Override
    public void print(Object a) {
        if(a instanceof Apple){
            Apple apple = (Apple) a;

        }

    }
}