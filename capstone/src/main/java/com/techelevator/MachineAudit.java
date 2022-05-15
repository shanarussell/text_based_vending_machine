package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MachineAudit {

    double feedMoneyValue;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    File logFile = new File("logFile.txt");
    Calculator calculator = new Calculator();

    //format the date and time
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    LocalDateTime now = LocalDateTime.now();

    //constructors
    public MachineAudit() {
    }

    public MachineAudit(double feedMoneyValue) {
        this.feedMoneyValue = feedMoneyValue;
    }

    //writes to the logFile each time money is fed into machine
    public void logFeedMoney(double feedMoneyValue, double customerBalance){

        //Open file for appending
        try(PrintWriter logSales = new PrintWriter(new FileOutputStream(logFile, true))){
            logSales.println(dtf.format(now) + " FEED MONEY: $"+ df.format(feedMoneyValue) + " $" + df.format(customerBalance));

        } catch (FileNotFoundException e){
            System.err.println("Cannot open logFile for writing");
        }

    }

    //writes to the logFile each time a valid item is selected
    public void logValidItemSelected(String chosenName, String chosenCode, double chosenPrice, double customerBalance){

        //Open file for appending
        try(PrintWriter logSales = new PrintWriter(new FileOutputStream(logFile, true))){
            logSales.println(dtf.format(now) + " " + chosenName + " " + chosenCode + " $" + df.format(customerBalance + chosenPrice) + " $" + df.format(customerBalance));

        } catch (FileNotFoundException e){
            System.err.println("Cannot open logFile for writing");
        }

    }


    //writes to the logFile each time change is given
    public void logChangeGiven(double customerBalance){

        //Open file for appending
        try(PrintWriter logSales = new PrintWriter(new FileOutputStream(logFile, true))){
            logSales.println(dtf.format(now) + " GIVE CHANGE: $" + df.format(customerBalance) + " $0.00");

        } catch (FileNotFoundException e){
            System.err.println("Cannot open logFile for writing");
        }

    }


}
