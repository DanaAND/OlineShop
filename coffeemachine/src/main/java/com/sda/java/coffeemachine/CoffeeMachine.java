package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.menu.Espresso;
import com.sda.java.coffeemachine.menu.FilterCoffee;
import com.sda.java.coffeemachine.menu.Latte;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {

    Stock stock = new Stock();
    CoffeeType coffeeType;

    List<CoffeeMachineLog> preparedCoffeeHistory = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Stock stock = coffeeMachine.stock;
        stock.addToStock(Ingredient.COFFEE, 200);
        stock.addToStock(Ingredient.WATER, 200);
        stock.addToStock(Ingredient.MILK, 200);
        stock.addToStock(Ingredient.SUGAR, 200);

        coffeeMachine.chooseCoffee(CoffeeType.LATTE);
        coffeeMachine.prepareCoffee();
        coffeeMachine.chooseCoffee(CoffeeType.ESPRESSO);
        coffeeMachine.prepareCoffee();
        coffeeMachine.chooseCoffee(CoffeeType.FILTERCOFFEE);
        coffeeMachine.prepareCoffee();

        try {
            final Coffee coffee = coffeeMachine.prepareCoffee();
            System.out.println("Consuming the coffee : " + coffee);
        } catch ( NotEnoughIngredientsException e ) {
            System.out.println(e.getMessage());
        }

        String log = coffeeMachine.showLog();
        System.out.println("Printing history : " + System.lineSeparator() + log);
        Files.write(Paths.get("print_history.log"), log.getBytes());


    }

    public void chooseCoffee(CoffeeType coffeeType) {
        //todo: add check if credit is sufficient and show message
        this.coffeeType = coffeeType;
    }

    public Stock getStock() {
        return stock;
    }


    public Coffee prepareCoffee() throws Exception {
        validateStock();
        stock.removeFromStock(Ingredient.COFFEE, coffeeType.getCoffeeRequired());
        stock.removeFromStock(Ingredient.WATER, coffeeType.getWaterRequired());
        stock.removeFromStock(Ingredient.MILK, coffeeType.getMilkRequired());
        stock.removeFromStock(Ingredient.SUGAR, coffeeType.getSugarRequired());

        Coffee coffee = createCoffee();
        CoffeeMachineLog coffeeMachineLog = new CoffeeMachineLog(coffee);
        preparedCoffeeHistory.add(coffeeMachineLog);
        return coffee;
    }

    private void validateStock() throws NotEnoughIngredientsException {
        if (!(stock.getIngredient(Ingredient.COFFEE) >= coffeeType.getCoffeeRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.COFFEE);
        }
        if (!(stock.getIngredient(Ingredient.WATER) >= coffeeType.getWaterRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.WATER);
        }
        if (!(stock.getIngredient(Ingredient.MILK) >= coffeeType.getMilkRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.MILK);
        }
        if (!(stock.getIngredient(Ingredient.SUGAR) >= coffeeType.getSugarRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.SUGAR);
        }

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

    public String showLog() {
        final StringBuilder stringBuilder = new StringBuilder();
        preparedCoffeeHistory.forEach(logEntry ->

                stringBuilder.append(logEntry).append(System.lineSeparator())
        );

        return stringBuilder.toString();

    }

}
