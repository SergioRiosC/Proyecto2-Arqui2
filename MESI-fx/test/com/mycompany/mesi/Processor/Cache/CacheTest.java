package com.mycompany.mesi.Processor.Cache;

/**
 * Class to test the functionality of the Cache class.
 * This class runs various tests to verify the correct behavior of 
 * methods in the Cache class, including setting and getting cache lines,
 * updating data, and modifying MESI state.
 * 
 * Tests include:
 * - Initialization of cache
 * - Setting and retrieving cache lines
 * - Updating data in cache lines
 * - Changing MESI state of cache lines
 */
public class CacheTest {

    /**
     * Runs all tests for the Cache class.
     * Creates an instance of Cache and tests various functionalities,
     * including setting and getting cache lines, updating data, and changing state.
     */
    public void runTests() {
        // Initialize Cache and run tests
        Cache cache = new Cache();
        System.out.println("Cache initialized with a size of 8.\n");

        // Test setting and retrieving cache lines
        testSetAndGetLine(cache);

        // Test updating data in cache lines
        testUpdateLine(cache);

        // Test changing MESI state of cache lines
        testSetState(cache);

        System.out.println("\nAll Cache tests completed.");
    }

    /**
     * Tests setting and retrieving cache lines in the Cache.
     *
     * @param cache The Cache instance to test.
     */
    private void testSetAndGetLine(Cache cache) {
        System.out.println("Testing set and get cache lines...");

        // Set cache lines
        cache.setLine(100, 12345L, MESIState.SHARED);
        cache.setLine(200, 54321L, MESIState.EXCLUSIVE);

        // Retrieve cache lines and print results
        CacheLine line1 = cache.getLine(100);
        System.out.println("Cache line at address 100 (expected data: 12345, expected state: SHARED): " +
                "data=" + line1.getData() + ", state=" + line1.getState());

        CacheLine line2 = cache.getLine(200);
        System.out.println("Cache line at address 200 (expected data: 54321, expected state: EXCLUSIVE): " +
                "data=" + line2.getData() + ", state=" + line2.getState());

        System.out.println("Set and get cache lines test completed.\n");
    }

    /**
     * Tests updating data in an existing cache line.
     * Verifies that the MESI state changes to MODIFIED after updating.
     *
     * @param cache The Cache instance to test.
     */
    private void testUpdateLine(Cache cache) {
        System.out.println("Testing updating cache line data...");

        // Set initial line
        cache.setLine(300, 11111L, MESIState.SHARED);

        // Update data in cache line at address 300
        cache.updateLine(300, 22222L);

        // Retrieve updated line and print results
        CacheLine line = cache.getLine(300);
        System.out.println("Cache line at address 300 after update (expected data: 22222, expected state: MODIFIED): " +
                "data=" + line.getData() + ", state=" + line.getState());

        System.out.println("Updating cache line data test completed.\n");
    }

    /**
     * Tests changing the MESI state of an existing cache line.
     *
     * @param cache The Cache instance to test.
     */
    private void testSetState(Cache cache) {
        System.out.println("Testing changing MESI state...");

        // Set initial line
        cache.setLine(400, 33333L, MESIState.EXCLUSIVE);

        // Change MESI state to SHARED
        cache.setState(400, MESIState.SHARED);
        CacheLine line = cache.getLine(400);
        System.out.println("Cache line at address 400 after setState(SHARED) (expected state: SHARED): " + line.getState());

        // Change MESI state to INVALID
        cache.setState(400, MESIState.INVALID);
        System.out.println("Cache line at address 400 after setState(INVALID) (expected state: INVALID): " + line.getState());

        System.out.println("Changing MESI state test completed.\n");
    }

}
