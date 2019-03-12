package com.sda.java.coffeemachine;

public class StandardGrinder implements Grinder{

    @Override
    public int grind(CoffeeType coffeeType) {
        System.out.println("Grinding coffee beans: " + coffeeType.getCoffeeRequired());
        return coffeeType.getCoffeeRequired();
    }
}
