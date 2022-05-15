package com.techelevator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    TextBasedVendingMachine testMachine = new TextBasedVendingMachine();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testMainMenu() {
        try{
            String userInput = "r";

            // IMPORTANT: Save the old System.out!
            PrintStream old = System.out;

            // Create a stream to hold the output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(new PrintStream(baos, false, "UTF-8"));

            // IMPORTANT: save old System.in!
            InputStream productSelection = System.in;

            // Set new System.in
            System.setIn(new ByteArrayInputStream(userInput.getBytes()));

            // Calling main method should save main method's output as string literal to baos.
            testMachine.mainMenu();
            // Put things back
            System.out.flush();
            System.setOut(old);

            //Restore
            System.setIn(productSelection);


        }catch(IOException ioe){
            new IOException("i/o problem - test not executed\n");
        }

    }

}