package com.sda.java.emag;

import com.sda.java.emag.businesslogic.Cart;
import com.sda.java.emag.businesslogic.CartController;
import com.sda.java.emag.businesslogic.Stock;
import com.sda.java.emag.businesslogic.User;
import com.sda.java.emag.item.Color;
import com.sda.java.emag.item.Phone;
import com.sda.java.emag.item.Shoes;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationEmag {

    public static final String PHONE_NAME = "X";
    public static final float PRICE = 1000f;
    public static final float DISPLAY_SIZE = 5.5f;
    public static final String BRAND = "Iphone";
    public static final String CPU = "A10";
    public static final float COMPARE_DOUBLE_DELTA = 0.01f;
    public static final String SHOES_MODEL = "Nike";
    public static final float SHOES_PRICE = 200f;
    public static final String SHOES_BRAND = "Nike";
    public static final int SHOES_SIZE = 26;
    public static final int AVAILABLE_QUANTITY = 100000;
    public static final int REQUESTED_QUANTITY = AVAILABLE_QUANTITY;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Object stockMutex = new Object();
        final Stock baneasaMall = new Stock (new ConcurrentHashMap<>(), stockMutex);
//        baneasaMall.loadState();
//        System.out.println(baneasaMall.showItems());

        final Phone iphone = new Phone(PHONE_NAME, PRICE, DISPLAY_SIZE, BRAND, CPU);
        final Shoes shoes = new Shoes(SHOES_MODEL, SHOES_PRICE, SHOES_BRAND, SHOES_SIZE, Color.RED);
        final Cart cart = new Cart(new HashMap<>());

//        final int supply_quatity = 5;
//        final int consume_quatity = 3;
//        final int currentIphonestock = baneasaMall.addItem(iphone, supply_quatity );
//        System.out.println("Initial IphoneX stock quantity: " + currentIphonestock);
//        baneasaMall.addItem(shoes,REQUESTED_QUANTITY);
        baneasaMall.addItem(iphone,AVAILABLE_QUANTITY);

//        int retrivedIphoneXQuantity = baneasaMall.retrieveItem(iphone, consume_quatity);
//        System.out.println("received Iphone x quantity1: " + retrivedIphoneXQuantity);
//        retrivedIphoneXQuantity = baneasaMall.retrieveItem(iphone, consume_quatity);
//        System.out.println("received Iphone x quantity2: " + retrivedIphoneXQuantity);

//        System.out.println(baneasaMall.showItems());
//
//        cart.addedCartItems(iphone, 20);
//        cart.addedCartItems(shoes,100);
//        try {
//            cart.print();
//        }catch ( IOException e ){
//            System.out.println("Cannot access file during print" + e.getMessage());
//        }

//        baneasaMall.saveState();


        CartController cartController = new CartController(baneasaMall);
        final User user1 = new User (new CartController(baneasaMall), iphone, REQUESTED_QUANTITY);
        final User user2 = new User (new CartController(baneasaMall), iphone, REQUESTED_QUANTITY);


        final Thread thread1 = new Thread(user1);
        final Thread thread2 = new Thread(user2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int user1RetrievedItemsQuantity = user1.getRetrievedItemsQuantity();
        System.out.println("USER1's retrieved items" + user1RetrievedItemsQuantity);
        int user2RetrievedItemsQuantity = user2.getRetrievedItemsQuantity();
        System.out.println("USER2's retrieved items" + user2RetrievedItemsQuantity);
        System.out.println("Stock items " + (user1RetrievedItemsQuantity + user2RetrievedItemsQuantity));
        System.out.println(baneasaMall.showItems());
    }

}
