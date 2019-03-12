package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.Coffee;
import com.sda.java.coffeemachine.CoffeeType;

public interface CoffeeMachine {

    void chooseCoffee(CoffeeType coffeeType);

    Coffee prepareCoffee() throws NotEnoughIngredientsException;
}
