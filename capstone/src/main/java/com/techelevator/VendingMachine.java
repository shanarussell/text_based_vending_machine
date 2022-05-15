package com.techelevator;

import com.techelevator.caught.SelectionException;

public interface VendingMachine {

    //displays the main menu and asks them to make a choice 1-3 or hidden menu
    void mainMenu() throws SelectionException;

    //choices for main menu

    //gets the items from the Inventory class, which pulls the data from
    // the csv file and lists them
    void displayProducts();

    //displays the purchase menu
    // (feed money, select product, finish transaction, current money)
    void purchaseMenu();

    //user selects an item that they want from the list of available products
    //does it exist?
    //do they have enough money?
    //is it sold out?
    //if everything is valid, update balance, dispense and print
    //return to purchaseMenu()
    void selectProduct();

    //message display to enter money
    void displayEnterBillsMessage();

    //updates the log file when money is fed into machine
    void updateLogFileWithFeedMoneyValue() throws SelectionException;

    //updates the log file when a valid item is selected
    void updateLogFileWithValidItemSelected(String name, String code, double price, double customerBalance);

    //updates the log file when change is given
    void updateLogFileWithChangeGiven();

    //receives the bills entered by the user
    //updates balance
    void enterBills();

    //calculates total coins needed for change
    //displays total change to user
    void displayChangeMessage();

    //prints finish transaction menu
    //calls displayChangeMessage
    //updates customerBalance to 0
    //returns user to main menu
    public void finishTransaction();



}
