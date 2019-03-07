package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.menu.Espresso;
import com.sda.java.coffeemachine.menu.FilterCoffee;
import com.sda.java.coffeemachine.menu.Latte;

import java.util.Map;

public class CoffeeMachine {

    Stock stock = new Stock();


    public Stock getStock (){
        return stock;
    }

    CoffeeType coffeeType = CoffeeType.FILTERCOFFEE;

    public static void main(String[] args) throws Exception {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Stock stock = coffeeMachine.getStock();
        stock.removeCoffee(100);
        final Coffee coffee = coffeeMachine.prepareCoffee();
        System.out.println("Consuming the coffee : " + coffee);

    }

    public void setCoffeeType(CoffeeType coffeeType) {
        //todo: add check if credit is sufficient and show message
        this.coffeeType = coffeeType;
    }

    public Coffee prepareCoffee() throws Exception {
        if (stock.getCoffeeStock() > coffeeType.getCoffeeRequired()
                && stock.getWaterStock() > coffeeType.getWaterRequired()
                && stock.getMilkStock() > coffeeType.getMilkRequired()
                && stock.getSugarStock() > coffeeType.getSugarRequired()) {
            stock.removeCoffee(coffeeType.getCoffeeRequired());
            stock.removeWater(coffeeType.getWaterRequired());
            stock.removeMilk(coffeeType.getMilkRequired());
            stock.removeSugar(coffeeType.getSugarRequired());

            return createCoffee();

        }
        throw new Exception("Cannot create coffee");
    }

    private Coffee createCoffee() {
        switch (coffeeType) {

            case ESPRESSO:
                return new Espresso();

            case FILTERCOFFEE:
                return new FilterCoffee();

            case LATTE:
                return new Latte();

            default:
                return new FilterCoffee();
        }

    }

}
