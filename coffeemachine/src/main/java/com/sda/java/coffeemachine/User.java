package com.sda.java.coffeemachine;

import java.util.Random;

public class User {

    private CoffeeMachine coffeeMachine;
    private Coffee coffee;

    public User(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public Coffee getCoffee() {
        final byte coffeeTypesCount = (byte) CoffeeType.values().length;
        Random random = new Random();
        final int randomPick = random.nextInt(coffeeTypesCount);
        final CoffeeType randomChoosenCoffee = CoffeeType.values()[randomPick];

        coffeeMachine.chooseCoffee(randomChoosenCoffee);
        try {
            coffee = coffeeMachine.prepareCoffee();
        } catch (NotEnoughIngredientsException e) {
            System.out.println("User could not get coffee! " + e.getMessage());
        }

        return coffee;
    }

    public void consumeCoffee() {
        if (coffee == null ) {
            System.out.println(" No coffee available" );
        } else {
            System.out.println(" Consuming the coffee " + coffee.toString());
            coffee = null;
        }

    }
}
