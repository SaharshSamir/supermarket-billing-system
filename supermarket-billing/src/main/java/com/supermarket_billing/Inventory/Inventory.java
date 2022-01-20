package com.supermarket_billing.Inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
// import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// import java.io.Reader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.supermarket_billing.Item.Item;
// import com.supermarket_billing.Item.Item.Categories;

// import com.supermarket_billing.utils.JavaUtils;

public class Inventory {
    // PRIVATE
    // private JavaUtils utils = new JavaUtils();
    private ArrayList<Item> items = new ArrayList<Item>();
    // private ArrayList<Categories> categories = new ArrayList<Categories>();
    private Date restockDate = new Date();
    private static ArrayList<String> categories = new ArrayList<String>(Arrays.asList(
            "PRODUCE",
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
    private static String dataLocation = "supermarket-billing/src/main/java/com/supermarket_billing/data/inventory.json";

    // UTILS
    private void generateInventoryDatabase() {
        HashMap<String, Object> inventoryBase = new HashMap<String, Object>();
        inventoryBase.put("items", this.items);
        // inventoryBase.put("categories", this.categories);
        inventoryBase.put("restockDate", this.restockDate);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String InventoryJson = gson.toJson(inventoryBase);

        try (FileWriter writer = new FileWriter(dataLocation)) {
            writer.write(InventoryJson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllItems() {
        Iterator<Item> itemIt = items.iterator();
        while (itemIt.hasNext()) {
            Item thisItem = itemIt.next();
            System.out.println("\n" + thisItem.getItemId() + ". " + thisItem.getName());
            System.out.println("\n");
        }
    }

    public void updateData() {
        HashMap<String, Object> inventoryBase = new HashMap<String, Object>();
        inventoryBase.put("items", this.items);
        // inventoryBase.put("categories", this.categories);
        inventoryBase.put("restockDate", this.restockDate);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String InventoryJson = gson.toJson(inventoryBase);
        try (FileWriter writer = new FileWriter(dataLocation)) {
            writer.write(InventoryJson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem(Item newItem) {
        this.items.add(newItem);
        updateData();
    }

    public void deleteItem(int itemId) {
        // delete item
        ArrayList<Item> items = this.getItems();
        items.remove(itemId - 1);

        // re-index the item IDs
        int len = items.size();
        for (int i = len; i != 0; i--) {
            int currentIndex = i - 1;

            // get current item and change it's id
            Item currentItem = items.get(currentIndex);
            currentItem.setItemId(i);
            items.set(currentIndex, currentItem);
        }

        this.updateData();
    }

    // private Inventory getInventory() throws IOException {
    // Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // Inventory currentInventory;
    // Reader reader = new FileReader(dataLocation);
    // currentInventory = gson.fromJson(reader, Inventory.class);
    // return currentInventory;
    // }

    public void setRestockDate(String newRestockDate) {
        try {
            Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(newRestockDate);
            this.restockDate = newDate;
            this.updateData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GETTERS
    public ArrayList<Item> getItems() {
        return this.items;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getRestockDate() {
        Date restockDate = this.restockDate;
        DateFormat dateFormatter = new SimpleDateFormat("dd / MM / yyyy");
        String formatterRestockDate = dateFormatter.format(restockDate);
        return formatterRestockDate;
    }

    public Inventory() {
        generateInventoryDatabase();
    }
}
