package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Cart {
    public static final String SEPARATOR = ", ";
    private Map<Item, Integer> userItems;
    private float total;

    public Cart(Map<Item, Integer> items) {
        this.userItems = items;
    }

    public int addedCartItems(Item item, int addQuatity) {
        total += addQuatity * item.getPrice();
        if (userItems.containsKey(item)) {
            final int updatedQuantity = userItems.get(item) + addQuatity;
            userItems.put(item, updatedQuantity);
            return updatedQuantity;
        }
        userItems.put(item, addQuatity);
        return addQuatity;
    }

    public int removedCartItem(Item item, int removeQuantity) {
        if (!userItems.containsKey(item)) {
            return 0;
        }
        int currentQuantity = userItems.get(item);
        if (currentQuantity <= removeQuantity) {
            userItems.remove(item);
            total -= currentQuantity * item.getPrice();
            return currentQuantity;
        }

        int updateQuantity = currentQuantity - removeQuantity;
        userItems.put(item, updateQuantity);
        total -= currentQuantity * item.getPrice();
        return removeQuantity;
    }

    public Map<Item, Integer> removeAll() {
        final Map<Item, Integer> previousState = userItems;
        userItems = new HashMap<>();
        return previousState;
    }


    public String checkout() {
        String processedItems = removeAll().entrySet().stream()
                .map(itemEntry -> // parcurge toate elementele(un fel de for each) si returneaza orice tip/forma de date
                {
                    final StringBuilder displayResult = new StringBuilder();
                    final Item key = itemEntry.getKey();
                    displayResult.append(key.showDetails());
                    displayResult.append(SEPARATOR);
                    displayResult.append(itemEntry.getValue());
                    displayResult.append(System.lineSeparator());
                    return displayResult.toString();
                })
                .collect(Collectors.joining(System.lineSeparator()));
        removeAll();
        return processedItems;
    }

    private String getString() {
        return userItems.entrySet().stream().map(printEntry ->
                    {
                final StringBuilder displayResult = new StringBuilder();
                final Item key = printEntry.getKey();
                displayResult.append(key.showDetails());
                displayResult.append(SEPARATOR);
                displayResult.append(printEntry.getValue());
                displayResult.append(System.lineSeparator());
                return displayResult;


                    })
            .collect(Collectors.joining(System.lineSeparator()));
    }

    public void print() throws IOException {

        final FileWriter fileWriter = new FileWriter("print_cart.txt");
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        final  String processedItems = getString();
        bufferedWriter.write(processedItems);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

}
