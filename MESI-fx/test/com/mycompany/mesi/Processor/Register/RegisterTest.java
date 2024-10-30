package com.mycompany.mesi.Processor.Register;

/**
 * Class to test the functionality of the Register class.
 * This class runs various tests to verify the correct behavior of 
 * methods in the Register class, including value setting, getting,
 * and toString representation.
 * 
 * Tests include:
 * - Initial value check
 * - Setting and retrieving values
 * - Verifying the register ID
 * - Testing the toString method
 */
public class RegisterTest {

    /**
     * Runs all tests for the Register class.
     * Creates a Register instance and tests various functionalities,
     * including getId, getValue, setValue, and toString.
     */
    public void runTests() {
        // Create a Register instance with ID 1
        Register register = new Register(1);

        // Test getValue method (initially should be 0)
        System.out.println("Initial value (expected 0): " + register.getValue());

        // Test setValue method by setting the value to 100
        register.setValue(100);
        System.out.println("Value after setValue(100) (expected 100): " + register.getValue());

        // Test updating the value to another number (200)
        register.setValue(200);
        System.out.println("Value after setValue(200) (expected 200): " + register.getValue());

        // Test getId method to ensure it returns the correct ID
        System.out.println("Register ID (expected 1): " + register.getId());

        // Test toString method for correct string representation
        System.out.println("toString output: " + register.toString());
        
        // Indicate that all tests are complete
        System.out.println("All tests completed.");
    }
}
