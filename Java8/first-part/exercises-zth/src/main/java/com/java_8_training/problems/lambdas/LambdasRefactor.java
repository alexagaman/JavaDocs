package com.java_8_training.problems.lambdas;


import com.java_8_training.problems.lambdas.model.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

public class LambdasRefactor {
    public static void main(String[] args) {
        System.out.println(sortInventoryByDecreasingWeight());
        System.out.println(filterGreenApples());
    }

    public static List<Apple> sortInventoryByDecreasingWeight() {
        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
        inventory.sort(( apple1, apple2) ->apple2.getWeight().compareTo(apple1.getWeight()));
        return inventory;
    }

    public static List<Apple> filterGreenApples() {

        // TODO: refactor to use lambda expression
        // TODO: refactor to use standard functional interface
        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

        List<Apple> greenApples = filterApples(inventory, (apple)->"green".equals(apple.getColor()));

        return greenApples;
    }

    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @FunctionalInterface
    interface ApplePredicate {
        boolean test(Apple apple);
    }
}
