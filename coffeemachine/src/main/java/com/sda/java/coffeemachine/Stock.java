package com.sda.java.coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<Ingredient, Integer> ingredients = new HashMap<Ingredient, Integer>();

    public Stock() {
        for (int i = 0; i < Ingredient.values().length; i ++) {
            ingredients.put(Ingredient.values()[i], 0);
        }
    }

    public int getIngredient(Ingredient ingredient) throws NotEnoughIngredientsException {
        if (!ingredients.containsKey(ingredient)) {
            throw new IngredientNotFoundError ("Ingredient not found " + ingredient);
        }
        return ingredients.get(ingredient);
    }

    public void addToStock(Ingredient ingredient, int quantity){
        if (!ingredients.containsKey(ingredient)) {
            throw new IngredientNotFoundError("Ingredient not found " + ingredient);
        }
        Integer currentQuantity = ingredients.get(ingredient);
        ingredients.put(ingredient, quantity + currentQuantity);
    }

    public void removeFromStock(Ingredient ingredient, int quantity) {
        if (!ingredients.containsKey(ingredient)) {
            throw new IngredientNotFoundError("Ingredient not found" + ingredient);
        }
        Integer currentQuantity = ingredients.get(ingredient);
        ingredients.put(ingredient, currentQuantity - quantity);
    }
}
