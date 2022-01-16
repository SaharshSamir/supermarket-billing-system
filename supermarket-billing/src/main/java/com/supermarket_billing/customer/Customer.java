package com.supermarket_billing.customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.supermarket_billing.Item.Item;

public class Customer {
    private int customer_id = 0;
    private String name;
    private long phoneNo;
    private float total;
    private ArrayList<Item> monthlyTab;
    private String dataLocation = "supermarket-billing/src/main/java/com/supermarket_billing/data/customer.json";

    // getters
    public int getCustomerId() {
        return this.customer_id;
    }

    public String getName() {
        return this.name;
    }

    public long getPhoneNo() {
        return this.phoneNo;
    }

    public float getTotal() {
        return this.total;
    }

    public ArrayList<Item> getMontlyTab() {
        return this.monthlyTab;
    }

    // setters
    public void setCustomerId(int newCustomerId) {
        this.customer_id = newCustomerId;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPhoneNo(long newPhoneNo) {
        this.phoneNo = newPhoneNo;
    }

    public void setTotal(float newTotal) {
        this.total = newTotal;
    }

    public void setMonthlyTab(ArrayList<Item> newMonthlyTab) {
        this.monthlyTab = newMonthlyTab;
    }

    public void addItemToMonthlyTab(Item item) {
        this.monthlyTab.add(item);
    }

    // utils
    public void addItem(Item newItem) {
        this.total += newItem.getPrice();
        this.monthlyTab.add(newItem);
    }

    public void updateCustomerData() {
        HashMap<String, Object> CustomerBase = new HashMap<String, Object>();
        CustomerBase = new HashMap<String, Object>();
        CustomerBase.put("name", this.name);
        CustomerBase.put("phoneno", this.phoneNo);
        CustomerBase.put("total", this.total);
        CustomerBase.put("monthlytab", this.monthlyTab);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String customerjson = gson.toJson(CustomerBase);

        try (FileWriter writer = new FileWriter(dataLocation)) {
            writer.write(customerjson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // // constructor
    // public Customer(){

    // }
    public Customer() {
        this.name = "";
        this.phoneNo = 0;
        this.total = 0;
    }

    public Customer(int customer_id, String name, long phoneNo, float total, ArrayList<Item> monthlyTab) {
        this.customer_id = customer_id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.total = total;
        this.monthlyTab = monthlyTab;
    }

    @Override
    public String toString() {
        String output = "Customer Id: " + this.customer_id + "\nName: " + this.name + "\nPhone No: " + this.phoneNo
                + "\nTotal: " + this.total + "\nMonthly Tab: " + this.monthlyTab;
        return output;
    }
}
