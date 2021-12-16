package com.supermarket_billing.Item;

public class Item {
    // PRIVATE
    private String name;
    private float price;
    private String category;
    private int itemId;

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

    public int getItemId() {
        return this.itemId;
    }

    public String getCategory() {
        return this.category;
    }

    public float getPrice() {
        return this.price;
    }

    // setters
    public void setName(String name) {
        this.name = name;
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

    public Item() {
        this.name = "";
        this.category = "OTHER";
        this.price = 0;
        this.itemId = 0;
    }

    public Item(String name, String category, float price, int itemId) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        String output = "Name: " + this.name + "\n" + "Price: " + this.price + "\n "
                + "Category: " + this.category;
        return output;
    }
}
