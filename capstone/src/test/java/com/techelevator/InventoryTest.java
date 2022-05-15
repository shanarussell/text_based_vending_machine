package com.techelevator;

import com.techelevator.vmItems.Candy;
import com.techelevator.vmItems.Chips;
import com.techelevator.vmItems.Drink;
import com.techelevator.vmItems.Gum;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class InventoryTest {

    Inventory inventory = new Inventory();
    List<VendingItem> vendingItems = new ArrayList<>();

    @BeforeEach
    public void createInventoryToTest(){
        vendingItems.add(new Chips("A1", "Potato Crisps", 3.05, "Chip", 5));
        vendingItems.add(new Candy("B1", "Moonpie", 1.80, "Candy", 5));
        vendingItems.add(new Drink("C1", "Cola", 1.25, "Drink", 5));
        vendingItems.add(new Gum("D1", "U-Chews", .85, "Gum", 5));

    }

    @Test
    //is inventory not empty
    public void areVendingItemsCreated(){
        Assertions.assertFalse(vendingItems.isEmpty());
    }

    @Test
    //does Each vending machine item has a Name and a Price
    //does vending item quantity equal 5
    public void doesEachItemHaveNameAndPrice(){
        Assert.assertEquals("Potato Crisps", vendingItems.get(0).getName());
        Assert.assertEquals(3.05, vendingItems.get(0).getPrice(), 0);
        Assert.assertEquals(5, vendingItems.get(0).getQuantity(), 0);

        Assert.assertEquals("Moonpie", vendingItems.get(1).getName());
        Assert.assertEquals(1.80, vendingItems.get(1).getPrice(), 0);
        Assert.assertEquals(5, vendingItems.get(1).getQuantity(), 0);

        Assert.assertEquals("Cola", vendingItems.get(2).getName());
        Assert.assertEquals(1.25, vendingItems.get(2).getPrice(), 0);
        Assert.assertEquals(5, vendingItems.get(2).getQuantity(), 0);

        Assert.assertEquals("U-Chews", vendingItems.get(3).getName());
        Assert.assertEquals(.85, vendingItems.get(3).getPrice(), 0);
        Assert.assertEquals(5, vendingItems.get(3).getQuantity(), 0);
    }




    @Test
    //The vending machine inventory is stocked via an input file when the vending machine starts. - check for file not found
    public void doesFileExist(){
        //will pass if file exists
        File testFile = new File("vendingmachine.csv");
        assertTrue(testFile.exists());
    }

    @Test
    //testing subtractQuantity method from the VendingItem abstract class
    public void testSubtractQuantity(){
        Assert.assertEquals(4, vendingItems.get(0).subtractQuantity(1));
    }

    @Test
    public void testGetSound() {
        Assert.assertEquals("Crunch Crunch, Yum!", vendingItems.get(0).getSound());
        Assert.assertEquals("Munch Munch, Yum!", vendingItems.get(1).getSound());
        Assert.assertEquals("Glug Glug, Yum!", vendingItems.get(2).getSound());
        Assert.assertEquals("Chew Chew, Yum!", vendingItems.get(3).getSound());
    }


}