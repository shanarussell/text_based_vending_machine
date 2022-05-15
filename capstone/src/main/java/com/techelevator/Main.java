package com.techelevator;

import com.techelevator.caught.SelectionException;

public class Main {
    public static void main(String[] args) throws SelectionException {

        //welcome message
        System.out.println("******************************");
        System.out.println("Welcome to the Vending Machine");
        System.out.println("******************************");
        System.out.println();

        //Make a new VendingMachine object
        VendingMachine vendingMachine = new TextBasedVendingMachine();

        //call the main menu method from the TextBasedVendingMachine class
        vendingMachine.mainMenu();

    }
}
