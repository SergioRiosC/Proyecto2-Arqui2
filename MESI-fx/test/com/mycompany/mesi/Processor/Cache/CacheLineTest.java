package com.mycompany.mesi.Processor.Cache;

/**
 * Class to test the functionality of the CacheLine class.
 * This class runs various tests to verify the correct behavior of 
 * methods in the CacheLine class, including setting and getting data,
 * address, and state.
 * 
 * Tests include:
 * - Initialization with a specific address and data
 * - Retrieving and modifying data and address
 * - Setting and retrieving MESI state
 */
public class CacheLineTest {

    /**
     * Runs all tests for the CacheLine class.
     * Creates instances of CacheLine and tests various functionalities,
     * including setting and getting data, address, and state.
     */
    public void runTests() {
        // Test CacheLine initialization
        testInitialization();

        // Test setting and getting data
        testSetAndGetData();

        // Test setting and getting MESI state
        testSetAndGetState();

        System.out.println("\nAll CacheLine tests completed.");
    }

    /**
     * Tests the initialization of a CacheLine instance with a specific address and data.
     * Verifies that the initial state is set to INVALID.
     */
    private void testInitialization() {
        System.out.println("Testing CacheLine initialization...");

        CacheLine cacheLine = new CacheLine(100, 12345L);

        System.out.println("Address (expected 100): " + cacheLine.getAddress());
        System.out.println("Data (expected 12345): " + cacheLine.getData());
        System.out.println("Initial State (expected INVALID): " + cacheLine.getState());

        System.out.println("CacheLine initialization test completed.\n");
    }

    /**
     * Tests setting and retrieving data in the CacheLine.
     */
    private void testSetAndGetData() {
        System.out.println("Testing setting and getting data...");

        CacheLine cacheLine = new CacheLine(200, 54321L);
        System.out.println("Initial Data (expected 54321): " + cacheLine.getData());

        cacheLine.setData(98765L);
        System.out.println("Data after setData(98765) (expected 98765): " + cacheLine.getData());

        System.out.println("Setting and getting data test completed.\n");
    }

    /**
     * Tests setting and retrieving the MESI state in the CacheLine.
     */
    private void testSetAndGetState() {
        System.out.println("Testing setting and getting MESI state...");

        CacheLine cacheLine = new CacheLine(300, 11111L);
        System.out.println("Initial State (expected INVALID): " + cacheLine.getState());

        cacheLine.setState(MESIState.SHARED);
        System.out.println("State after setState(SHARED) (expected SHARED): " + cacheLine.getState());

        cacheLine.setState(MESIState.MODIFIED);
        System.out.println("State after setState(MODIFIED) (expected MODIFIED): " + cacheLine.getState());

        System.out.println("Setting and getting MESI state test completed.\n");
    }

}
