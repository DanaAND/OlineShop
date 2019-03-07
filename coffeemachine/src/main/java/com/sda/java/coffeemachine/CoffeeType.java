package com.sda.java.coffeemachine;

public enum CoffeeType {

    ESPRESSO (5, 50, 0, 0), FILTERCOFFEE (5, 50, 5, 0),
    LATTE (5, 50, 5, 10);

    final int coffeeRequired;
    final int waterRequired;
    final int sugarRequired;
    final int milkRequired;


    CoffeeType (int coffee, int water, int sugar, int milk) {

        this.coffeeRequired = coffee;
        this.waterRequired = water;
        this.sugarRequired = sugar;
        this.milkRequired = milk;
    }

    public int getCoffeeRequired() {
        return coffeeRequired;
    }

    public int getWaterRequired() {
        return waterRequired;
    }

    public int getSugarRequired() {
        return sugarRequired;
    }

    public int getMilkRequired() {
        return milkRequired;
    }
}
