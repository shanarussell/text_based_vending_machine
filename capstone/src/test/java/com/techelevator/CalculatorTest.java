package com.techelevator;

import com.techelevator.caught.SelectionException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculatorTest extends TestCase {
    Calculator testCalculator = new Calculator();

    @Test

    public void testFigureCustomerBalanceAfterBillInput() throws SelectionException {
        //do regular lowercase and uppercase values work
        double customerBalance = 0;
        Assert.assertEquals(1.00, testCalculator.figureCustomerBalanceAfterBillInput("a"), 0);
        Assert.assertEquals(6.00, testCalculator.figureCustomerBalanceAfterBillInput("b"), 0);
        Assert.assertEquals(16.00, testCalculator.figureCustomerBalanceAfterBillInput("c"), 0);
        Assert.assertEquals(17.00, testCalculator.figureCustomerBalanceAfterBillInput("A"), 0);
        Assert.assertEquals(22.00, testCalculator.figureCustomerBalanceAfterBillInput("B"), 0);
        Assert.assertEquals(32.00, testCalculator.figureCustomerBalanceAfterBillInput("C"), 0);


        //checks to see if selectionexception is thrown
        Assertions.assertThrows(SelectionException.class, () ->{
            testCalculator.figureCustomerBalanceAfterBillInput("r");
        });
    }

    @Test
    public void testFigureFeedMoneyValueFromEnterBillsInput() throws SelectionException {

        //do regular lowercase and uppercase values work
        Assert.assertEquals(1.00, testCalculator.figureFeedMoneyValueFromEnterBillsInput("a"), 0);
        Assert.assertEquals(5.00, testCalculator.figureFeedMoneyValueFromEnterBillsInput("b"), 0);
        Assert.assertEquals(10.00, testCalculator.figureFeedMoneyValueFromEnterBillsInput("c"), 0);
        Assert.assertEquals(1.00, testCalculator.figureFeedMoneyValueFromEnterBillsInput("A"), 0);
        Assert.assertEquals(5.00, testCalculator.figureFeedMoneyValueFromEnterBillsInput("B"), 0);
        Assert.assertEquals(10.00, testCalculator.figureFeedMoneyValueFromEnterBillsInput("C"), 0);

        //does it throw a Selection Exception for any other character?
        Assertions.assertThrows(SelectionException.class, () ->{
            testCalculator.figureFeedMoneyValueFromEnterBillsInput("r");
        });
    }

    public void testFigureCustomerBalanceMinusChosenPrice() {
        Assert.assertEquals(9.25, testCalculator.figureCustomerBalanceMinusChosenPrice(10,0.75), 0);
    }

}