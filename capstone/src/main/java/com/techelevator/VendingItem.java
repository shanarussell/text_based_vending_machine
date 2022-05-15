package com.techelevator;

import java.text.DecimalFormat;

public abstract class VendingItem {
    private String name;
    private double price;
    private String code;
    private String type;
    private int quantity;
    private static final DecimalFormat df = new DecimalFormat("0.00"); //used to format some of the $ decimals


    //constructor
    public VendingItem (String code, String name, double price, String type, int quantity) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.type = type;
        this.quantity = quantity;

    }
    public VendingItem(){

    }

    //getters and setters
    public String getCode() {
        return code;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int subtractQuantity(int change){
        return quantity-= change;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getSound();

}
