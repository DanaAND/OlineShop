package com.sda.java.coffeemachine;

import java.util.Date;
import java.util.List;

public class CoffeeMachineLog {

    Date date = new Date();
    Coffee coffee;

    public CoffeeMachineLog(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String toString() {
        return "CoffeeMachineLog{" +
                "date=" + date +
                ", coffee=" + coffee +
                '}';
    }
}
