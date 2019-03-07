package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.menu.Espresso;

import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<Ingredient, Integer> ingredients = new HashMap<Ingredient, Integer>();

    public Stock (){
        for (Ingredient eachIngredient : Ingredient.values()) {
            ingredients.put(eachIngredient, 0);
        }
    }

    public int getIngredient (Ingredient ingredient) throws Exception {
        if(!ingredients.containsKey(ingredient)) {
            throw new Exception("Ingredient not found" + ingredient);
        }
        return ingredients.get(ingredient);
    }

    public void addToStock (String ingredients, int quantity) {
      //  coffeeStock += quantity;
    }

    public void removeFromStock (int quantity) {
        //coffeeStock - quantity;
    }
}
