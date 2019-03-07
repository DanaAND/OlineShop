package com.sda.java.coffeemachine;

public abstract class Coffee {

    private final int coffee;
    private final int water;
    private final int sugar;
    private final int milk;

    public Coffee(CoffeeType coffeeType) {
        this.coffee = coffeeType.getCoffeeRequired();
        this.water = coffeeType.getWaterRequired();
        this.sugar = coffeeType.getSugarRequired();
        this.milk = coffeeType.getMilkRequired();
    }

    protected abstract String getCoffeeName();

    @Override
    public String toString() {
        return getCoffeeName() + "{" +
                "coffee=" + coffee +
                ", water=" + water +
                ", sugar=" + sugar +
                ", milk=" + milk +
                '}';
    }



}
