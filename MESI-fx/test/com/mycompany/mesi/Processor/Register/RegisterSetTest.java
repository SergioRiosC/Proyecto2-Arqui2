package com.mycompany.mesi.Processor.Register;

/**
 * Class to test the functionality of the RegisterSet class.
 * This class runs various tests to verify the correct behavior of 
 * methods in the RegisterSet class, including setting and getting values,
 * validating index boundaries, and testing the default constructor.
 * 
 * Tests include:
 * - Initialization with default and custom sizes
 * - Setting and retrieving values for registers
 * - Boundary checks for valid and invalid register indices
 * - String representation of the RegisterSet
 */
public class RegisterSetTest {

    /**
     * Runs all tests for the RegisterSet class.
     * Creates instances of RegisterSet and tests various functionalities,
     * including setting and getting values, validating default size, and 
     * checking the string representation.
     */
    public void runTests() {
        // Test the default constructor (should create 4 registers)
        RegisterSet defaultRegisterSet = new RegisterSet();
        System.out.println("Default RegisterSet created with 4 registers:");
        System.out.println(defaultRegisterSet.toString());

        // Test the custom constructor with a specified number of registers
        RegisterSet customRegisterSet = new RegisterSet(6);
        System.out.println("Custom RegisterSet created with 6 registers:");
        System.out.println(customRegisterSet.toString());

        // Test setting and getting values for registers in the default set
        System.out.println("Setting value 100 in register 0 of the default RegisterSet.");
        defaultRegisterSet.setValue(0, 100);
        System.out.println("Value in register 0 (expected 100): " + defaultRegisterSet.getValue(0));

        System.out.println("Setting value 200 in register 1 of the default RegisterSet.");
        defaultRegisterSet.setValue(1, 200);
        System.out.println("Value in register 1 (expected 200): " + defaultRegisterSet.getValue(1));

        // Test setting and getting values for registers in the custom set
        System.out.println("Setting value 300 in register 2 of the custom RegisterSet.");
        customRegisterSet.setValue(2, 300);
        System.out.println("Value in register 2 (expected 300): " + customRegisterSet.getValue(2));

        System.out.println("Setting value 400 in register 5 of the custom RegisterSet.");
        customRegisterSet.setValue(5, 400);
        System.out.println("Value in register 5 (expected 400): " + customRegisterSet.getValue(5));

        // Test boundary check - invalid index access
        try {
            System.out.println("Attempting to access invalid register index -1:");
            defaultRegisterSet.getValue(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to access invalid register index 4 in default RegisterSet:");
            defaultRegisterSet.getValue(4); // Should fail, as default RegisterSet has indices 0-3
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Finalización de la prueba
        System.out.println("All RegisterSet tests completed.");
    }
}
