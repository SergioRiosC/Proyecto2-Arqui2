package com.mycompany.mesi.Processor.Cache;

/**
 * Represents a cache line in a cache memory system, storing data, 
 * its memory address, and its coherence state (using the MESI protocol).
 * Each cache line contains 64-bit data and a state that helps in maintaining
 * cache coherence across multiple processors.
 */
public class CacheLine {
    private final int address; // Memory address of the cache line
    private long data;         // 64-bit data stored in the cache line
    private MESIState state;   // Coherence state of the cache line in the MESI protocol

    /**
     * Constructs a CacheLine with a specified address and data.
     * The initial state of the cache line is set to INVALID.
     *
     * @param address The memory address of the cache line.
     * @param data The 64-bit data to be stored in the cache line.
     */
    public CacheLine(int address, long data) {
        this.address = address;
        this.data = data;
        this.state = MESIState.INVALID;
    }

    /**
     * Retrieves the memory address of the cache line.
     *
     * @return The address of the cache line.
     */
    public int getAddress() {
        return address;
    }

    /**
     * Retrieves the data stored in the cache line.
     *
     * @return The 64-bit data of the cache line.
     */
    public long getData() {
        return data;
    }

    /**
     * Sets the data in the cache line.
     *
     * @param data The 64-bit data to be stored in the cache line.
     */
    public void setData(long data) {
        this.data = data;
    }

    /**
     * Retrieves the current coherence state of the cache line.
     *
     * @return The current state of the cache line in the MESI protocol.
     */
    public MESIState getState() {
        return state;
    }

    /**
     * Sets the coherence state of the cache line.
     *
     * @param state The new MESIState to be assigned to the cache line.
     */
    public void setState(MESIState state) {
        this.state = state;
    }

    /**
     * Returns a string representation of the cache line.
     * Includes the address, data, and state of the cache line.
     *
     * @return A string representing the cache line.
     */
    @Override
    public String toString() {
        return "CacheLine [address=" + address + ", data=" + data + ", state=" + state + "]";
    }
}
