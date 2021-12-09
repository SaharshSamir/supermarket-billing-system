package com.supermarket_billing.Inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

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
    private static String dataLocation = "D:\\codes\\supermarket-billing-java\\supermarket_billing\\supermarket-billing\\src\\main\\java\\com\\supermarket_billing\\data\\inventory.json";

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

    public Inventory getInventory() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Inventory currentInventory;
        Reader reader = new FileReader(dataLocation);
        currentInventory = gson.fromJson(reader, Inventory.class);
        return currentInventory;
    }

    // GETTERS
    public ArrayList<Item> getItems() {
        return this.items;
    }

    public Inventory() {
        generateInventoryDatabase();
    }
}

// public class Inventory {
// // PRIVATE
// private Item[] items;
// private Categories[] categories;
// private Date restockDate;
// private static String dataLocation =
// "D:\\codes\\supermarket-billing-java\\supermarket_billing\\supermarket-billing\\src\\main\\java\\com\\supermarket_billing\\data\\inventory.json";

// // UTILS
// static JSONObject generateInventoryJson() {
// HashMap<Object, Object> rootObjectDetails = new HashMap<Object, Object>();

// // intializing items list
// ArrayList<Item> itemsList = new ArrayList<Item>();
// rootObjectDetails.put("items", itemsList);

// // intializing categories list
// ArrayList<String> categotiesList = new ArrayList<String>();
// rootObjectDetails.put("categories", categotiesList);

// // initializing restock date key
// rootObjectDetails.put("restockDate", "");

// JSONObject rootObject = new JSONObject(rootObjectDetails);
// return rootObject;
// }

// private static JSONObject getCurrentData() {

// JSONParser parser = new JSONParser();
// JSONObject data = new JSONObject();
// try (Reader reader = new FileReader(dataLocation)) {
// data = (JSONObject) parser.parse(reader);
// } catch (IOException e) {
// e.printStackTrace();
// } catch (ParseException e) {
// e.printStackTrace();
// }
// return data;
// }

// // PUBLIC
// // ------GETTERS------
// public Item[] getItems() {
// return this.items;
// }

// public Categories[] getCategories() {
// return this.categories;
// }

// public Date getRestockDate() {
// return this.restockDate;
// }

// public JSONObject getInventory() {
// JSONParser parser = new JSONParser();
// JSONObject data = new JSONObject();
// try (Reader reader = new FileReader(dataLocation)) {
// data = (JSONObject) parser.parse(reader);
// } catch (IOException e) {
// e.printStackTrace();
// } catch (ParseException e) {
// e.printStackTrace();
// }
// return data;
// }

// // ------SETTERS------
// public void addItem(Item newItem) {
// JSONObject inventory = getCurrentData();
// JSONArray items = (JSONArray) inventory.get("items");
// items.add(newItem);
// // inventory.getClass().
// System.out.println("From add item: \n");
// System.out.println(items);
// }

// // CONSTRUCTOR
// public Inventory() {
// JSONObject inventoryJson = generateInventoryJson();

// try (FileWriter inventoryFile = new FileWriter(dataLocation)) {
// inventoryFile.write(inventoryJson.toJSONString());
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// }