package com.mycompany.mesi.Processor.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a cache in a memory system, consisting of multiple cache lines.
 * Each cache line stores data, its memory address, and its coherence state according 
 * to the MESI protocol. This class provides methods to retrieve, set, update, 
 * and modify the state of cache lines within the cache.
 */
public class Cache {
    private final Map<Integer, CacheLine> cacheLines;   // Map to store cache lines by their address
    private final int cacheSize = 8;                    // Minimum of 8 blocks of 32 bytes

    /**
     * Constructs a Cache instance with a predefined size of cache lines.
     * Initializes the cache lines map with a default capacity based on cacheSize.
     */
    public Cache() {
        this.cacheLines = new HashMap<>(cacheSize);
    }

    /**
     * Retrieves a cache line at a specific memory address.
     *
     * @param address The memory address of the cache line to retrieve.
     * @return The CacheLine at the specified address, or null if not present in the cache.
     */
    public CacheLine getLine(int address) {
        return cacheLines.get(address);
    }

    /**
     * Sets a cache line with a specific address, data, and state.
     * This is typically used when loading data from memory or bus into the cache.
     *
     * @param address The memory address of the cache line.
     * @param data The data to store in the cache line.
     * @param state The MESIState of the cache line after setting it.
     */
    public void setLine(int address, long data, MESIState state) {
        CacheLine line = new CacheLine(address, data);
        line.setState(state);
        cacheLines.put(address, line);
    }

    /**
     * Updates the data in an existing cache line at a specific address.
     * Changes the MESI state to MODIFIED to indicate that the line has been modified.
     *
     * @param address The memory address of the cache line to update.
     * @param data The new data to store in the cache line.
     */
    public void updateLine(int address, long data) {
        CacheLine line = cacheLines.get(address);
        if (line != null) {
            line.setData(data);
            line.setState(MESIState.MODIFIED); // State changes to MODIFIED after a write
        }
    }

    /**
     * Changes the MESI state of a specific cache line at a given address.
     *
     * @param address The memory address of the cache line.
     * @param state The new MESIState to assign to the cache line.
     */
    public void setState(int address, MESIState state) {
        CacheLine line = cacheLines.get(address);
        if (line != null) {
            line.setState(state);
        }
    }
}
