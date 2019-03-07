package com.sda.java.emag.item;

import java.util.Objects;

import static com.sda.java.emag.businesslogic.Stock.SEPARATOR;

public class Phone extends Item {

    public static final Category CATEGORY = Category.ELECTRONICS;
    private final float display_size;
    private final String brand;
    private final String CPU;

    public Phone(String name, float price, float display_size, String brand, String cpu) {
        super(CATEGORY, name, price);
        this.display_size = display_size;
        this.brand = brand;
        CPU = cpu;
    }

    public float getDisplay_size() {
        return display_size;
    }

    public String getBrand() {
        return brand;
    }

    public String getCPU() {
        return CPU;
    }


    public String showDetails() {
        final StringBuilder displayPhoneDetails = new StringBuilder();
        displayPhoneDetails.append(getItemDetails());
        displayPhoneDetails.append(CATEGORY);
        displayPhoneDetails.append(SEPARATOR);
        displayPhoneDetails.append(display_size);
        displayPhoneDetails.append(SEPARATOR);
        displayPhoneDetails.append(brand);
        displayPhoneDetails.append(SEPARATOR);
        displayPhoneDetails.append(CPU);
        return displayPhoneDetails.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Phone phone = (Phone) o;
        return Float.compare(phone.display_size, display_size) == 0 &&
                Objects.equals(brand, phone.brand) &&
                Objects.equals(CPU, phone.CPU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), display_size, brand, CPU);
    }
}
