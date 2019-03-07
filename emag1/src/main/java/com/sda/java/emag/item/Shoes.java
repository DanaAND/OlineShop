package com.sda.java.emag.item;

import java.util.Objects;

import static com.sda.java.emag.businesslogic.Stock.SEPARATOR;

public class Shoes extends Item {

    private final String brand;
    private final int size;
    private final Color color;


    public Shoes(String name, float price, String brand, int size, Color color1) {
        super(Category.FASHION, name, price);
        this.brand = brand;
        this.size = size;
        this.color = color1;
    }

    public String getBrand() {
        return brand;
    }

    public float getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public String showDetails(){
        final StringBuilder displayShoesDetails = new StringBuilder();
        displayShoesDetails.append(getItemDetails());
        displayShoesDetails.append(SEPARATOR);
        displayShoesDetails.append(brand);
        displayShoesDetails.append(SEPARATOR);
        displayShoesDetails.append(size);
        displayShoesDetails.append(SEPARATOR);
        displayShoesDetails.append(color);
        return displayShoesDetails.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return size == shoes.size &&
                Objects.equals(brand, shoes.brand) &&
                color == shoes.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, size, color);
    }
}
