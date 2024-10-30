package com.mycompany.mesi.Memory;

import java.util.HashMap;

/**
 * Represents a shared memory in a multiprocessor system.
 * The memory allows read and write operations at specific addresses.
 * Each memory address can store a 64-bit data value.
 */
public class Memory {
    private final HashMap<Integer, Long> memory; // HashMap to store data at specific memory addresses

    /**
     * Constructs an instance of Memory with an empty memory map.
     * Initializes the memory as a HashMap where each key represents a memory address.
     */
    public Memory() {
        memory = new HashMap<>();
    }

    /**
     * Reads the data stored at a specific memory address.
     * If the address does not contain any data, it returns 0 by default.
     *
     * @param address The memory address to read data from.
     * @return The 64-bit data stored at the specified address, or 0 if the address is empty.
     */
    public long read(int address) {
        return memory.getOrDefault(address, 0L);
    }

    /**
     * Writes data to a specific memory address.
     * If the address already contains data, it is overwritten with the new value.
     *
     * @param address The memory address to write data to.
     * @param data The 64-bit data value to store at the specified address.
     */
    public void write(int address, long data) {
        memory.put(address, data);
    }
}
