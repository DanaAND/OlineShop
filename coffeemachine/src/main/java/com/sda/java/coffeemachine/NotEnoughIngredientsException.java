package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.Coffee;
import com.sda.java.coffeemachine.CoffeeType;
import com.sda.java.coffeemachine.Ingredient;

public class NotEnoughIngredientsException extends Exception {

    public NotEnoughIngredientsException(CoffeeType coffeeType, Ingredient ingredient ) {
        super("Cannot create coffee: " + coffeeType + " because is not enough: " + ingredient);
    }

}
