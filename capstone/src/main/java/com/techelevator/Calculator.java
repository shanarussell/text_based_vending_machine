package com.techelevator;

import com.techelevator.caught.SelectionException;

import java.text.DecimalFormat;

public class Calculator {
    double customerBalance;
    double feedMoneyValue;

    // constructor
    public Calculator(){

    }

    //computes the customer balance after user inputs bills
    public double figureCustomerBalanceAfterBillInput(String billsEnteredInput) throws SelectionException {
        if (billsEnteredInput.equalsIgnoreCase("a")) {
            this.customerBalance = customerBalance;
            return customerBalance += 1.0;
        } else if (billsEnteredInput.equalsIgnoreCase("b")) {
            this.customerBalance = customerBalance;
            return customerBalance += 5.0;
        } else if (billsEnteredInput.equalsIgnoreCase("c")) {
            this.customerBalance = customerBalance;
            return customerBalance += 10.0;
        } else {
            throw new SelectionException("Please select a valid amount");
        }
    }

    public double figureFeedMoneyValueFromEnterBillsInput(String billsEnteredInput) throws SelectionException{
        if (billsEnteredInput.equalsIgnoreCase("a")) {
            this.feedMoneyValue = feedMoneyValue;
            return feedMoneyValue = 1.00;
        } else if (billsEnteredInput.equalsIgnoreCase("b")) {
            this.feedMoneyValue = feedMoneyValue;
            return feedMoneyValue = 5.00;
        } else if (billsEnteredInput.equalsIgnoreCase("c")) {
            this.feedMoneyValue = feedMoneyValue;
            return feedMoneyValue = 10.00; //updates logFile
        } else {
            throw new SelectionException("Please enter a valid amount");
        }

    }

    public double figureCustomerBalanceMinusChosenPrice(double customerBalance, double chosenPrice){
        double newBalance = customerBalance - chosenPrice;
        this.customerBalance = newBalance;
        return newBalance;
    }

    //calculates the amount of changed needed in coins
    //is called from the displayChangeMessage of TBVM
    public int[] calculateChangeNeeded(){
        int cents = (int) (getCustomerBalance() * 100);
        int numQuarters = cents / 25;
        int numDimes = (cents % 25) / 10;
        int numNickels = ((cents % 25) % 10) / 5;
        int numPennies = ((cents % 25) % 10) % 5;
        return new int[] {numQuarters, numDimes, numNickels, numPennies};

    }

    //getters and setters

    public double getCustomerBalance() {
        return this.customerBalance;
    }

    public void setCustomerBalance(double customerBalance) {
        this.customerBalance = customerBalance;
    }

    public double getFeedMoneyValue() {
        return feedMoneyValue;
    }

}
