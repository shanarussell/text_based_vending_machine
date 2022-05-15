package com.techelevator;

import com.techelevator.vmItems.Candy;
import com.techelevator.vmItems.Chips;
import com.techelevator.vmItems.Drink;
import com.techelevator.vmItems.Gum;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//this class pulls the inventory items out of the csv file so the main class can display them


public class Inventory {

    private List<VendingItem> vendingItems;

    public Inventory() {

        vendingItems = new ArrayList<>();

        File newFile = new File("vendingmachine.csv");
        Scanner fileScanner;
        try {
            //this reads from vendingmachine.csv
            fileScanner = new Scanner(newFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine(); //reads each line as a string

                /*
                since there are multiple values per line, putting them in an array
                so we can split them up
                 */
                String [] individualItems = line.split("\\|");

                //assigning values based on their position in the array
                String code = individualItems[0];
                String name = individualItems[1];
                double price = Double.parseDouble(individualItems[2]);
                String type = individualItems[3];

                //we always start at 5. Value wasn't in the csv, so assigning it here
                int quantity = 5;

                //add each item into the vendingItems list as a new object, based on their type
                if(type.equals("Chip")) {
                    vendingItems.add(new Chips(code, name, price, type, quantity));
                } else if (type.equals("Candy")) {
                    vendingItems.add(new Candy(code, name, price, type, quantity));
                } else if (type.equals("Drink")) {
                    vendingItems.add(new Drink(code, name, price, type, quantity));
                } else if (type.equals("Gum")) {
                    vendingItems.add(new Gum(code, name, price, type, quantity));
                }

            }

        //in case the inventory file is not found
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found");
        }
    }


    //getters and setters
    public List<VendingItem> getVendingItems() {
        return vendingItems;
    }

}
