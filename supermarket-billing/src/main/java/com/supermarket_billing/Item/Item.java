package com.supermarket_billing.Item;

public class Item {
    // PRIVATE
    private String name;
    private float price;
    private String category;
    private int itemId;
    private float discount = 0;
    private float tax = 0;

    // PUBLIC
    // public enum Categories {
    // PRODUCE,
    // BEVERAGES,
    // BAKERY,
    // CANNED_GOODS,
    // DRY_GOODS,
    // FROZEN_FOOD,
    // MEAT,
    // CLEANERS,
    // PERSONAL_CARE,
    // PAPER_GOODS,
    // OTHER
    // }

    // getters
    public String getName() {
        return this.name;
    }

    public float getDiscount() {
        return this.discount;
    }

    public int getItemId() {
        return this.itemId;
    }

    public String getCategory() {
        return this.category;
    }

    public float getPrice() {
        return this.price;
    }

    public float getTax() {
        return this.tax;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount(float disc) {
        this.discount = disc;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public Item() {
        this.name = "";
        this.category = "OTHER";
        this.price = 0;
        this.itemId = 0;
    }

    public Item(String name, String category, float price, int itemId, float discount, float tax) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.itemId = itemId;
        this.discount = discount;
        this.tax = tax;
    }

    @Override
    public String toString() {
        String output = "Name: " + this.name + "\n" + "Price: " + this.price + "\n "
                + "Category: " + this.category + "\nDiscount: " + this.discount;
        return output;
    }
}
