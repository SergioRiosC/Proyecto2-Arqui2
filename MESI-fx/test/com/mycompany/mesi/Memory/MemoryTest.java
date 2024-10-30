package com.mycompany.mesi.Memory;

/**
 * Class to test the functionality of the Memory class.
 * This class runs various tests to verify the correct behavior of 
 * methods in the Memory class, including reading and writing data.
 * 
 * Tests include:
 * - Reading from an empty address
 * - Writing and reading data at specific addresses
 * - Overwriting existing data at an address
 */
public class MemoryTest {

    /**
     * Runs all tests for the Memory class.
     * Creates an instance of Memory and tests various functionalities,
     * including reading from an empty address, writing and reading data,
     * and overwriting existing data.
     */
    public void runTests() {
        // Initialize Memory and run tests
        Memory memory = new Memory();
        System.out.println("Memory initialized.\n");

        // Test reading from an empty address
        testReadEmptyAddress(memory);

        // Test writing and reading data
        testWriteAndReadData(memory);

        // Test overwriting existing data
        testOverwriteData(memory);

        System.out.println("\nAll Memory tests completed.");
    }

    /**
     * Tests reading from an empty address in the Memory.
     * Verifies that reading from an address with no data returns 0.
     *
     * @param memory The Memory instance to test.
     */
    private void testReadEmptyAddress(Memory memory) {
        System.out.println("Testing reading from an empty address...");

        long data = memory.read(100); // Address 100 is expected to be empty
        System.out.println("Read from address 100 (expected 0): " + data);

        System.out.println("Reading from an empty address test completed.\n");
    }

    /**
     * Tests writing data to and reading data from specific addresses in the Memory.
     *
     * @param memory The Memory instance to test.
     */
    private void testWriteAndReadData(Memory memory) {
        System.out.println("Testing writing and reading data...");

        // Write data to specific addresses
        memory.write(200, 12345L);
        memory.write(300, 54321L);

        // Read and verify the data from those addresses
        System.out.println("Read from address 200 (expected 12345): " + memory.read(200));
        System.out.println("Read from address 300 (expected 54321): " + memory.read(300));

        System.out.println("Writing and reading data test completed.\n");
    }

    /**
     * Tests overwriting data at a specific address in the Memory.
     * Verifies that the data at the address is updated correctly.
     *
     * @param memory The Memory instance to test.
     */
    private void testOverwriteData(Memory memory) {
        System.out.println("Testing overwriting data...");

        // Write initial data to address 400
        memory.write(400, 11111L);
        System.out.println("Initial write to address 400 (expected 11111): " + memory.read(400));

        // Overwrite data at address 400
        memory.write(400, 22222L);
        System.out.println("After overwriting address 400 (expected 22222): " + memory.read(400));

        System.out.println("Overwriting data test completed.\n");
    }

}
