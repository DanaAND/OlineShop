package com.sda.java.coffeemachine.menu;

import com.sda.java.coffeemachine.Coffee;
import com.sda.java.coffeemachine.CoffeeType;

public class Espresso extends Coffee {

    public static final CoffeeType ESPRESSO = CoffeeType.ESPRESSO;

    public Espresso() {
        super(ESPRESSO);
    }

    protected String getCoffeeName() {
        return ESPRESSO.toString();
    }
}
