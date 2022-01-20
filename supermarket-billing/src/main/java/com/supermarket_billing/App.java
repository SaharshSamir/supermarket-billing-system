package com.supermarket_billing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ListIterator;

import com.supermarket_billing.Inventory.Inventory;
import com.supermarket_billing.Item.Item;
import com.supermarket_billing.cart.Cart;
import com.supermarket_billing.customer.Customer;
import com.supermarket_billing.customer.Customers;
// import com.supermarket_billing.Item.Item.Categories;

// import com.supermarket_billing.utils.JavaUtils;

/**
 * Hello world!
 */
public final class App {
    // global vars
    static Scanner sc = new Scanner(System.in);
    static Inventory inventory = new Inventory();
    static Item item = new Item();
    static Customers customers = new Customers();
    static Cart cart = new Cart();

    public static void firstMenu() {
        System.out.println("1. Item functions");
        System.out.println("2. Customer functions");
        System.out.println("3. Cart functions");
        System.out.println("pick one option: ");
    }

    public static void checkout(ArrayList<Item> items) {
        Iterator<Item> itemIt = items.iterator();
        float total = 0;
        System.out.println("");
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        System.out.printf("%5s %10s %10s %8s %20s %17s", "ITEM", "PRICE", "CATEGORY", "DISCOUNT", "TAX",
                "AMOUNT TO PAY");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        // iterates over the list
        while (itemIt.hasNext()) {
            Item currentItem = itemIt.next();
            float mrp = currentItem.getPrice();
            // tax amount is tax% of mrp
            float taxAmount = currentItem.getTax() / 100 * currentItem.getPrice();
            float discountedAmount = currentItem.getDiscount() / 100 * currentItem.getPrice();
            float amountToPay = (mrp + taxAmount) - discountedAmount;
            total += amountToPay;
            System.out.format("%7s %14s %7s %10s %25s %13s", currentItem.getName(), currentItem.getPrice(),
                    currentItem.getCategory(),
                    currentItem.getDiscount(), currentItem.getTax(), amountToPay);
            System.out.println();
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------");
        System.out.printf("%5s %10s %10s %8s %20s %17s", "TOTAL", "     ", "        ", "        ", "   ",
                total);
        System.out.println(
                "----------------------------------------------------------------------------------------------");
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
                        System.out.println(currentItem);
                        // System.out.println("Item id: " + currentItem.getItemId() + "\n");
                        // System.out.println("Name: " + currentItem.getName() + "\n");
                        // System.out.println("Price: " + currentItem.getPrice() + "\n");
                        // System.out.println("Category: " + currentItem.getCategory() + "\n");
                        // System.out.println("\n");
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

                // get item discount
                System.out.println("Enter item discount: ");
                float newItemDiscount = sc.nextFloat();

                // get item tax
                System.out.println("Enter item tax: ");
                float newItemTax = sc.nextFloat();

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
                Item newItem = new Item(newItemName, newItemCategory, newItemPrice, itemId + 1, newItemDiscount,
                        newItemTax);
                inventory.addItem(newItem);
                System.out.println("New item was added: ");
                System.out.println(newItem);
                break;

            // edit an item
            case 4: {

                items = inventory.getItems();

                while (itemIt.hasNext()) {
                    Item currentItem = itemIt.next();
                    System.out.println(currentItem.getItemId() + " " + currentItem.getName());
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
                System.out.println("1.Name \n2.Price \n3.Category \n4.Discount \n5.Tax");
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
                    case 4: {

                        System.out.println("Enter the new item Discount: ");
                        float newDiscount = sc.nextFloat();
                        itemToEdit.setDiscount(newDiscount);
                        inventory.updateData();
                        break;
                    }
                    case 5: {
                        System.out.println("Enter the item tax :");
                        float newTax = sc.nextFloat();
                        itemToEdit.setTax(newTax);
                        inventory.updateData();
                        break;
                    }
                    default:
                        System.out.println("invalid input.");
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
                System.out.println("invalid input.");
                break;
        }

    }

    public static void handleCustomerFunctions() {
        ArrayList<String> categories = inventory.getCategories();
        Iterator<String> categoriesIterator = categories.iterator();
        ArrayList<Item> items = inventory.getItems();
        Iterator<Item> itemIt = items.iterator();
        // display customer menu
        System.out.println("\nSelect an option: ");
        System.out.println("1.Show all registered customers");
        System.out.println("2.Show a registered customer");
        System.out.println("3.Add a registered customer");
        System.out.println("4.Add to Monthly Tab");
        System.out.println("5.Delete a registed customer");
        System.out.println("6.Prepare bill");
        System.out.println("Enter your choice[1-5]: ");
        int ans = sc.nextInt();

        switch (ans) {
            // Show all registered customers
            case 1: {
                ArrayList<Customer> allCustomers = customers.getAllCustomers();
                Iterator<Customer> customerIt = allCustomers.iterator();
                while (customerIt.hasNext()) {
                    Customer currentCustomer = customerIt.next();
                    System.out.println(currentCustomer.getName());
                }
                break;
            }
            // Show a registered customer
            case 2: {
                ArrayList<Customer> allCustomers = customers.getAllCustomers();
                Iterator<Customer> customersIt = allCustomers.iterator();
                System.out.println("Enter the id of the customer that you wish to see: ");
                int cust_id = sc.nextInt();
                while (customersIt.hasNext()) {
                    Customer currentCustomer = customersIt.next();
                    if (currentCustomer.getCustomerId() == cust_id) {
                        System.out.println(currentCustomer);
                        break;
                    }
                }
                break;
            }
            // Add a customer
            case 3: {
                // Get new customer details
                System.out.println("Enter customer name: ");
                String name = sc.next();
                System.out.println("Enter customer phone no: ");
                long phoneNo = sc.nextLong();
                ArrayList<Customer> allCustomers = customers.getAllCustomers();
                int cust_id = allCustomers.size() + 1;
                ArrayList<Item> monthlyTab = new ArrayList<Item>();
                Customer newCustomer = new Customer(cust_id, name, phoneNo, 0, monthlyTab);
                customers.addCustomer(newCustomer);
                System.out.println("New customer added: \n" + newCustomer);
                break;
            }
            // Add item to monthly tab
            case 4: {
                ArrayList<Customer> allCustomers = customers.getAllCustomers();
                Iterator<Customer> customersIt = allCustomers.iterator();
                System.out.println("Enter the id of the customer: ");
                int cust_id = sc.nextInt();
                // Customer thisCustomer = null;
                // while (customersIt.hasNext()) {
                // Customer currentCustomer = customersIt.next();
                // if (currentCustomer.getCustomerId() == cust_id) {
                // thisCustomer = currentCustomer;
                // break;
                // }
                // }
                // if (thisCustomer == null) {
                // break;
                // }
                // items = inventory.getItems();
                // itemIt = items.iterator();
                while (itemIt.hasNext()) {
                    Item thisItem = itemIt.next();
                    System.out.println("\n" + thisItem.getItemId() + ". " + thisItem.getName());
                    System.out.println("\n");
                }
                System.out.println("Enter item id: ");
                int item_id = sc.nextInt();
                itemIt = items.iterator();
                while (itemIt.hasNext()) {
                    Item currentItem = itemIt.next();
                    System.out.println(currentItem);
                    if (currentItem.getItemId() == item_id) {
                        customers.addItemToMonthlyTab(cust_id, currentItem);
                    }
                }

            }
            // Delete a registered customer
            case 5: {
                System.out.println("Enter the id of the customer: ");
                int cust_id = sc.nextInt();

                customers.deleteCustomer(cust_id);
                break;
            }
            // Prepare the bill
            case 6: {
                System.out.println("Enter the id of the customer: ");
                int cust_id = sc.nextInt();
            }
            default:
                break;
        }

    }

    public static void handleCartFunctions() {
        ArrayList<Item> items = inventory.getItems();
        System.out.println("1. Show all items in a cart");
        System.out.println("2. Add items to cart");
        System.out.println("3. Remove an item from cart");
        System.out.println("4. Clear cart");
        System.out.println("5. Checkout ");

        System.out.println("Enter your choice: ");
        int ans = sc.nextInt();

        switch (ans) {
            // Show all items in a cart
            case 1: {
                cart.showAllItems();
                break;
            }
            // Add items to cart
            case 2: {
                inventory.showAllItems();
                System.out.println("Enter the id of the item you want to add to the cart: ");
                int itemId = sc.nextInt();
                Iterator<Item> itemIt = items.iterator();
                while (itemIt.hasNext()) {
                    Item currentItem = itemIt.next();
                    if (currentItem.getItemId() == itemId) {
                        cart.addItemToCart(currentItem);
                        break;
                    }
                }
                break;
            }
            // Remove an item from cart
            case 3: {
                System.out.println("Enter the id of the item you want to remove: ");
                int itemId = sc.nextInt();
                Iterator<Item> itemIt = items.iterator();
                while (itemIt.hasNext()) {
                    Item currentItem = itemIt.next();

                    if (currentItem.getItemId() == itemId) {
                        cart.deleteItem(item);
                        break;
                    }
                }
                break;
            }
            // Clear cart
            case 4: {
                cart.clearCart();
                break;
            }
            // checkout
            case 5: {
                checkout(items);
                break;
            }
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
                case 1: {
                    handleInventoryFunctions();
                    break;
                }
                case 2: {
                    handleCustomerFunctions();
                    break;
                }
                case 3: {
                    handleCartFunctions();
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
