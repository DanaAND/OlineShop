package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartController {
    
    private final Cart cart = new Cart(new LinkedHashMap<>());
    private final Stock stock;

    public CartController (Stock stock) {
        this.stock = stock;
    }

    public int addItemToCart (Item item, int quatity){
        final int supply = stock.retrieveItem(item,quatity);
        cart.addedCartItems(item,supply);
        return supply;
    }

    public int removeItemFromCart (Item item, int quantity){
        final int remove = cart.removedCartItem(item,quantity);
        return stock.addItem(item, remove);
    }

    public int removeAllCartItems () {
        final Map<Item, Integer> removedItems = cart.removeAll();
        for( Map.Entry<Item,Integer> entry : removedItems.entrySet()){
            stock.addItem(entry.getKey(), entry.getValue());
        }
        return removedItems.entrySet().size();

//   varianta cu Lambda
//        final Map<Item, Integer> removedItems = cart.removeAll();
//        removeItems.forEach(stock::addItem; //not functional oriented programming
//        return removedItems.entrySet().size();
    }

}
