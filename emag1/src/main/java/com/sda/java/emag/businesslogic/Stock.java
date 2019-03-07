package com.sda.java.emag.businesslogic;

import com.sda.java.emag.item.Item;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Stock implements Serializable {
    private final long serialVersionUID = 1L;
    public static final String SEPARATOR = ", ";

    private Map<Item, Integer> items;

    private final transient Object mutex;

    public Stock(Map<Item, Integer> items, Object mutex) {
        this.mutex = mutex;
        this.items = items;
    }

//    public int addItem(Item item, int supply_quatity) {
//        if (items.containsKey(item)) {
//            final int supply = items.get(item) + supply_quatity;
//            items.put(item, supply);
//            return supply;
//        }
//        items.put(item, supply_quatity);
//        return supply_quatity;
//    }

    public int addItem(Item item, int supply_quatity) {
        final Integer previousValue = items.putIfAbsent(item, supply_quatity);
        if (previousValue == null)
            if (items.containsKey(item)) {
                return supply_quatity;
            }
        synchronized (mutex) {
            final int supply = items.get(item) + supply_quatity;
            items.put(item, supply_quatity);
            return supply;
        }
    }


    public int retrieveItem(Item item, int consume_quatity) {
        synchronized (mutex) {

            if (!items.containsKey(item)) {
                return 0;
            }
            int currentQuantity = items.get(item);
            if (currentQuantity < consume_quatity) {
                items.put(item, 0);
                return currentQuantity;
            }
            int updateQuantity = currentQuantity - consume_quatity;
            items.put(item, updateQuantity);
            return consume_quatity;
        }
    }

    public String showItems() {
        final StringBuilder displayResult = new StringBuilder();
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            final Item key = itemEntry.getKey();
            displayResult.append(key.showDetails());
            displayResult.append(SEPARATOR);
            displayResult.append(itemEntry.getValue());
            displayResult.append(System.lineSeparator());

        }
        return displayResult.toString();
    }

    public void saveState() throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream("Serializat");
        final BufferedOutputStream bufferedWriter = new BufferedOutputStream(fileOutputStream);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedWriter);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void loadState() throws IOException, ClassNotFoundException {
        if (!Files.exists(Paths.get("Serializat"))) {
            return;
        }
        final FileInputStream fileInputStream = new FileInputStream("Serializat");
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        final Stock readObject = (Stock) objectInputStream.readObject();
        items = readObject.items;
        objectInputStream.close();
        fileInputStream.close();
    }




}
