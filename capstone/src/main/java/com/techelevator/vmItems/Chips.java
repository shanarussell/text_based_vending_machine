package com.techelevator.vmItems;

import com.techelevator.VendingItem;

public class Chips extends VendingItem {


    public Chips(String code, String name, double price, String type, int quantity) {
        super(code, name, price, type, quantity);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getSound() {
        // TODO Auto-generated method stub
        return "Crunch Crunch, Yum!";
    }
}
