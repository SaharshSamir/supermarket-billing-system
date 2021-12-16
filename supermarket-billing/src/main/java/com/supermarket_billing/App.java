package com.supermarket_billing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ListIterator;

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
    static Item item = new Item();

    public static void firstMenu() {
        System.out.println("1. Item functions");
        System.out.println("2. Customer functions");
        System.out.println("3. Cart functions");
        System.out.println("pick one option: ");
    }

    public static void handleInventoryFunctions() {
        ArrayList<Item> items = new ArrayList<Item>();
        Iterator<Item> itemIt = items.iterator();
        ArrayList<String> categories = inventory.getCategories();

        Iterator<String> categoriesIterator = categories.iterator();
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
        System.out.println("---RESTOCK DATE---");
        System.out.println("7. Show restock date");
        System.out.println("8. Edit restock date");
        // Get input
        System.out.println("\n Pick an option[1-8]: \n");
        int key = sc.nextInt();

        // handle input
        switch (key) {
            // Show all items in inventory
            case 1:
                items = inventory.getItems();
                itemIt = items.iterator();
                while (itemIt.hasNext()) {
                    Item thisItem = itemIt.next();
                    System.out.println("\n" + thisItem.getItemId() + ". " + thisItem.getName());
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
                        System.out.println("\n");
                        System.out.println("Item id: " + currentItem.getItemId() + "\n");
                        System.out.println("Name: " + currentItem.getName() + "\n");
                        System.out.println("Price: " + currentItem.getPrice() + "\n");
                        System.out.println("Category: " + currentItem.getCategory() + "\n");
                        System.out.println("\n");
                    }
                }
                break;
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
                int count = 1;
                // for (Categories cat : catArr) {
                // System.out.println(count++ + "." + cat + "\n");
                // }
                while (categoriesIterator.hasNext()) {
                    String cat = categoriesIterator.next();
                    System.out.println(count++ + "." + cat + "\n");
                }
                int catAns = sc.nextInt();
                String newItemCategory = categories.get(catAns - 1);
                // String[] categoryNames = utils.getNames(Categories.class);

                // building item object
                int itemId = inventory.getItems().size();
                Item newItem = new Item(newItemName, newItemCategory, newItemPrice, itemId + 1);
                inventory.addItem(newItem);
                System.out.println("New item was added: ");
                System.out.println(newItem);
                break;

            // edit an item
            case 4: {

                items = inventory.getItems();

                while (itemIt.hasNext()) {
                    System.out.println(itemIt.next().getName());
                    System.out.println("\n");
                }
                System.out.println("Enter the id of the item you want to edit: ");
                int itemIdToEdit = sc.nextInt();
                itemIt = items.iterator();
                Item itemToEdit = new Item();
                while (itemIt.hasNext()) {
                    Item thisItem = itemIt.next();
                    if (thisItem.getItemId() == itemIdToEdit) {
                        itemToEdit = thisItem;
                        System.out.println(itemToEdit);
                    }
                }
                System.out.println("What field do you want to edit?[1-3] \n");
                System.out.println("1.Name \n2.price \n3.category");
                int opt = sc.nextInt();
                switch (opt) {
                    // edit Item Name
                    case 1: {
                        System.out.println("Enter the new name: ");
                        String newName = sc.next();
                        itemToEdit.setName(newName);
                        inventory.updateData();
                        break;
                    }
                    // edit Item Price
                    case 2: {
                        System.out.println("Enter the new price: ");
                        float newPrice = sc.nextFloat();
                        itemToEdit.setPrice(newPrice);
                        inventory.updateData();
                        break;
                    }
                    // edit Item Category
                    case 3: {
                        System.out.println("Pick a category for the item: ");
                        // catArr = Categories.values();
                        count = 1;
                        while (categoriesIterator.hasNext()) {
                            String cat = categoriesIterator.next();
                            System.out.println(count++ + "." + cat + "\n");
                        }

                        catAns = sc.nextInt();
                        String newCategory = categories.get(catAns - 1);
                        itemToEdit.setCategory(newCategory);
                        inventory.updateData();
                        break;
                    }
                    default:
                        break;
                }
                break;
            }

            // delete an item
            case 5: {
                System.out.println("Enter the id of the item you want to delete: ");
                int itemToDeleteId = sc.nextInt();
                inventory.deleteItem(itemToDeleteId);
                break;
            }

            // show all categories
            case 6: {
                ListIterator<String> categoriesIt = categories.listIterator();
                while (categoriesIt.hasNext()) {
                    int idx = categoriesIt.nextIndex();
                    String currentCategory = categoriesIt.next();
                    System.out.println(idx + 1 + " " + currentCategory);
                }
                break;
            }

            // delete a category
            // case 7:{
            // System.out.println("Enter the id of the category you want to delete: ");
            // int catId = sc.nextInt();
            // ListIterator<String> categoriesIt = categories.listIterator();
            // while(categoriesIt.hasNext()){
            // int idToCheck = catId-1;
            // int currentCatId = categoriesIt.nextIndex();
            // if(idToCheck == currentCatId){
            // String deletedCatcategories.remove(currentCatId);
            // }
            // }
            // break;
            // }

            // show restock date
            case 7: {
                // System.out.println("current restock date: " + inventory.);
                String restockDate = inventory.getRestockDate();
                System.out.println("Restock date: " + restockDate);
                break;
            }

            // edit restock date
            case 8: {
                System.out.println("Enter the new restock date ['dd/MM/yyyy']: ");
                String newRestockDate = sc.next();
                inventory.setRestockDate(newRestockDate);
                System.out.println("Inventory restock date updated.");
                break;
            }

            default:
                break;
        }

    }

    public static void handleCustomerFunctions() {

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
                case 1: {
                    handleInventoryFunctions();
                    break;
                }
                case 2: {
                    handleCustomerFunctions();
                }

                default:
                    break;
            }
            System.out.println("Do you want to continue?");
            res = sc.next().charAt(0);
        } while (res == 'y');

        sc.close();
    }
}
