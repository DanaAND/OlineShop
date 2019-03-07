package com.sda.java.coffeemachine.menu;

import com.sda.java.coffeemachine.Coffee;
import com.sda.java.coffeemachine.CoffeeType;

public class FilterCoffee extends Coffee {

    public static final CoffeeType FILTERCOFFEE = CoffeeType.FILTERCOFFEE;

    public FilterCoffee() {
        super(FILTERCOFFEE);
    }

    protected String getCoffeeName() {
        return FILTERCOFFEE.toString();
    }
}
