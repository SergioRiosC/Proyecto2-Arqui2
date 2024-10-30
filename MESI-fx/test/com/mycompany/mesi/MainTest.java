package com.mycompany.mesi;

import com.mycompany.mesi.Memory.MemoryTest;
import com.mycompany.mesi.Processor.Cache.CacheLineTest;
import com.mycompany.mesi.Processor.Cache.CacheTest;
import com.mycompany.mesi.Processor.Register.RegisterTest;
import com.mycompany.mesi.Processor.Register.RegisterSetTest;
import com.mycompany.mesi.Processor.InstructionMemory.InstructionMemoryTest;

/**
 * MainTest class to execute test cases for different components in the system.
 * This class serves as an entry point to run tests for various classes.
 * Currently, it runs tests for the Register, RegisterSet, and InstructionMemory classes.
 */
public class MainTest {
    
    /**
     * Executes tests for the Register class.
     * This method creates an instance of RegisterTest and calls its runTests() method
     * to verify the correct behavior of the Register class.
     */
    public static void testRegister() {
        System.out.println("Running Register tests...");
        
        // Instantiate RegisterTest and run its tests
        RegisterTest registerTest = new RegisterTest();
        registerTest.runTests();
        
        System.out.println("Register tests finished.\n");
    }

    /**
     * Executes tests for the RegisterSet class.
     * This method creates an instance of RegisterSetTest and calls its runTests() method
     * to verify the correct behavior of the RegisterSet class.
     */
    public static void testRegisterSet() {
        System.out.println("Running RegisterSet tests...");
        
        // Instantiate RegisterSetTest and run its tests
        RegisterSetTest registerSetTest = new RegisterSetTest();
        registerSetTest.runTests();
        
        System.out.println("RegisterSet tests finished.\n");
    }
    
    /**
     * Executes tests for the InstructionMemory class.
     * This method creates an instance of InstructionMemoryTest and calls its runTests() method
     * to verify the correct behavior of the InstructionMemory class.
     */
    public static void testInstructionMemory() {
        System.out.println("Running InstructionMemory tests...");
        
        // Instantiate InstructionMemoryTest and run its tests
        InstructionMemoryTest instructionMemoryTest = new InstructionMemoryTest();
        instructionMemoryTest.runTests();
        
        System.out.println("InstructionMemory tests finished.\n");
    }
    
    /**
     * Executes tests for the CacheLine class.
     * This method creates an instance of CacheLineTest and calls its runTests() method
     * to verify the correct behavior of the CacheLine class.
     */
    public static void testCacheLine() {
        System.out.println("Running CacheLine tests...");
        
        // Instantiate CacheLineTest and run its tests
        CacheLineTest cacheLineTest = new CacheLineTest();
        cacheLineTest.runTests();
        
        System.out.println("CacheLine tests finished.\n");
    }
    
    /**
     * Executes tests for the Cache class.
     * This method creates an instance of CacheTest and calls its runTests() method
     * to verify the correct behavior of the Cache class.
     */
    public static void testCache() {
        System.out.println("Running Cache tests...");
        
        // Instantiate CacheTest and run its tests
        CacheTest cacheTest = new CacheTest();
        cacheTest.runTests();
        
        System.out.println("Cache tests finished.\n");
    }
    
    /**
     * Executes tests for the Memory class.
     * This method creates an instance of MemoryTest and calls its runTests() method
     * to verify the correct behavior of the Memory class.
     */
    public static void testMemory() {
        System.out.println("Running Memory tests...");
        
        // Instantiate MemoryTest and run its tests
        MemoryTest memoryTest = new MemoryTest();
        memoryTest.runTests();
        
        System.out.println("Memory tests finished.\n");
    }

    /**
     * The main entry point to execute all tests.
     * This method starts by printing a message, calls test methods for each class, 
     * and then prints a completion message.
     *
     * @param args Command-line arguments (not used in this test suite).
     */
    public static void main(String[] args) {
        System.out.println("\nStarting tests...\n\n");
        
        testRegister();             // Execute tests for the Register class
        testRegisterSet();          // Execute tests for the RegisterSet class
        testInstructionMemory();    // Execute tests for the InstructionMemory class
        testCacheLine();            // Execute tests for the CacheLine class
        testCache();                // Execute tests for the Cache class
        testMemory();               // Execute tests for the Memory class
        
        System.out.println("\n\nTests finished.\n");
    }
}
