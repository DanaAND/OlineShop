package com.sda.java.coffeemachine;

public class IngredientNotFoundError extends Error {

    public IngredientNotFoundError(String ingredient) {
        super("Cannot find ingredient : " + ingredient);
    }
}
