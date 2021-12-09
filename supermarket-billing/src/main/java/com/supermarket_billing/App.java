package com.supermarket_billing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

import com.supermarket_billing.Inventory.Inventory;
import com.supermarket_billing.Item.Item;
// import com.supermarket_billing.Item.Item.Categories;

import com.supermarket_billing.utils.JavaUtils;

/**
 * Hello world!
 */
public final class App {
    // global vars
    static Scanner sc = new Scanner(System.in);
    static Inventory inventory = new Inventory();
    static JavaUtils utils = new JavaUtils();

    public static void firstMenu() {
        System.out.println("1. Item functions");
        System.out.println("2. Customer functions");
        System.out.println("3. Cart functions");
        System.out.println("pick one option: ");
    }

    public static void handleInventoryFunctions() {
        ArrayList<Item> items = new ArrayList<Item>();
        Iterator<Item> itemIt = items.iterator();

        // ArrayList<Categories> categories = new ArrayList<Categories>();
        // Iterator<Categories> categoriesIt = categories.iterator();

        // Display menu
        System.out.println("---ITEMS---");
        System.out.println("1. Show all items in iventory");
        System.out.println("2. Show item detail");
        System.out.println("3. Add an item");
        System.out.println("4. Edit an item");
        System.out.println("5. Delete an item");
        System.out.println("---CATEGOIRES---");
        System.out.println("6. Show all categories");
        System.out.println("7. Delete a category");
        System.out.println("---RESTOCK DATE---");
        System.out.println("8. Show restock date");
        System.out.println("9. Edit restock date");
        // Get input
        System.out.println("\n Pick an option[1-9]: ");
        int key = sc.nextInt();

        // handle input
        switch (key) {
            // Show all items in inventory
            case 1:
                items = inventory.getItems();
                itemIt = items.iterator();
                while (itemIt.hasNext()) {
                    System.out.println(itemIt.next().getName());
                    System.out.println("\n");
                }
                break;

            // show item detail
            case 2:
                items = inventory.getItems();
                itemIt = items.iterator();
                System.out.println("Enter item id: ");
                int item_id = sc.nextInt();
                while (itemIt.hasNext()) {
                    Item currentItem = itemIt.next();

                    if (currentItem.getItemId() == item_id) {
                        System.out.println("");
                    }
                }
                // add an item
            case 3:
                // get Item name
                System.out.println("Enter item name: ");
                String newItemName = sc.next();

                // get Item price
                System.out.println("Enter item price: ");
                float newItemPrice = sc.nextFloat();

                // get Item category
                System.out.println("Pick a category for the item: ");
                // Categories catArr[] = Categories.values();
                // int count = 1;
                // for (Categories cat : catArr) {
                // System.out.println(count++ + "." + cat + "\n");
                // }
                int catAns = sc.nextInt();
                // Categories newItemCategory = Categories.values()[catAns - 1];
                // String[] categoryNames = utils.getNames(Categories.class);

                // building item object
                int itemId = inventory.getItems().size();
                // Item newItem = new Item(newItemName, newItemCategory, newItemPrice, itemId +
                // 1);
                // inventory.addItem(newItem);
                // System.out.println("New item was added: ");
                // System.out.println(newItem);
                break;

            // edit an item
            case 4:
                items = inventory.getItems();

                while (itemIt.hasNext()) {
                    System.out.println(itemIt.next().getName());
                    System.out.println("\n");
                }
                System.out.println("Enter the name of the item you want to edit: ");
                String itemNameToEdit = sc.next();
                itemIt = items.iterator();
                Item itemToEdit = new Item();
                while (itemIt.hasNext()) {
                    if (itemIt.next().getName() == itemNameToEdit) {
                        itemToEdit = itemIt.next();
                    }
                }
                System.out.println(itemToEdit);
                System.out.println("What field do you want to edit?[1-3] \n");
                System.out.println("1.Name \n 2.price \n 3.category");
                int opt = sc.nextInt();
                switch (opt) {
                    case 1:
                        System.out.println("Enter the new name: ");
                        String newName = sc.next();
                        itemToEdit.setName(newName);
                        inventory.updateData();
                        break;
                    case 2:
                        System.out.println("Enter the new price: ");
                        float newPrice = sc.nextFloat();
                        itemToEdit.setPrice(newPrice);
                        inventory.updateData();
                        break;
                    case 3:
                        // System.out.println("Pick a category for the item: ");
                        // catArr = Categories.values();
                        // count = 1;
                        // for (Categories cat : catArr) {
                        // System.out.println(count++ + "." + cat + "\n");
                        // }
                        // catAns = sc.nextInt();
                        // Categories newCategory = Categories.values()[catAns - 1];
                        // itemToEdit.setCategory(newCategory);
                        // inventory.updateData();
                        break;
                    default:
                        break;
                }

                break;

            // delete an item
            case 5:
                break;

            // show all categories
            case 6:
                break;

            // delete a category
            case 7:
                break;

            // show restock date
            case 8:
                break;

            // edit restock date
            case 9:
                break;

            default:
                break;
        }

    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */

    public static void main(String[] args) throws IOException {

        char res = 'y';
        do {
            firstMenu();
            int key;
            key = sc.nextInt();
            switch (key) {
                case 1:
                    handleInventoryFunctions();
                    break;

                default:
                    break;
            }
            System.out.println("Do you want to continue?");
            res = sc.next().charAt(0);
        } while (res == 'y');

        // Item apple = new Item("Apple", Categories.PRODUCE, 20);
        // Item banana = new Item("banana", Categories.PRODUCE, 10);
        // Inventory currentInventory = newInventory.getInventory();
        // System.out.println("Before adding a new item : \n");
        // ArrayList<Item> items = currentInventory.getItems();
        // newInventory.addItem(apple);
        // newInventory.addItem(banana);
        // currentInventory = newInventory.getInventory();
        // System.out.println("After adding a new item : \n");
        // ArrayList<Item> newItems = newInventory.getItems();
        sc.close();
    }
}
