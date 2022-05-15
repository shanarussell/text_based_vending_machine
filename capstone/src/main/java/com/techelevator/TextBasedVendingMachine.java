package com.techelevator;

import com.techelevator.caught.InsuffecientFundsException;
import com.techelevator.caught.ItemNotFoundException;
import com.techelevator.caught.OutOfStockException;
import com.techelevator.caught.SelectionException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TextBasedVendingMachine implements VendingMachine {

    private Inventory inventory = new Inventory();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private double customerBalance = 0.00;
    private MachineAudit addToLog = new MachineAudit();
    private Map<String, Integer> salesReportMap = new HashMap<>(); //for keeping track of items sold for the sales report
    SalesReport salesReport = new SalesReport(); //new SalesReport object
    Calculator calculator = new Calculator();




    //this method displays the main menu and asks them to make a choice 1-3 or hidden menu
    @Override
    public void mainMenu() {
        // create scanner and ask customer to choose products
        System.out.println("");
        System.out.println("*** Main Menu ***");
        System.out.println("1) Display vending machine items");
        System.out.println("2) Make a purchase");
        System.out.println("3) Exit");
        System.out.println("Please enter a number: ");

        boolean inputInt = false;

            try {
                Scanner selectProductScanner = new Scanner(System.in);
                String productSelection = selectProductScanner.nextLine();
                //if 1, display products from TextBasedVendingMachine class
                //if 2, go to purchase menu
                //if 3, end program
                //if 4, hidden menu

                if (productSelection.equals("1") || productSelection.equals(2) || productSelection.equals("3") || productSelection.equals("4")){
                    inputInt = true;
                }

                if (productSelection.equals("1")) {
                    displayProducts();  //invokes the displayProducts method below
                    mainMenu(); //go back to this method
                } else if (productSelection.equals("2")) {
                    purchaseMenu();

                } else if (productSelection.equals("3")) {
                    System.out.println("");
                    System.out.println("*** Thank you for using the Vending Machine ***");
                    System.exit(0); //normal termination of jvm

                } else if (productSelection.equals("4")) {
                    System.out.println("You've chosen the hidden sales report");
                    salesReport.readReport(); //go to the generateReport method in the Sales Report class
                } else {
                    throw new SelectionException("Please enter a valid selection");
                }
            } catch (SelectionException e) {
                System.err.println(e.getMessage());
                mainMenu();
            }


    }




    //displays welcome message and choices
    @Override
    public void displayProducts() {
        System.out.println("Products available");
        List<VendingItem> listOfItems = inventory.getVendingItems();
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.print(listOfItems.get(i).getCode() + " | ");
            System.out.print(listOfItems.get(i).getName() + " | ");
            System.out.print("$" + df.format(listOfItems.get(i).getPrice()) + " | ");
            System.out.println("Qty: " + listOfItems.get(i).getQuantity());
        }

    }


    //displays the purchase menu (feed money, select product, finish transaction, current money)
    @Override
    public void purchaseMenu() {

        Boolean inputIsInteger = false;
        do {
            try {
                System.out.println("");
                System.out.println("*** Purchase Menu ***");
                System.out.println("1) Feed Money into machine");
                System.out.println("2) Select a product");
                System.out.println("3) Finish Transaction");
                System.out.println("");
                System.out.println("** Your current balance is: $" + df.format(calculator.getCustomerBalance()) + " **");
                System.out.println("");
                System.out.println("Please enter your selection: ");

                //collect user input, assign to String variable
                Scanner purchaseMenuScanner = new Scanner(System.in);
                String purchaseMenuScannerStringInput = purchaseMenuScanner.nextLine();
            if (purchaseMenuScanner.equals("1") || purchaseMenuScanner.equals(2) || purchaseMenuScanner.equals("3")){
                inputIsInteger = true;
            }
                //Purchase menu choices
                if (purchaseMenuScannerStringInput.equalsIgnoreCase("1")) {
                    displayEnterBillsMessage();
                } else if (purchaseMenuScannerStringInput.equalsIgnoreCase("2")) {
                    selectProduct();
                } else if (purchaseMenuScannerStringInput.equalsIgnoreCase("3")) {
                    finishTransaction();
                } else {
                    throw new SelectionException("Please select a valid menu option");
                }
            } catch (SelectionException e) {
                System.err.println(e.getMessage());
                purchaseMenu();
            }
        }while (inputIsInteger);
    }


    /*
      user selects an item that they want from the list of available product
      shows list of available product
      asks them to pick one
    */
    @Override
    public void selectProduct() {

        //This variable is used to avoid termination of jvm due to exceptions thrown
        boolean doWeNeedToTryAgain = false;
        boolean doesItemExist = false;
        VendingItem chosen = new VendingItem() {
            @Override
            public String getSound() {
                return null;
            }
        };

        do {
            try {
                //generate scanner to receive product selection
                displayProducts();
                List<VendingItem> listOfItems = inventory.getVendingItems();
                Scanner pickProductScanner = new Scanner(System.in);
                System.out.println("");
                System.out.println("Enter the code for the item you'd like");
                String productSelection = pickProductScanner.nextLine();

                //iterate through vending items to check for product code selected
                for (VendingItem item : listOfItems) {
                    if (item.getCode().equalsIgnoreCase(productSelection)) {
                        //assign item to local object variable
                        chosen = item;
                        //we've found the item so no need to try again at this point
                        doWeNeedToTryAgain = false;
                        //it exists!
                        doesItemExist = true;
                    }
                }



                        /*
                        if item doesn't exist prompt customer to input a valid code
                         */
                        if(!doesItemExist){
                            throw new ItemNotFoundException("Please input a valid code to receive an item from the vending machine");
                        }

                            /*
                        if selected item is sold out, customer informed, returned to purchase menu
                        if it's not sold out, subtract 1 from the quantity
                        subtractQuantity method available via VendingItem class
                        */

                        if (chosen.getQuantity() > 0) {
                            chosen.subtractQuantity(1);
                        } else {
                            throw new OutOfStockException("Sorry this item is out of stock, please try again.");
                        }


                        /*
                        check customer balance
                        if customer has the funds, remove price of item from their current balance
                        or insufficientFunds exception is thrown prompting user to enter more money
                         */
                            if (calculator.getCustomerBalance() > chosen.getPrice()) {
                                calculator.figureCustomerBalanceMinusChosenPrice(calculator.getCustomerBalance(), chosen.getPrice());
                            } else {
                                throw new InsuffecientFundsException("Please insert more money to complete transaction");
                            }

                            //we found a valid product dispense it to customer and print item name, cost, and money remaining
                            System.out.println("");
                            System.out.println("*** Item dispensed ***");
                            System.out.println(chosen.getName() + " | Price: $" + df.format(chosen.getPrice()) + " | Current balance: $" + df.format(calculator.getCustomerBalance()));
                            System.out.println(chosen.getSound());

                            //also write it to the log file
                            updateLogFileWithValidItemSelected(chosen.getName(), chosen.getCode(), chosen.getPrice(), calculator.getCustomerBalance());

                            //also add to the map for the sales report
                            if(salesReportMap.containsKey(chosen.getName())){
                                salesReportMap.put(chosen.getName(), salesReportMap.get(chosen.getName())+1);
                            } else {
                                salesReportMap.put(chosen.getName(), 1);
                            }

                            //after dispensing, return to purchase menu
                            purchaseMenu();




            } catch (InsuffecientFundsException e) {
                //print message and return to purchase menu
                System.err.println(e.getMessage());
                purchaseMenu();
            } catch (OutOfStockException f) {
                //print messages
                System.err.println(f.getMessage());
                //return system to start of selectProduct() method. (current method)
                purchaseMenu();
            } catch (ItemNotFoundException g) {
                System.err.println(g.getMessage());
                //return system to start of selectProduct() method. (current method)
                doWeNeedToTryAgain = true;
            }
        }while (doWeNeedToTryAgain) ;
    }

    //asks user to enter bills
    @Override
    public void displayEnterBillsMessage () {
        System.out.println("Please enter amount: (a) $1; (b) $5; (c) $10; (d) stop entering money");
        System.out.println("");
        System.out.println("*** Current balance: $" + df.format(calculator.getCustomerBalance()) + " ***");
        enterBills();
    }

    @Override
    public void updateLogFileWithFeedMoneyValue() throws SelectionException{
        addToLog.logFeedMoney(calculator.getFeedMoneyValue(), calculator.getCustomerBalance()); //updates logFile
        displayEnterBillsMessage();
    }

    @Override
    public void updateLogFileWithValidItemSelected(String name, String code, double price, double customerBalance){
        addToLog.logValidItemSelected(name, code, price, customerBalance);

    }

    @Override
    public void updateLogFileWithChangeGiven(){
        addToLog.logChangeGiven(calculator.getCustomerBalance());
    }



    @Override
    public void enterBills () {
        //log file variable representing customer balance
        double feedMoneyValue = 0;

        try{
            Scanner billsEnteredScanner = new Scanner(System.in);
            String billsEnteredInput = billsEnteredScanner.nextLine();


            //if it's d, exit
            if(billsEnteredInput.equalsIgnoreCase("d")){
                purchaseMenu();
            }

            //call methods from the calculator class that figure out customerBalance and feedMoneyBalance
            calculator.figureCustomerBalanceAfterBillInput(billsEnteredInput);
            calculator.figureFeedMoneyValueFromEnterBillsInput(billsEnteredInput);

            //call method to update the log file
            updateLogFileWithFeedMoneyValue();

            //call method to start over
            displayEnterBillsMessage();

                } catch (SelectionException e){
                    System.err.println(e.getMessage());
                    displayEnterBillsMessage();
                    enterBills();
                }


            }


            //Shows change given to customer
            @Override
            public void displayChangeMessage () {
                //write it to the log file
                updateLogFileWithChangeGiven(); //updates logFile

                //calculates total coins needed for change, returns an array
                calculator.calculateChangeNeeded();
                System.out.println("Quarters: " + calculator.calculateChangeNeeded()[0]);
                System.out.println("Dimes: " + calculator.calculateChangeNeeded()[1]);
                System.out.println("Nickles: " + calculator.calculateChangeNeeded()[2]);
                System.out.println("Pennies: " + calculator.calculateChangeNeeded()[3]);


            }


    /*
    The customer's money is returned using the smallest amount of coins possible
    quarters, dimes, and nickels
     */
            @Override
            public void finishTransaction () {
                System.out.println("");
                System.out.println("** Thanks for your purchase **");
                System.out.println("Balance in Machine: $" + df.format(calculator.getCustomerBalance()));
                System.out.println("Your change is:");
                displayChangeMessage();

                //The machine's current balance must be updated to $0 remaining.
                calculator.setCustomerBalance(0);
                System.out.println("Your current balance is $0");

                //call the generateReport method in the Sales Report class
                salesReport.generateReport(salesReportMap);

                //After completing their purchase, the user is returned to the "Main" menu to continue using the vending machine.
                mainMenu();
            }


        }
