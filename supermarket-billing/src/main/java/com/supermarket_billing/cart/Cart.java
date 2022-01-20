package com.supermarket_billing.cart;

import java.util.ArrayList;
import java.util.Iterator;

import com.supermarket_billing.Item.Item;

public class Cart {
    private ArrayList<Item> items = new ArrayList<Item>();
    private float total = 0;

    public void addItemToCart(Item item) {
        items.add(item);
        total += item.getPrice();
    }

    public void showAllItems() {
        Iterator<Item> itemsIt = items.iterator();
        while (itemsIt.hasNext()) {
            Item currentItem = itemsIt.next();
            System.out.println(currentItem.getItemId() + ". " + currentItem.getName());
        }
    }

    public void deleteItem(Item item) {
        total -= item.getPrice();
        items.removeIf(itm -> (itm.getItemId() == item.getItemId()));
    }

    public void clearCart() {
        items.clear();
        total = 0;
    }

    @Override
    public String toString() {
        String str = "\n********CART********\n\nItems: " + items + "\nTotal: " + total;
        return str;
    }

}
