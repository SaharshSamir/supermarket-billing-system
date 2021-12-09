package com.supermarket_billing.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class Item {
    // PRIVATE
    private String name;
    private float price;
    // private Categories category;
    private int itemId;
    private ArrayList<String> categories = new ArrayList<String>(Arrays.asList("PRODUCE",
            "BEVERAGES",
            "BAKERY",
            "CANNED GOODS",
            "DRY_GOODS",
            "FROZEN FOOD",
            "MEAT",
            "CLEANERS",
            "PERSONAL CARE",
            "PAPER GOODS",
            "OTHER"));
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

    // public Categories getCategory() {
    // return this;
    // }

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

    // public void setCategory(Categories category) {
    // this.category = category;
    // }

    public void setPrice(float price) {
        this.price = price;
    }

    public Item() {
        this.name = "";
        // this.category = Categories.OTHER;
        this.price = 0;
        this.itemId = 0;
    }

    // public Item(String name, Categories category, float price, int itemId) {
    // this.name = name;
    // this.category = category;
    // this.price = price;
    // this.itemId = itemId;
    // }

    // @Override
    // public String toString() {
    // String output = "Name: " + this.name + "\n" + "Price: " + this.price + "\n "
    // + "Category: " + this.category;
    // return output;
    // }
}
