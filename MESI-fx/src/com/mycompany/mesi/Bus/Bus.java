package com.mycompany.mesi.Bus;

import com.mycompany.mesi.Memory.Memory;
import com.mycompany.mesi.Processor.Processor;
import com.mycompany.mesi.Processor.Cache.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the shared bus in a multiprocessor system, enabling communication
 * between processors and shared memory. The bus manages operations related to
 * reading from memory, invalidating cache lines in other processors, and checking 
 * exclusivity of cache lines across processors.
 */
public class Bus {
    private final List<Processor> processors; // List of processors connected to the bus
    private final Memory memory; // Shared memory

    /**
     * Constructs a Bus instance with access to shared memory.
     *
     * @param memory The shared memory that all processors have access to.
     */
    public Bus(Memory memory) {
        this.processors = new ArrayList<>();
        this.memory = memory;
    }

    /**
     * Adds a processor to the bus, allowing it to participate in cache coherence operations.
     *
     * @param processor The Processor instance to add to the bus.
     */
    public void addProcessor(Processor processor) {
        processors.add(processor);
    }

    /**
     * Reads data from shared memory at a specified address. If any other processor
     * has the data in a modified state, it writes back the data to memory before
     * the read operation.
     *
     * @param address The memory address to read data from.
     * @param requester The processor requesting the read.
     * @return A CacheLine containing the address and the data read from memory.
     */
    public CacheLine readFromMemory(int address, Processor requester) {
        // Check if any other processor has the cache line in a modified state
        for (Processor p : processors) {
            if (p != requester) {
                CacheLine line = p.getCache().getLine(address);
                if (line != null && line.getState() == MESIState.MODIFIED) {
                    memory.write(address, line.getData()); // Write back to memory
                    line.setState(MESIState.SHARED); // Change state to SHARED after writing back
                }
            }
        }
        // Read the updated data from shared memory
        long data = memory.read(address);
        return new CacheLine(address, data);
    }

    /**
     * Invalidates the cache line at the specified address in all processors
     * except the one making the request, ensuring exclusive access for the requester.
     *
     * @param address The memory address to invalidate in other caches.
     * @param requester The processor requesting the invalidation.
     */
    public void invalidateOtherCaches(int address, Processor requester) {
        for (Processor p : processors) {
            if (p != requester) {
                CacheLine line = p.getCache().getLine(address);
                if (line != null) {
                    line.setState(MESIState.INVALID); // Set state to INVALID in other caches
                }
            }
        }
    }

    /**
     * Checks if a cache line at the specified address is exclusively held by the
     * requesting processor, meaning no other processor has a valid copy.
     *
     * @param address The memory address to check for exclusivity.
     * @param requester The processor requesting the exclusivity check.
     * @return True if the cache line is exclusively held by the requester; false otherwise.
     */
    public boolean isExclusive(int address, Processor requester) {
        for (Processor p : processors) {
            if (p != requester) {
          CacheLine line = p.getCache().getLine(address);
                if (line != null && line.getState() != MESIState.INVALID) {
                    return false; // Another processor has a valid copy
                }
            }
        }
        return true; // No other processor has a valid copy
    }
}
