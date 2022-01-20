package com.supermarket_billing.customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.supermarket_billing.customer.Customer;
import com.supermarket_billing.Item.Item;

public class Customers {
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private int totalCustomers = 0;
    private String dataLocation = "supermarket-billing/src/main/java/com/supermarket_billing/data/customer.json";

    public void updateData() {

        HashMap<String, Object> CustomerBase = new HashMap<String, Object>();

        CustomerBase.put("customers", this.customers);
        CustomerBase.put("total_customers", this.totalCustomers);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String customerjson = gson.toJson(CustomerBase);

        try (FileWriter writer = new FileWriter(dataLocation)) {
            writer.write(customerjson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addItemToMonthlyTab(int cust_id, Item item) {
        Iterator<Customer> customerIt = customers.iterator();
        while (customerIt.hasNext()) {
            Customer currentCustomer = customerIt.next();
            if (currentCustomer.getCustomerId() == cust_id) {
                currentCustomer.addItem(item);
                break;
            }
        }
        updateData();
        customerIt = customers.iterator();
        while (customerIt.hasNext()) {
            System.out.println(customerIt.next());
        }
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
        updateData();
    }

    public ArrayList<Customer> getAllCustomers() {
        return this.customers;
    }

    public void deleteCustomer(int cust_id) {
        customers.removeIf(cust -> (cust.getCustomerId() == cust_id));
        updateData();
    }

    public void prepareBill(int cust_id) {
        Iterator<Customer> customerIt = this.customers.iterator();
        while (customerIt.hasNext()) {
            Customer currentCustomer = customerIt.next();
            if (currentCustomer.getCustomerId() == cust_id) {
                currentCustomer.checkout();
                break;
            }
        }
    }

    // constructor
    public Customers() {
        HashMap<String, Object> CustomerBase = new HashMap<String, Object>();

        CustomerBase.put("customers", this.customers);
        CustomerBase.put("total_customers", this.totalCustomers);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String customerjson = gson.toJson(CustomerBase);

        try (FileWriter writer = new FileWriter(dataLocation)) {
            writer.write(customerjson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
